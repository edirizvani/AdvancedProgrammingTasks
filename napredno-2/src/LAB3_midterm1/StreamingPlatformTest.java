package LAB3_midterm1;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class StreamingPlatformTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StreamingPlatform sp = new StreamingPlatform();
        while (sc.hasNextLine()){
            String line = sc.nextLine();
            String [] parts = line.split(" ");
            String method = parts[0];
            String data = Arrays.stream(parts).skip(1).collect(Collectors.joining(" "));
            if (method.equals("addItem")){
                sp.addItem(data);
            }
            else if (method.equals("listAllItems")){
                sp.listAllItems(System.out);
            } else if (method.equals("listFromGenre")){
                System.out.println(data);
                sp.listFromGenre(data, System.out);
            }
        }

    }
}
class StreamingPlatform{
    Set<Stream> streams;

    public StreamingPlatform() {
        this.streams = new TreeSet<>();
    }

    void addItem (String data){
        String[] info=data.split(";");
        if(info[2].startsWith("S1")){
            createTvShow(info);
        }else{
            streams.add(createMovie(info));
        }
    }
    void createTvShow(String[] info){

        String[] categories=info[1].split(",");
        Set<Movie> episodes=new TreeSet<>();
        for (int i = 2; i < info.length; i++) {
            String[] episode=info[i].split(" ");
            String name=episode[0];
            int[] ratingsInt=new int[episode.length-1];
            int c=0;
            for (int j = 1; j <episode.length ; j++) {
                ratingsInt[c++]=Integer.parseInt(episode[j]);
            }
           episodes.add(new Movie(name,categories,ratingsInt));
        }
        streams.add(new TvShow(info[0],categories,episodes));

    }
    Movie createMovie(String[] info){
        String[] categories=info[1].split(",");
        String[] ratings=info[2].split(" ");
        int[] ratingsInt=Arrays.stream(ratings).mapToInt(Integer::parseInt).toArray();
        return new Movie(info[0],categories,ratingsInt);
    }
    void listAllItems (OutputStream os){
        PrintWriter pr=new PrintWriter(os);
        for(Stream str:streams){
            pr.println(str);
        }
        pr.flush();
    }
    void listFromGenre (String genre, OutputStream os){
        PrintWriter pr=new PrintWriter(os);
        for(Stream str:streams){
            if(str.hasSameGenre(genre)){
                pr.println(str);
            }
        }
        pr.flush();
    }
}

abstract class Stream implements Comparable<Stream>{
    String name;
    String[] genre;

     public Stream(String name, String[] genre) {
         this.name = name;
         this.genre = genre;
     }
     abstract double getRating();

    @Override
    public int compareTo(Stream o) {
        if(this.getRating()>o.getRating()){
            return -1;
        }else {
            return 1;
        }
    }
    boolean hasSameGenre(String g){
        for (int i = 0; i < this.genre.length; i++) {
            if(this.genre[i].equals(g)){
                return true;
            }
        }
        return false;
    }


}
class Movie extends Stream {
    int[] ratings;

    public Movie(String name, String[] genre,  int[]  ratings) {
        super(name, genre);
        this.ratings = ratings;
    }
    @Override
    double getRating(){
        return Arrays.stream(ratings).average().orElse(0.0)*Math.min(ratings.length/20.0,1.0);
    }
    @Override
    public int compareTo(Stream other) {
        // Use default comparison logic from Stream, but reverse the order for descending

            if(this.getRating()>other.getRating()){
                return -1;// Descending for movies
            }else{
                return 1;
            }

    }

    @Override
    public String toString() {
        return String.format("Movie %s %.4f",name,getRating());
    }
}
class TvShow extends Stream{
    float rating;
    Set<Movie> episodes;

    public TvShow(String name, String[] genre, Set<Movie> episodes) {
        super(name, genre);
        this.episodes = episodes;
    }
    @Override
    double getRating() {
        double sum=0.0;
        int count=0;
        for(Movie mov:episodes){
            if(count>2){
                break;
            }
            sum+=mov.getRating();
            count++;
        }
        return sum/3.0;
    }
    @Override
    public String toString() {
        return String.format("TV Show %s %.4f (%d episodes)",name,getRating(),episodes.size());
    }
}
