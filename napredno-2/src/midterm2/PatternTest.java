//package midterm2;

import java.util.ArrayList;
import java.util.List;

public class PatternTest {
    public static void main(String args[]) {
        List<Song> listSongs = new ArrayList<Song>();
        listSongs.add(new Song("first-title", "first-artist"));
        listSongs.add(new Song("second-title", "second-artist"));
        listSongs.add(new Song("third-title", "third-artist"));
        listSongs.add(new Song("fourth-title", "fourth-artist"));
        listSongs.add(new Song("fifth-title", "fifth-artist"));
        MP3Player player = new MP3Player(listSongs);


        System.out.println(player.toString());
        System.out.println("First test");


        player.pressPlay();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressPlay();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Second test");


        player.pressStop();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressStop();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
        System.out.println("Third test");


        player.pressFWD();
        player.printCurrentSong();
        player.pressFWD();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressPlay();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressStop();
        player.printCurrentSong();

        player.pressFWD();
        player.printCurrentSong();
        player.pressREW();
        player.printCurrentSong();


        System.out.println(player.toString());
    }



}
 class Song {
    String title;
    String artist;

     public Song(String title, String artist) {
         this.title = title;
         this.artist = artist;
     }

     @Override
     public String toString() {
         return "Song{" +
                 "title=" + title  +
                 ", artist=" + artist  +
                 '}';
     }
 }
class MP3Player {
    private List<Song> songs;
    private int currentIndex;

    private State playState;
    private State stopState;
    private State pauseState;
    private State state;
    final void createStates(){
        playState=new playState(this);
        stopState=new stopState(this);
        pauseState=new pauseState(this);
        state=stopState;
    }
    public MP3Player(List<Song> songs) {
        this.songs = songs;
        this.currentIndex = 0;
        createStates();
    }
    void FWD(){
        System.out.println("Forward...");
        this.currentIndex=(currentIndex+1)%songs.size();
    }
    void REW(){
        System.out.println("Reward...");
        this.currentIndex=(currentIndex+songs.size()-1)%songs.size();
    }
    void printCurrentSong(){
        System.out.println(songs.get(currentIndex));
    }
    public List<Song> getSongs() {
        return songs;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }
    public void setStop() {
        System.out.println("Songs are stopped");
        this.currentIndex = 0;
        this.state = stopState;
    }
    public void setPause() {

        this.state = pauseState;
    }
    public void setPlay() {
        System.out.println("Song "+ currentIndex+ " is playing");
        this.state = playState;
    }

    public void pressPlay() {
        state.pressPlay();
    }

    public void pressStop() {
     state.pressStop();
    }

    public void pressFWD() {
        state.pressFWD();
    }

    public void pressREW() {
        state.pressREW();
    }

    @Override
    public String toString() {
        return "MP3Player{currentSong = " + currentIndex + ", songList = " + songs + "}";

    }
}
interface State{
    void pressPlay();
    void pressStop();
    void pressFWD();
    void pressREW();
}
abstract class AbstractState implements State{
    MP3Player mp3;

    public AbstractState(MP3Player mp3) {
        this.mp3 = mp3;
    }
}
class playState extends AbstractState{

    public playState(MP3Player mp3) {
        super(mp3);
    }

    @Override
    public void pressPlay() {
        System.out.println("Song is already playing");
    }



    @Override
    public void pressStop() {
        System.out.println("Song "+ mp3.getCurrentIndex()+ " is paused");
        mp3.setPause();
    }

    @Override
    public void pressFWD() {

        mp3.setPause();
        mp3.FWD();
    }

    @Override
    public void pressREW() {
        mp3.setPause();
        mp3.REW();
    }
}
class stopState extends AbstractState{

    public stopState(MP3Player mp3) {
        super(mp3);
    }

    @Override
    public void pressPlay() {
        mp3.setPlay();
    }


    @Override
    public void pressStop() {
        System.out.println("Songs are already stopped");
    }

    @Override
    public void pressFWD() {
        mp3.FWD();
    }

    @Override
    public void pressREW() {
        mp3.REW();
    }
}
class pauseState extends AbstractState{

    public pauseState(MP3Player mp3) {
        super(mp3);
    }

    @Override
    public void pressPlay() {
        mp3.setPlay();
    }
    @Override
    public void pressStop() {
        mp3.setStop();
    }

    @Override
    public void pressFWD() {
        mp3.FWD();
    }

    @Override
    public void pressREW() {
        mp3.REW();
    }
}
//Vasiot kod ovde