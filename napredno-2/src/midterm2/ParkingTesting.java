package midterm2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class DateUtil {
    public static long durationBetween(LocalDateTime start, LocalDateTime end) {
        return Duration.between(start, end).toMinutes();
    }
}

public class ParkingTesting {

    public static <K, V extends Comparable<V>> void printMapSortedByValue(Map<K, V> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(String.format("%s -> %s", entry.getKey().toString(), entry.getValue().toString())));

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int capacity = Integer.parseInt(sc.nextLine());

        Parking parking = new Parking(capacity);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equals("update")) {
                String registration = parts[1];
                String spot = parts[2];
                LocalDateTime timestamp = LocalDateTime.parse(parts[3]);
                boolean entrance = Boolean.parseBoolean(parts[4]);
                parking.update(registration, spot, timestamp, entrance);
            } else if (parts[0].equals("currentState")) {
                System.out.println("PARKING CURRENT STATE");
                parking.currentState();
            } else if (parts[0].equals("history")) {
                System.out.println("PARKING HISTORY");
                parking.history();
            } else if (parts[0].equals("carStatistics")) {
                System.out.println("CAR STATISTICS");
                printMapSortedByValue(parking.carStatistics());
            } else if (parts[0].equals("spotOccupancy")) {
                LocalDateTime start = LocalDateTime.parse(parts[1]);
                LocalDateTime end = LocalDateTime.parse(parts[2]);
                printMapSortedByValue(parking.spotOccupancy(start, end));
            }
        }
    }
}
class Parking {
    private int capacity;
    Map<String,Vehicle> vehicles;
    List<Vehicle> history;
    public Parking(int capacity) {
        this.capacity = capacity;
        vehicles = new HashMap<>();
        history = new ArrayList<>();
    }
    Comparator<Vehicle> compareVehicle1=Comparator.comparing(Vehicle::getIn_timestamp);
    void update(String registration, String spot, LocalDateTime timestamp, boolean entrance) {
        if(vehicles.containsKey(registration)) {
            Vehicle v= vehicles.remove(registration);
            v.changeState(entrance,timestamp);
            history.add(v);
        }else{
            vehicles.put(registration, new Vehicle(registration, spot, timestamp, entrance));
        }
    }
    void currentState(){
        List<Vehicle> vehicles_parked=vehicles.values().stream().filter(vehicle -> vehicle.state.getState()==true).sorted(compareVehicle1.reversed()).collect(Collectors.toList());
        float percentage=(float)vehicles_parked.size()/capacity;
        System.out.printf("Capacity filled: %.2f%%\n",percentage*100.0);
        vehicles_parked.forEach(vehicle->{
            System.out.println(vehicle.toString());
        });
    }
    void history(){
        history.sort(Comparator.comparing(Vehicle::getDuration).reversed());
        history.forEach(vehicle->{
            System.out.println(vehicle.toString());
        });
    }
    Map<String, Integer> carStatistics(){
          Map<String,Integer> carStatistic=history.stream().collect(Collectors.groupingBy(Vehicle::getRegistration,TreeMap::new,Collectors.collectingAndThen(Collectors.counting(),Long::intValue)));
          carStatistic.forEach((registration,count)->{
              if(vehicles.containsKey(registration)){
                  count+=1;
              }
          });
          return carStatistic;
    }
    Map<String, Double> spotOccupancy(LocalDateTime start, LocalDateTime end) {
        long minutes = Duration.between(start, end).toMinutes();

        // Include vehicles currently in the parking
        List<Vehicle> allVehicles = new ArrayList<>(history);
        allVehicles.addAll(vehicles.values()); // Add active vehicles

        return allVehicles.stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getSpot,
                        TreeMap::new,
                        Collectors.summingDouble(vehicle ->
                                ((double) vehicle.getDurationWithinRange(start, end) / minutes) * 100.0
                        )
                ));
    }

}
class Vehicle {
    private String registration;
    String spot;
    LocalDateTime in_timestamp;
    LocalDateTime out_timestamp;
    State state;

    public Vehicle(String registration, String spot, LocalDateTime in_timestamp, boolean entry) {
        this.registration = registration;
        this.spot = spot;
        this.in_timestamp = in_timestamp;
        this.state = new ParkedCar();
    }
    void changeState(boolean entry,LocalDateTime timestamp) {
        if (entry) {
            this.state = new ParkedCar(); // Parked
            this.in_timestamp = timestamp; // Reset in time
        } else {
            this.state = new OutsideCar(); // Outside
            this.out_timestamp = timestamp; // Set out time
        }
    }

    public String getSpot() {
        return spot;
    }

    public LocalDateTime getIn_timestamp() {
        return in_timestamp;
    }

    public String getRegistration() {
        return registration;
    }

    @Override
    public String toString() {
        if(state.getState()==true){
            return String.format("Registration number: %s Spot: %s Start timestamp: %s", registration, spot, in_timestamp, out_timestamp);
        }
        return String.format("Registration number: %s Spot: %s Start timestamp: %s End timestamp: %s Duration in minutes: %d", registration, spot, in_timestamp, out_timestamp,getDuration());
    }
    int getDuration() {
        return (int)ChronoUnit.MINUTES.between(in_timestamp,out_timestamp);
    }
    long getDurationWithinRange(LocalDateTime start, LocalDateTime end) {
        LocalDateTime effectiveStart = in_timestamp.isAfter(start) ? in_timestamp : start;
        LocalDateTime effectiveEnd = (out_timestamp == null || out_timestamp.isAfter(end)) ? end : out_timestamp;
        if (effectiveStart.isBefore(effectiveEnd)) {
            return Duration.between(effectiveStart, effectiveEnd).toMinutes();
        }
        return 0;
    }

}
interface State {
    boolean getState();
}
class ParkedCar implements State {

    @Override
    public boolean getState() {
        return true;
    }
}
class OutsideCar implements State {

    @Override
    public boolean getState() {
        return false;
    }
}

