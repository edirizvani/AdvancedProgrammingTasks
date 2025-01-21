package midterm2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class EventCalendarTest {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        int year = scanner.nextInt();
        scanner.nextLine();
        EventCalendar eventCalendar = new EventCalendar(year);
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            String name = parts[0];
            String location = parts[1];
            Date date = df.parse(parts[2]);
            try {
                eventCalendar.addEvent(name, location, date);
            } catch (WrongDateException e) {
                System.out.println(e.getMessage());
            }
        }
        Date date = df.parse(scanner.nextLine());
        eventCalendar.listEvents(date);
        eventCalendar.listByMonth();
    }
}

// vashiot kod ovde
class WrongDateException extends Exception {


    public WrongDateException(String message) {
        super(message);
    }
}
class EventCalendar {
    int year;
    Map<Integer, Set<Event>> events;


    Comparator<Event> eventComparator=Comparator.comparing(Event::getDate).thenComparing(Event::getName);
    public EventCalendar(int year) {
        this.year = year;
        events = new TreeMap<>();

    }
    private LocalDateTime getLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    public void addEvent(String name, String location, Date date) throws WrongDateException {
        LocalDateTime dateTime = getLocalDateTime(date);
        if(dateTime.getYear()!=this.year){
            throw new WrongDateException(String.format("Wrong date: %s",date.toString()));
        }
            events.putIfAbsent(dateTime.getMonthValue(), new TreeSet<>(eventComparator));
        events.get(dateTime.getMonthValue()).add(new Event(name,location,dateTime,date));


    }
    public void listEvents(Date date){
        LocalDateTime dateTime = getLocalDateTime(date);
        if(events.containsKey(dateTime.getMonthValue())){
            Set<Event> EVENTS=events.get(dateTime.getMonthValue()).stream().filter(event-> event.date.getDayOfMonth()==dateTime.getDayOfMonth()).collect(Collectors.toCollection(()->new TreeSet<>(eventComparator)));
            if(EVENTS!=null){
                if(EVENTS.isEmpty()){
                    System.out.println("No events on this day!");
                }else{
                    EVENTS.forEach(event->{
                        System.out.println(event);
                    });;
                }
            }
        }else{
            System.out.println("No events on this day!");
        }
    }
    public void listByMonth(){
        for (int i = 1; i <= 12; i++) {
            int j=0;
            if(events.get(i)!=null){
                j=events.get(i).size();
            }
            System.out.println(i+" : "+j);
        }
    }

}
class Event{

    String name;
    String location;
    LocalDateTime date;
    Date date1;

    public Event(String name, String location, LocalDateTime date,Date date1) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.date1 = date1;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd MMM, YYY HH:mm");
        return String.format("%s at %s, %s", df.format(date1), location, name);
    }
}

