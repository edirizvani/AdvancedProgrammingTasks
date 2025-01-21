package LAB3_midterm1;

import java.io.*;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;


public class RaceTest {
    public static void main(String[] args) {
        TeamRace.findBestTeam(System.in, System.out);
    }
}

class TeamRace{
    Set<Racer> racers;

    public TeamRace() {
        this.racers = new TreeSet<>();
    }

    static void findBestTeam(InputStream is, OutputStream os){
        Scanner sc=new Scanner(new InputStreamReader(is));
        TeamRace race=new TeamRace();
        while (sc.hasNextLine()){
            String[] info=sc.nextLine().split(" ");
            String[] from=info[1].split(":");
            LocalTime start= LocalTime.of(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]));
            from =info[2].split(":");
            LocalTime end= LocalTime.of(Integer.parseInt(from[0]), Integer.parseInt(from[1]), Integer.parseInt(from[2]));
            race.racers.add(new Racer(info[0],start,end));
        }
        PrintWriter pr=new PrintWriter(os);
        int count=0;
        LocalTime time=LocalTime.of(0,0,0);
        for (Racer racer:race.racers){
            if(count>3){
                break;
            }
            time=Racer.sumTime(time,racer.getTime());
            pr.println(racer);
            count++;
        }
        pr.println(String.format("%02d:%02d:%02d",time.getHour(),time.getMinute(),time.getSecond()));
        pr.flush();
    }
}

class Racer implements Comparable<Racer>{
    String name;
    LocalTime start;
    LocalTime end;

    public Racer(String name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
    static LocalTime sumTime(LocalTime end,LocalTime start){
        long sec_end=(end.getHour()*60*60)+end.getSecond()+(end.getMinute()*60);
        long sec_from=(start.getHour()*60*60)+start.getSecond()+(start.getMinute()*60);
        int h,m,s;
        long total_sec=sec_end+sec_from;
        h=(int)total_sec/(60*60);
        total_sec%=(60*60);
        m=(int)total_sec/60;
        s=(int)total_sec%60;
        return LocalTime.of(h,m,s);
    }
    LocalTime getTime(){
        long sec_end=(end.getHour()*60*60)+end.getSecond()+(end.getMinute()*60);
        long sec_from=(start.getHour()*60*60)+start.getSecond()+(start.getMinute()*60);
        int h,m,s;
        long total_sec=sec_end-sec_from;
        h=(int)total_sec/(60*60);
        total_sec%=(60*60);
        m=(int)total_sec/60;
        s=(int)total_sec%60;
        return LocalTime.of(h,m,s);
    }

    @Override
    public int compareTo(Racer o) {
        if(this.getTime().isBefore(o.getTime())){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %02d:%02d:%02d",name,getTime().getHour(),getTime().getMinute(),getTime().getSecond());
    }
}

