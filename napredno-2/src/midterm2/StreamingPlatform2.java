import java.net.Inet4Address;
import java.util.*;
import java.util.stream.Collectors;

class CosineSimilarityCalculator {

    public static double cosineSimilarity(Map<String, Integer> c1, Map<String, Integer> c2) {
        return cosineSimilarity(c1.values(), c2.values());
    }

    public static double cosineSimilarity(Collection<Integer> c1, Collection<Integer> c2) {
        int[] array1;
        int[] array2;
        array1 = c1.stream().mapToInt(i -> i).toArray();
        array2 = c2.stream().mapToInt(i -> i).toArray();
        double up = 0.0;
        double down1 = 0, down2 = 0;

        for (int i = 0; i < c1.size(); i++) {
            up += (array1[i] * array2[i]);
        }

        for (int i = 0; i < c1.size(); i++) {
            down1 += (array1[i] * array1[i]);
        }

        for (int i = 0; i < c1.size(); i++) {
            down2 += (array2[i] * array2[i]);
        }

        return up / (Math.sqrt(down1) * Math.sqrt(down2));
    }
}


public class StreamingPlatform2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StreamingPlatform sp = new StreamingPlatform();

        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            if (parts[0].equals("addMovie")) {
                String id = parts[1];
                String name = Arrays.stream(parts).skip(2).collect(Collectors.joining(" "));
                sp.addMovie(id ,name);
            } else if (parts[0].equals("addUser")){
                String id = parts[1];
                String name = parts[2];
                sp.addUser(id ,name);
            } else if (parts[0].equals("addRating")){
                //String userId, String movieId, int rating
                String userId = parts[1];
                String movieId = parts[2];
                int rating = Integer.parseInt(parts[3]);
                sp.addRating(userId, movieId, rating);
            } else if (parts[0].equals("topNMovies")){
                int n = Integer.parseInt(parts[1]);
                System.out.println("TOP " + n + " MOVIES:");
                sp.topNMovies(n);
            } else if (parts[0].equals("favouriteMoviesForUsers")) {
                List<String> users = Arrays.stream(parts).skip(1).collect(Collectors.toList());
                System.out.println("FAVOURITE MOVIES FOR USERS WITH IDS: " + users.stream().collect(Collectors.joining(", ")));
                sp.favouriteMoviesForUsers(users);
            } else if (parts[0].equals("similarUsers")) {
                String userId = parts[1];
                System.out.println("SIMILAR USERS TO USER WITH ID: " + userId);
                sp.similarUsers(userId);
            }
        }
    }
}
class StreamingPlatform {
    Map<String,Movie> movies;
    Map<String,User> users;
    Map<String,Map<Movie,Rating>> userMovieMap;
    List<Rating> ratings;
    public StreamingPlatform() {
        movies = new HashMap<>();
        users = new HashMap<>();
        userMovieMap = new HashMap<>();
        ratings = new ArrayList<>();
    }
    void addMovie (String id, String name){
        if(movies.containsKey(id)){
            System.out.println("Movie " + id + " already exists");
        }
        movies.put(id,new Movie(id,name));
    }
    void addUser (String id, String username){
        if(users.containsKey(username)){
            System.out.println("User " + username + " already exists");
        }
        users.put(id,new User(id,username));
    }
   void addRating (String userId, String movieId, int rating){
        if(rating>10 || rating<1){
            System.out.println("Rating must be between 1 and 10");
        }
        User user = users.get(userId);
        Movie movie = movies.get(movieId);
        if(movie == null || user == null){
            System.out.println("User " + userId + " does not exist or movie " + movieId + " does not exist");
            return;
        }
        Rating r= new Rating(movie,user,rating);
        ratings.add(r);
        movie.addRating(r);

        userMovieMap.putIfAbsent(userId,new HashMap<>());
        userMovieMap.get(userId).putIfAbsent(movie,r);
    }
    void topNMovies (int n){
        List<Movie> movies_list = movies.values().stream().sorted(Comparator.comparing(Movie::getRating).reversed()).limit(n).collect(Collectors.toList());
        movies_list.forEach(System.out::println);
    }
    void favouriteMoviesForUsers(List<String> userIds){
        for(String userid : userIds){
            System.out.printf("User ID: %s Name: %s\n",userid,users.get(userid).getUsername());
            List<Movie> movies=new ArrayList<>();
            int max= (int) userMovieMap.get(userid).values().stream().mapToDouble(Rating::getRatingInt).max().orElse(0);
            userMovieMap.get(userid).entrySet().stream().sorted((e1,e2)->{
                if (e1.getKey().getRating() > e2.getKey().getRating()) {
                    return -1;
                }else{
                    return 1;
                }
            }).filter(entry->entry.getValue().getRatingInt()==max).forEach(entry->{
                System.out.printf("Movie ID: %s Title: %s Rating: %.2f\n",entry.getKey().id,entry.getKey().name,entry.getKey().getRating());

            });
            System.out.println();
            }
        }
    void similarUsers(String userId){
        User user = users.get(userId);
        Map<String,Integer> mapUser=new HashMap<>();
        userMovieMap.get(userId).forEach((movie,rating)->{
            mapUser.put(movie.id, rating.getRatingInt());
        });
        movies.forEach((movie,rating)->{
            if(!mapUser.containsKey(movie)){
                mapUser.put(movie,0);
            }
        });

        //here is the list that I will keep;
        Map<Double,User> list=new TreeMap<>(Comparator.reverseOrder());

        for(Map.Entry<String, Map<Movie, Rating>> entry:userMovieMap.entrySet()){
            if(entry.getKey().equals(userId)){
                continue;
            }
            User user1=users.get(entry.getKey());
            Map<String,Integer> mapUser1=new HashMap<>();
            entry.getValue().forEach((movie,rating)->{
               mapUser1.put(movie.id, rating.getRatingInt());
            });
            movies.forEach((movie,rating)->{
                if(!mapUser1.containsKey(movie)){
                    mapUser1.put(movie,0);
                }
            });
            double simmilarity=CosineSimilarityCalculator.cosineSimilarity(mapUser,mapUser1);
            list.put(simmilarity,user1);
        }
        list.entrySet().forEach((entry)->{
            System.out.println(entry.getValue()+" "+entry.getKey());
        });
    }
}
class Movie{
    String id;
    String name;
    List<Rating> ratings;
    public Movie(String id, String name) {
        this.id = id;
        this.name = name;
        ratings = new ArrayList<>();
    }
    void addRating (Rating rating){
        ratings.add(rating);
    }
    double getRating(){
        return ratings.stream().mapToDouble(Rating::getRating).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return String.format("Movie ID: %s Title: %s Rating: %.2f", id, name, this.getRating());
    }
}
class User{
    String id;
    String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return String.format("User ID: %s Name: %s", id, username);
    }
}
class Rating{
    Movie movie;
    User user;
    double rating;

    public Rating(Movie movie, User user, double rating) {
        this.movie = movie;
        this.user = user;
        this.rating = rating;
    }
    public int getRatingInt(){
        return (int)rating;
    }

    public double getRating() {
        return rating;
    }
}
