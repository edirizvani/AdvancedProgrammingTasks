//package midterm2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}

public class StopCoronaTest {

    public static double timeBetweenInSeconds(ILocation location1, ILocation location2) {
        return Math.abs(Duration.between(location1.getTimestamp(), location2.getTimestamp()).getSeconds());
    }
    public static  double euclideanDistance(ILocation l1, ILocation l2){
        return Math.sqrt(Math.pow(l1.getLongitude() - l2.getLongitude(), 2) +   Math.pow(l1.getLatitude() - l2.getLatitude(), 2));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StopCoronaApp stopCoronaApp = new StopCoronaApp();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            switch (parts[0]) {
                case "REG": //register
                    String name = parts[1];
                    String id = parts[2];
                    try {
                        stopCoronaApp.addUser(name, id);
                    } catch (UserAlreadyExistException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "LOC": //add locations
                    id = parts[1];
                    List<ILocation> locations = new ArrayList<>();
                    for (int i = 2; i < parts.length; i += 3) {
                        locations.add(createLocationObject(parts[i], parts[i + 1], parts[i + 2]));
                    }
                    stopCoronaApp.addLocations(id, locations);

                    break;
                case "DET": //detect new cases
                    id = parts[1];
                    LocalDateTime timestamp = LocalDateTime.parse(parts[2]);
                    stopCoronaApp.detectNewCase(id, timestamp);

                    break;
                case "REP": //print report
                    stopCoronaApp.createReport();
                    break;
                default:
                    break;
            }
        }
    }

    private static ILocation createLocationObject(String lon, String lat, String timestamp) {
        return new ILocation() {
            @Override
            public double getLongitude() {
                return Double.parseDouble(lon);
            }

            @Override
            public double getLatitude() {
                return Double.parseDouble(lat);
            }

            @Override
            public LocalDateTime getTimestamp() {
                return LocalDateTime.parse(timestamp);
            }
        };
    }
}


class User{
    String name;
    String id;
    List<ILocation> locations;

    LocalDateTime timeHeGotVirus;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.locations = new ArrayList<>();

        timeHeGotVirus = null;
    }
    void addLocation(List<ILocation> iLocations) {
        locations=iLocations;
    }
    void detectNewCase(LocalDateTime timestamp){
        timeHeGotVirus = timestamp;
    }

    public LocalDateTime getTimeHeGotVirus() {
        return timeHeGotVirus;
    }

    int isDirectContact(User u){
        if(this.equals(u)){
            return 0;
        }
        int count=0;
        for (int i = 0; i < this.locations.size(); i++) {
            for (int j = 0; j < u.locations.size(); j++) {
                if(StopCoronaTest.euclideanDistance(locations.get(i),u.locations.get(j))<=2 && StopCoronaTest.timeBetweenInSeconds(locations.get(i),u.locations.get(j))<=300){
                    ++count;
                    break;
                }
            }
        }
        return count;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        if(timeHeGotVirus!=null){
            return String.format("%s %s %s", name, id, timeHeGotVirus.toString());
        }else{
            return String.format("%s %s I guess he doesnt have virus", name, id);
        }
    }
}
class StopCoronaApp{
    Map<String,User> users ;
    Set<User> usersSet;
    List<User> indirectUsers;
    public StopCoronaApp() {
        this.users = new HashMap<>();
        indirectUsers = new ArrayList<>();
    }

    void addUser(String name, String id) throws UserAlreadyExistException {
        if(!users.containsKey(id)){
            users.put(id, new User(name,id));
        }else{
            throw new UserAlreadyExistException(String.format("User with %s already exists", id));
        }
    }
    void addLocations (String id, List<ILocation> iLocations){
        if(!users.containsKey(id)){

        }else{
            users.get(id).addLocation(iLocations);
        }
    }
    void detectNewCase (String id, LocalDateTime timestamp){
        if(!users.containsKey(id)){

        }else{
            User user = users.get(id);
            user.detectNewCase(timestamp);
            indirectUsers.add(user);
        }
    }
    Map<User, Integer> getDirectContacts (User u){
        Map<User, Integer> map = new HashMap<>();
        users.entrySet().stream().forEach(e ->{
            int count=u.isDirectContact(e.getValue());
            if(count>0){
                map.put(e.getValue(),count);
            }
        });
        return map;
    }
    Collection<User> getIndirectContacts (User u){
       Map<User, Integer> map = getDirectContacts(u);

       Map<String,User> userMap = new HashMap<>();


       users.entrySet().stream().forEach(e ->{

          if(!map.containsKey(e.getValue()) && !e.getValue().equals(u)){

              for (Map.Entry<User,Integer> entry:map.entrySet()){
                  if(entry.getKey().equals(e.getValue())){
                      continue;
                  }
                  if(e.getValue().isDirectContact(entry.getKey())!=0){
                      if(!userMap.containsKey(e.getValue().id)){
                          userMap.put(e.getValue().getId(),e.getValue());
                      }
                  }
              }
          }
       });
      return userMap.values();
    }
    void createReport (){
        indirectUsers.sort(Comparator.comparing(User::getTimeHeGotVirus));
        int largerSum=0;
        for (User u : indirectUsers) {
            System.out.println(u);
            System.out.println("Direct contacts:");
            Map<User, Integer> map = getDirectContacts(u);
            final int[] sum = {0};
            map.entrySet().stream().sorted((e1,e2)->{
                if(e1.getValue()>=e2.getValue()){
                    return -1;
                }else{
                    return 1;
                }
            }).forEach(e ->{
                System.out.println(String.format("%s %s*** %d",e.getKey().name,e.getKey().id.substring(0,4),e.getValue()));
                sum[0] +=e.getValue();
            });
            largerSum+=sum[0];
            System.out.printf("Count of direct contacts: %d\n",sum[0]);
            Collection<User> indirectContacts = getIndirectContacts(u);
            System.out.println("Indirect contacts:");
            indirectContacts.stream().forEach(e ->{
                System.out.println(String.format("%s %s***",e.name,e.id.substring(0,4)));

            });
            System.out.printf("Count of indirect contacts: %d\n",indirectContacts.size());
        }

    }

}
