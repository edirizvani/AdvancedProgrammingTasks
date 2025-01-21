////package LAB3;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class SubtitlesTest {
//    public static void main(String[] args) {
//        Subtitles subtitles = new Subtitles();
//        int n = subtitles.loadSubtitles(System.in);
//        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
//        subtitles.print();
//        int shift = n * 37;
//        shift = (shift % 2 == 1) ? -shift : shift;
//        System.out.println(String.format("SHIFT FOR %d ms", shift));
//        subtitles.shift(shift);
//        System.out.println("+++++ SHIFTED SUBTITLES +++++");
//        subtitles.print();
//    }
//}
//
//// Вашиот код овде
//class Subtitles{
//    ArrayList<Subtitile> subtitles;
//
//    public Subtitles() {
//        this.subtitles = new ArrayList<>();
//    }
//    int loadSubtitles(InputStream inputStream){
//        Scanner sc=new Scanner(new InputStreamReader(inputStream));
//        int flag=0;
//        while (sc.hasNext()){
//            String line=sc.nextLine();
//            int real=Integer.parseInt(line);
//            String time=sc.nextLine();
//            String word=sc.nextLine();
//            if(!sc.hasNextLine()){
//                construct(time,real,word);
//                break;
//            }
//            String next_word=sc.nextLine();
//            if(next_word.length()>0){
//                word=word+"\n"+next_word;
//                if(!sc.hasNextLine()){
//                    construct(time,real,word);
//                    break;
//                }
//                sc.nextLine();
//            }
//            construct(time,real,word);
//        }
//        return subtitles.size();
//
//    }
//    void construct(String time,int real,String word){
//        String[] from_to=time.split(" --> ");
//        String[] from=from_to[0].split("[:|,]");
//
//        String[] to=from_to[1].split("[:|,]");
//        subtitles.add(new Subtitile(real,word,new Time(Integer.parseInt(from[0]),Integer.parseInt(from[1]),Integer.parseInt(from[2]),Integer.parseInt(from[3])),new Time(Integer.parseInt(to[0]),Integer.parseInt(to[1]),Integer.parseInt(to[2]),Integer.parseInt(to[3])) ));
//
//    }
//    void print(){
//        for (Subtitile sub:subtitles){
//            System.out.println(sub);
//        }
//    }
//    void shift(int ms){
//        for (Subtitile sub:subtitles){
//            sub.shift(ms);
//        }
//    }
//}
//
//class Subtitile{
//    int real_num;
//    String word;
//    Time from;
//    Time to;
//
//    public Subtitile(int real_num, String word, Time from, Time to) {
//        this.real_num = real_num;
//        this.word = word;
//        this.from = from;
//        this.to = to;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%d\n%s --> %s\n%s\n",real_num,from,to,word);
//    }
//    void shift(int ms){
//        from.shift(ms);
//        to.shift(ms);
//    }
//}
//class Time{
//
//    int hour;
//    int minutes;
//    int seconds;
//    int miliseconds;
//
//    public Time(int hour, int minutes, int seconds, int miliseconds) {
//        this.hour = hour;
//        this.minutes = minutes;
//        this.seconds = seconds;
//        this.miliseconds = miliseconds;
//    }
//    void shift(int ms){
//        int ms_here=(hour*60*60*1000)+(minutes*60*1000)+(seconds*1000)+miliseconds;
//        ms_here+=ms;
//
//        hour=ms_here/(1000*60*60);
//        ms_here=ms_here%(1000*60*60);
//
//        minutes=ms_here/(1000*60);
//        ms_here=ms_here%(1000*60);
//
//        seconds=ms_here/(1000);
//        miliseconds=ms_here%(1000);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%02d:%02d:%02d,%03d",hour,minutes,seconds,miliseconds);
//    }
//}
