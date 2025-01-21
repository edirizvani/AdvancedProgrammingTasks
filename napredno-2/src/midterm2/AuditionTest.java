import java.util.*;

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticpant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}
class Paticipant{
    String city;
    String code;
    String name;
    int age;

    public Paticipant(String city, String code, String name, int age) {
        this.city = city;
        this.code = code;
        this.name = name;
        this.age = age;
    }

    int compareTo(Paticipant paticipant) {
        int nameComparison = this.name.compareTo(paticipant.name);
        if(nameComparison != 0) {
            return nameComparison;
        }
        int age = Integer.compare(this.age, paticipant.age);
        if(age != 0) {
            return age;
        }
        return this.code.compareTo(paticipant.code);
        }


    @Override
    public String toString() {
        return String.format("%s %s %d",code, name, age);
    }
}
class Audition {
    Map<String, Map<String,Paticipant>> map_map;

    public Audition() {
        map_map = new HashMap<String, Map<String,Paticipant>>();
    }

    void addParticpant(String city, String code, String name, int age){
        if(!map_map.containsKey(city)){
             Map<String,Paticipant> map=new HashMap<>();
             map.put(code, new Paticipant(city, code, name, age));
            map_map.put(city,map);
        }else{
            map_map.get(city).computeIfAbsent(code,k->new Paticipant(city, code, name, age));
        }
    }

    void listByCity(String city) {
        TreeSet<Paticipant> set = new TreeSet<>(Paticipant::compareTo);
        set.addAll(map_map.get(city).values());
        for(Paticipant p : set) {
            System.out.println(p);
        }
    }
}