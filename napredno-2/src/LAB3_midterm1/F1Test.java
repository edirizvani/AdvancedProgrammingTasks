//package LAB3;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.*;
//
//public class F1Test {
//
//    public static void main(String[] args) {
//        F1Race f1Race = new F1Race();
//        f1Race.readResults(System.in);
//        f1Race.printSorted(System.out);
//    }
//
//}
//
//class F1Race {
//    // vashiot kod ovde
//    Set<RaceDriver> drivers;
//
//    public F1Race() {
//        this.drivers = new TreeSet<>();
//    }
//    void readResults(InputStream inputStream){
//        Scanner sc=new Scanner(new InputStreamReader(inputStream));
//        while(sc.hasNextLine()){
//            String[] info=sc.nextLine().split(" ");
//            drivers.add(new RaceDriver(info[0],info[1],info[2],info[3]));
//        }
//    }
//    void printSorted(OutputStream outputStream){
//        PrintWriter pr=new PrintWriter(outputStream);
//       int i=1;
//        for (RaceDriver rc:drivers){
//            pr.println(String.format("%d. %s",i++,rc));
//        }
//        pr.flush();
//    }
//}
//
//class RaceDriver implements Comparable<RaceDriver> {
//    String name;
//    ArrayList<Lap> laps;
//
//
//    public RaceDriver(String name, String lap1,String lap2,String lap3) {
//        laps=new ArrayList<>();
//        this.name = name;
//        construct(lap1);
//        construct(lap2);
//        construct(lap3);
//    }
//    void construct(String lap){
//        String[] times=lap.split(":");
//        laps.add(new Lap(Integer.parseInt(times[0]),Integer.parseInt(times[1]),Integer.parseInt(times[2])));
//    }
////    void allLapsTime(){
////        laptime=new Lap(0,0,0);
////        for (Lap lap:laps){
////            laptime.add(lap);
////        }
////    }
//    Lap bestlapTime(){
//        return laps.stream().min(Lap::compareTo).orElse(null);
//    }
//
//
//    @Override
//    public int compareTo(RaceDriver o) {
//        return this.bestlapTime().compareTo(o.bestlapTime());
//    }
//
//    @Override
//    public String toString() {
//
//        return String.format("%-10s%10s",name,bestlapTime());
//    }
//}
//class Lap implements Comparable<Lap>{
//    int min;
//    int sec;
//    int ms;
//
//    public Lap(int min, int sec, int ms) {
//        this.min = min;
//        this.sec = sec;
//        this.ms = ms;
//    }
//
//    @Override
//    public int compareTo(Lap o) {
//        if(this.min<o.min){
//            return -1;
//        }else if(this.min>o.min){
//            return 1;
//        }
//        if(this.sec<o.sec){
//            return -1;
//        }else if(this.sec>o.sec){
//            return 1;
//        }
//        if(this.ms<o.ms){
//            return -1;
//        }else if(this.ms>o.ms){
//            return 1;
//        }
//        return 0;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%d:%02d:%03d",min,sec,ms);
//    }
//}