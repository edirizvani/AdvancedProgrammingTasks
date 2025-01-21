package midterm2;

import java.io.*;
import java.util.*;



/**
 * I partial exam 2016
 */
class Temperature {
    boolean type; //0 for celcius /1 for fareneit
    double temperature;

    public Temperature(boolean type, double temperature) {
        this.type = type;
        this.temperature = temperature;
    }

    public double getTemperature(boolean type) {
        return  type ? this.getTemperatureFarenheit(): this.getTemperatureCelsius();
    }

    double getTemperatureFarenheit() {
        return type? temperature: temperature * 1.8 + 32;
    }
    double getTemperatureCelsius() {
        return type? ((temperature-32)*5)/9: temperature;
    }

}
class FactoryTemperature {
    static List<Temperature> readTemperature(String[] info){
        List<Temperature> list = new ArrayList<>();
        for (int i = 1; i <info.length ; i++) {
            char type = info[i].charAt(info[i].length()-1);
            if(type == 'F'){
                list.add(new Temperature(true, Double.parseDouble(info[i].substring(0,info[i].length()-1))));
            }else{
                list.add( new Temperature(false, Double.parseDouble(info[i].substring(0,info[i].length()-1))));
            }
        }
        return list;
    }
}

class DailyTemperatures {
    Map<Integer, List<Temperature>> days_temperature;

    public DailyTemperatures() {
        days_temperature = new TreeMap<>();
    }
    void readTemperatures(InputStream inputStream){
        Scanner scanner = new Scanner(new InputStreamReader(inputStream));
        while(scanner.hasNextLine()){
            String[] words = scanner.nextLine().split(" ");
            days_temperature.put(Integer.parseInt(words[0]),FactoryTemperature.readTemperature(words));
        }
    }
    void writeDailyStats(OutputStream outputStream, char scale){
        PrintWriter printWriter = new PrintWriter(outputStream);
        boolean type= scale == 'F';
        days_temperature.forEach((k,v)->{
            DoubleSummaryStatistics dss=v.stream().mapToDouble(temperature-> temperature.getTemperature(type)).summaryStatistics();
            printWriter.printf("%3d: Count: %3d Min: %6.2f%c Max: %6.2f%c Avg: %6.2f%c\n",k,dss.getCount(),dss.getMin(),scale,dss.getMax(),scale,dss.getAverage(),scale);
        });
        printWriter.flush();
    }

}












public class DailyTemperatureTest {
    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();
        dailyTemperatures.readTemperatures(System.in);
        System.out.println("=== Daily temperatures in Celsius (C) ===");
        dailyTemperatures.writeDailyStats(System.out, 'C');
        System.out.println("=== Daily temperatures in Fahrenheit (F) ===");
        dailyTemperatures.writeDailyStats(System.out, 'F');
    }
}

// Vashiot kod ovde