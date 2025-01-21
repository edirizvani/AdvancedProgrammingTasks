package midterm2;

import org.w3c.dom.ls.LSOutput;

import java.util.*;
class DurationConverter {
    public static String convert(int duration) {
        int hours = duration / 60;
        duration %= 60;
        if(hours < 24) {
            return String.format("%02d:%02d", hours, duration);
        }else{
            int days=0;
            while (hours >= 24) {
                hours -= 24;
                days++;
            }
            return String.format("%02d:%02d +%dd", hours, duration,days);
        }
    }
}
class Flight{
    String from; String to; int time; int duration;

    public Flight(String from, String to, int time, int duration) {
        this.from = from;
        this.to = to;
        this.time = time;
        this.duration = duration;
    }

    public String getTo() {
        return to;
    }

    public int getDuration() {
        return duration;
    }

    public int getTime() {
        return time;
    }

    @Override
    public String toString() {
        int hours=duration/60;
        int minutes = duration%60;
        return String.format("%s-%s %s-%s %s",from,to,DurationConverter.convert(time),DurationConverter.convert(time+duration),String.format("%dh%02dm", hours, minutes));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return time == flight.time && duration == flight.duration && Objects.equals(from, flight.from) && Objects.equals(to, flight.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, time, duration);
    }
}

class Airport{
    String name;
    String country;
    String code;
    int passengers;

    public Airport(String name, String country, String code, int passengers) {
        this.name = name;
        this.country = country;
        this.code = code;
        this.passengers = passengers;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)\n%s\n%d", name, code, country, passengers);
    }
}


class Airports {
    Map<String, Airport> airportMap;
    Map<String,TreeSet<Flight>> airportsToMap;
    Map<String,TreeSet<Flight>> airportsFromMap;



    public Airports() {

        this.airportsToMap = new TreeMap<>();
        this.airportsFromMap = new TreeMap<>();
        airportMap = new HashMap<>();
    }

    public void addAirport(String name, String country, String code, int passengers){
        Airport airport = new Airport(name, country, code, passengers);
        airportMap.put(airport.code, airport);
    }
    public static Comparator<Flight> comparatorFrom= Comparator.comparing(Flight::getTo).thenComparing(Flight::getTime);
    public static Comparator<Flight> comparatorTo= Comparator.comparing(Flight::getTime).thenComparing(Flight::hashCode);
    public void addFlights(String fromCode, String toCode, int time, int duration){

        airportsFromMap.computeIfAbsent(fromCode, k -> new TreeSet<>(comparatorFrom));
        airportsFromMap.get(fromCode).add(new Flight(fromCode, toCode, time, duration));


        airportsToMap.computeIfAbsent(toCode, k -> new TreeSet<>(comparatorTo));
        airportsToMap.get(toCode).add(new Flight(fromCode, toCode, time, duration));

    }
    public void showFlightsFromAirport(String code){
        System.out.println(airportMap.get(code));
        int i=1;
        for (Flight flight : airportsFromMap.get(code)){
            System.out.println(i+". "+flight.toString());
            i++;
        }

    }
    public void showDirectFlightsFromTo(String from, String to){
        String noFlights=String.format("No flights from %s to %s",from,to);
            airportsFromMap.get(from).stream().filter(f -> f.getTo().equals(to)).findAny().ifPresentOrElse(System.out::println, ()-> {
                System.out.println(noFlights);
            });
    }
    public void showDirectFlightsTo(String to){
        airportsToMap.get(to).forEach(System.out::println);
    }
}





public class AirportsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Airports airports = new Airports();
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] codes = new String[n];
        for (int i = 0; i < n; ++i) {
            String al = scanner.nextLine();
            String[] parts = al.split(";");
            airports.addAirport(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
            codes[i] = parts[2];
        }
        int nn = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < nn; ++i) {
            String fl = scanner.nextLine();
            String[] parts = fl.split(";");
            airports.addFlights(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
        }
        int f = scanner.nextInt();
        int t = scanner.nextInt();
        String from = codes[f];
        String to = codes[t];
        System.out.printf("===== FLIGHTS FROM %S =====\n", from);
        airports.showFlightsFromAirport(from);
        System.out.printf("===== DIRECT FLIGHTS FROM %S TO %S =====\n", from, to);
        airports.showDirectFlightsFromTo(from, to);
        t += 5;
        t = t % n;
        to = codes[t];
        System.out.printf("===== DIRECT FLIGHTS TO %S =====\n", to);
        airports.showDirectFlightsTo(to);
    }
}

// vashiot kod ovde

