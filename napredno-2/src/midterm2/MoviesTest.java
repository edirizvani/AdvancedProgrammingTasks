////package midterm2;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class MoviesTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        MoviesList moviesList = new MoviesList();
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; ++i) {
//            String title = scanner.nextLine();
//            int x = scanner.nextInt();
//            int[] ratings = new int[x];
//            for (int j = 0; j < x; ++j) {
//                ratings[j] = scanner.nextInt();
//            }
//            scanner.nextLine();
//            moviesList.addMovie(title, ratings);
//        }
//        scanner.close();
//        List<Movie> movies = moviesList.top10ByAvgRating();
//        System.out.println("=== TOP 10 BY AVERAGE RATING ===");
//        for (Movie movie : movies) {
//            System.out.println(movie);
//        }
//        movies = moviesList.top10ByRatingCoef();
//        System.out.println("=== TOP 10 BY RATING COEFFICIENT ===");
//        for (Movie movie : movies) {
//            System.out.println(movie);
//        }
//    }
//}
//class MoviesList {
//    private List<Movie> movies;
//
//    public MoviesList() {
//        this.movies = new ArrayList<>();
//    }
//    public void addMovie(String title, int[] ratings){
//        this.movies.add(new Movie(title, ratings));
//    }
//    public List<Movie> top10ByAvgRating(){
//        return movies.stream().sorted(Comparator.comparing(Movie::getRating).reversed().thenComparing(Movie::getTitle)).limit(10).collect(Collectors.toList());
//    }
//    double getMaxRating(){
//        return movies.stream().mapToDouble(movie -> movie.getRating()).max().orElse(0.0);
//
//    }
//    double compareMovie(Movie movie){
//        return movie.getRating()*movie.getRatingsLength()*(movie.getSumRatings()*getMaxRating());
//    }
//    public List<Movie> top10ByRatingCoef(){
//        Comparator<Movie> byRating = Comparator.comparing(this::compareMovie).reversed().thenComparing(Movie::getTitle);
//        return movies.stream().sorted(byRating).limit(10).collect(Collectors.toList());
//
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//       movies.forEach(movie -> builder.append(movie));
//       return builder.toString();
//    }
//}
//class Movie{
//    private String title;
//    private int[] ratings;
//
//    public Movie(String title, int[] ratings) {
//        this.title = title;
//        this.ratings = ratings;
//    }
//    public double getRating(){
//        return Arrays.stream(ratings).average().orElse(0.0);
//    }
//    public double getSumRatings(){
//        return Arrays.stream(ratings).sum();
//
//    }
//
//
//    public int getRatingsLength() {
//        return ratings.length;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%s (%.2f) of %d ratings",title,getRating(),ratings.length);
//    }
//}
//
//// vashiot kod ovde