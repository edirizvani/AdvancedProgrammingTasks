//package LAB3;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class TimeTableTest {
//
//    public static void main(String[] args) {
//        TimeTable timeTable = new TimeTable();
//        try {
//            timeTable.readTimes(System.in);
//        } catch (UnsupportedFormatException e) {
//            System.out.println("UnsupportedFormatException: " + e.getMessage());
//        } catch (InvalidTimeException e) {
//            System.out.println("InvalidTimeException: " + e.getMessage());
//        }
//        System.out.println("24 HOUR FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
//        System.out.println("AM/PM FORMAT");
//        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
//    }
//
//}
//
//enum TimeFormat {
//    FORMAT_24, FORMAT_AMPM
//}
//class UnsupportedFormatException extends Exception{
//    public UnsupportedFormatException(String s) {
//        super(s);
//    }
//}
//class InvalidTimeException extends Exception{
//    public InvalidTimeException(String s) {
//        super(s);
//    }
//}
//
//class TimeTable{
//    Set<Time> times;
//    TimeTable(){
//        times=new TreeSet<>();
//    }
//
//    void readTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidTimeException {
//        Scanner sc=new Scanner(new InputStreamReader(inputStream));
//
//        while (sc.hasNextLine()){
//            String time=sc.nextLine();
//            String[] parts=time.split(" ");
//            for(String p:parts){
//                Time time1=new Time(p);
//                times.add(time1);
//            }
//
//        }
//    }
//
//    void writeTimes(OutputStream outputStream, TimeFormat format){
//        PrintWriter pr=new PrintWriter(outputStream);
//        Iterator<Time> iterator=times.iterator();
//        while (iterator.hasNext()){
//            Time t=iterator.next();
//            if(format==TimeFormat.FORMAT_AMPM){
//                pr.println(t.print12());
//            }else{
//                pr.println(t.toString());
//            }
//
//        }
//        pr.flush();
//    }
//
//}
//class Time implements Comparable<Time> {
//
//
//    int hour;
//    int minute;
//
//    public Time(String time) throws UnsupportedFormatException, InvalidTimeException {
//        String[] parts=time.split("\\.");
//        if(parts.length==1){
//           parts=time.split(":");
//        }
//        if(parts.length==1){
//            throw new UnsupportedFormatException(time);
//        }
//        int h=Integer.parseInt(parts[0]);
//        int m=Integer.parseInt(parts[1]);
//        if(!createTime(h,m)){
//            throw new InvalidTimeException(time);
//        }
//        hour=h;
//        minute=m;
//
//    }
//    boolean createTime(int hour, int minutes) {
//        if(hour<0 || hour>23 || minutes<0 || minutes>59){
//            return false;
//        }
//        return true;
//    }
//    String print12(){
//            int h=hour;
//            String part="AM";
//            if(h==0){
//                h+=12;
//            }else if(h==12){
//                part="PM";
//            }else if(h>12){
//                h-=12;
//                part="PM";
//            }
//         return String.format("%2d:%02d %s",h,minute,part);
//        }
//
//    @Override
//    public String toString() {
//        return String.format("%2d:%02d",hour,minute);
//    }
//
//    @Override
//    public int compareTo(Time o) {
//        if(this.hour>o.hour){
//            return 1;
//        }else if(this.hour<o.hour){
//            return -1;
//        }else{
//            if(this.minute>o.minute){
//                return 1;
//            } else if (this.minute<o.minute) {
//                return -1;
//            }else{
//                return 0;
//            }
//        }
//    }
//}