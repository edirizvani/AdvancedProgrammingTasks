////package midterm2;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.util.*;
//import java.util.function.Supplier;
//import java.util.stream.Collectors;
//
//public class PayrollSystemTest {
//
//    public static void main(String[] args) {
//
//        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
//        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
//        for (int i = 1; i <= 10; i++) {
//            hourlyRateByLevel.put("level" + i, 10 + i * 2.2);
//            ticketRateByLevel.put("level" + i, 5 + i * 2.5);
//        }
//
//        PayrollSystem payrollSystem = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
//
//        System.out.println("READING OF THE EMPLOYEES DATA");
//        payrollSystem.readEmployees(System.in);
//
//        System.out.println("PRINTING EMPLOYEES BY LEVEL");
//        Set<String> levels = new LinkedHashSet<>();
//        for (int i=5;i<=10;i++) {
//            levels.add("level"+i);
//        }
//        Map<String, Set<Employee>> result = payrollSystem.printEmployeesByLevels(System.out, levels);
//        result.forEach((level, employees) -> {
//            System.out.println("LEVEL: "+ level);
//            System.out.println("Employees: ");
//            employees.forEach(System.out::println);
//            System.out.println("------------");
//        });
//
//
//    }
//}
//class PayrollSystem {
//    List<Employee> employees;
//    Map<String,Double> hourlyRateByLevel;
//    Map<String,Double> ticketRateByLevel;
//    PayrollSystem(Map<String,Double> hourlyRateByLevel, Map<String,Double> ticketRateByLevel){
//        this.hourlyRateByLevel = hourlyRateByLevel;
//        this.ticketRateByLevel = ticketRateByLevel;
//        employees = new ArrayList<>();
//    }
//    static Comparator<Employee> comparator = Comparator.comparing(Employee::getSalary).thenComparing(Employee::getLevel);
//
//    void readEmployees (InputStream is){
//        Scanner scanner = new Scanner(new InputStreamReader(is));
//        while (scanner.hasNextLine()) {
//            String[] lines = scanner.nextLine().split(";");
//            Employee e=null;
//            if(lines[0].equals("F")){
//                List<Integer> tickets = new ArrayList<>();
//                for (int i = 3; i < lines.length; i++) {
//                   tickets.add(Integer.parseInt(lines[i]));
//                }
//                e=new FreelanceEmployee('F',lines[1],lines[2],tickets,ticketRateByLevel.get(lines[2]));
//            }else if(lines[0].equals("H")){
//                e=new HourlyEmployee('F',lines[1],lines[2],Double.parseDouble(lines[3]),hourlyRateByLevel.get(lines[2]));
//            }
//            employees.add(e);
//        }
//    }
//    Map<String, Set<Employee>> printEmployeesByLevels (OutputStream os, Set<String> levels){
////        Map<String,Set<Employee>> map=new HashMap<>();
////        for (String level : levels) {
////            List<Employee> emp=employees.stream().filter(employee -> employee.level.equals(level)).collect(Collectors.toList());
////            if(emp.size()==0){
////                continue;
////            }
////            map.putIfAbsent(level, new TreeSet<>(comparator));
////            map.get(level).addAll(emp);
////        }
////        return map;
//        Map<String,Set<Employee>> map=employees.stream().collect(Collectors.groupingBy(Employee::getLevel,(Supplier<TreeMap<String,Set<Employee>>>) TreeMap::new,Collectors.toCollection(TreeSet::new)));
//        Set<String> keys = new HashSet<>(map.keySet());
//        keys.stream().filter(k->!levels.contains(k)).forEach(map::remove);
//        return map;
//
//    }
//
//}
//abstract class Employee implements Comparable<Employee>{
//    Character type;
//    String id;
//    String level;
//    double rate;
//
//    public Employee(Character type, String id, String level,Double rate) {
//        this.type = type;
//        this.id = id;
//        this.level = level;
//        this.rate = rate;
//    }
//
//    public abstract double getSalary();
//
//    public String getLevel() {
//        return level;
//    }
//    @Override
//    public int compareTo(Employee o) {
//        return Comparator.comparing(Employee::getSalary).thenComparing(Employee::getLevel).reversed().compare(this, o);
//    }
//}
//
//class HourlyEmployee extends Employee{
//    double hours;
//    double overtime;
//
//    public HourlyEmployee(Character type, String id, String level, double hours, Double rate) {
//        super(type, id, level,rate);
//        overtime = Math.max(0, hours - 40);
//        this.hours = hours - overtime;
//
//    }
//
//    @Override
//    public String toString() {
//
//        return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f",id,level,getSalary(),hours,overtime);
//    }
//
//    @Override
//    public double getSalary() {
//        return hours*rate+overtime*rate*1.5;
//    }
//}
//class FreelanceEmployee extends Employee{
//    List<Integer> points;
//
//    public FreelanceEmployee(Character type, String id, String level, List<Integer> points, Double rate) {
//        super(type, id, level, rate);
//        this.points = points;
//
//    }
//    @Override
//    public String toString() {
//        return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d",id,level,getSalary(),points.size(),points.stream().mapToInt(Integer::intValue).sum());
//    }
//
//    @Override
//    public double getSalary() {
//         return points.stream().mapToInt(point->point).sum()*rate;
//    }
//}