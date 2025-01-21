//package LAB3;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Duration;
//import java.util.*;
//
//public class WeatherStationTest {
//    public static void main(String[] args) throws ParseException {
//        Scanner scanner = new Scanner(System.in);
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        WeatherStation ws = new WeatherStation(n);
//        while (true) {
//            String line = scanner.nextLine();
//            if (line.equals("=====")) {
//                break;
//            }
//            String[] parts = line.split(" ");
//            float temp = Float.parseFloat(parts[0]);
//            float wind = Float.parseFloat(parts[1]);
//            float hum = Float.parseFloat(parts[2]);
//            float vis = Float.parseFloat(parts[3]);
//            line = scanner.nextLine();
//            Date date = df.parse(line);
//            ws.addMeasurment(temp, wind, hum, vis, date);
//        }
//        String line = scanner.nextLine();
//        Date from = df.parse(line);
//        line = scanner.nextLine();
//        Date to = df.parse(line);
//        scanner.close();
//        System.out.println(ws.total());
//        try {
//            ws.status(from, to);
//        } catch (RuntimeException e) {
//            System.out.println(e);
//        }
//    }
//}
//class WeatherStation{
//    int days;
//    Set<Measurement> measurements;
//
//
//    public WeatherStation(int days) {
//        this.days = days;
//        this.measurements = new TreeSet<>();
//
//    }
//    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date){
//
//        Measurement neww=new Measurement(temperature,wind,humidity,visibility,date);
//        Iterator<Measurement> iterator=measurements.iterator();
//
//        while (iterator.hasNext()) {
//            Measurement mes = iterator.next();
//            Duration dur = Duration.between(mes.getDate().toInstant(), neww.getDate().toInstant());
//            if (dur.toMinutes() < 2.5) {
//                return;
//            }
//            long d = Math.abs(mes.getDate().getTime() - neww.getDate().getTime());
//            long time = (long) this.days * 24 * 60 * 60 * 1000;
//            if (d > time) {
//                iterator.remove();
//            }
//        }
//        measurements.add(neww);
//    }
//    public int total(){
//        return measurements.size();
//    }
//    public void status(Date from, Date to){
//        int count=0;
//        float sum_temp=0;
//        for (Measurement mes:measurements){
//            if(!mes.getDate().before(from) && !mes.getDate().after(to)){
//                System.out.println(mes);
//                count++;
//                sum_temp+=mes.getTemperature();
//            }
//        }
//        if(count==0){
//            throw new RuntimeException();
//        }
//        System.out.printf("Average temperature: %.2f\n",sum_temp/count);
//    }
//}
//class Measurement implements Comparable<Measurement> {
//    private float temperature, wind, humidity,  visibility;
//    private Date date;
//
//    public Measurement(float temperature, float wind, float humidity, float visibility, Date date) {
//        this.temperature = temperature;
//        this.wind = wind;
//        this.humidity = humidity;
//        this.visibility = visibility;
//        this.date = date;
//    }
//
//    public float getTemperature() {
//        return temperature;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    @Override
//    public int compareTo(Measurement o) {
//        if(this.date.before(o.date)){
//            return -1;
//        }else {
//            return 1;
//        }
//    }
//
//    @Override
//    public String toString() {
//        String hello= date.toString().replaceAll("UTC","GMT");
//        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s",temperature,wind,humidity,visibility, hello);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Measurement that = (Measurement) o;
//        return Float.compare(that.temperature, temperature) == 0 && Float.compare(that.wind, wind) == 0 && Float.compare(that.humidity, humidity) == 0 && Float.compare(that.visibility, visibility) == 0 && Objects.equals(date, that.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(temperature, wind, humidity, visibility, date);
//    }
//}
//
//// vashiot kod ovde
