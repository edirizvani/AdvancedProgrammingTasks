package midterm2;

import java.util.*;
import java.util.stream.Collectors;

public class PayRollSystem2 {

    public static void main(String[] args) {

        Map<String, Double> hourlyRateByLevel = new LinkedHashMap<>();
        Map<String, Double> ticketRateByLevel = new LinkedHashMap<>();
        for (int i = 1; i <= 10; i++) {
            hourlyRateByLevel.put("level" + i, 11 + i * 2.2);
            ticketRateByLevel.put("level" + i, 5.5 + i * 2.5);
        }

        Scanner sc = new Scanner(System.in);

        int employeesCount = Integer.parseInt(sc.nextLine());

        PayrollSystem ps = new PayrollSystem(hourlyRateByLevel, ticketRateByLevel);
        Employee emp = null;
        for (int i = 0; i < employeesCount; i++) {
            try {
                emp = ps.createEmployee(sc.nextLine());
            } catch (BonusNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        }

        int testCase = Integer.parseInt(sc.nextLine());

        switch (testCase) {
            case 1: //Testing createEmployee
                if (emp != null)
                    System.out.println(emp);
                break;
            case 2: //Testing getOvertimeSalaryForLevels()
                ps.getOvertimeSalaryForLevels().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Overtime salary: %.2f\n", level, overtimeSalary);
                });
                break;
            case 3: //Testing printStatisticsForOvertimeSalary()
                ps.printStatisticsForOvertimeSalary();
                break;
            case 4: //Testing ticketsDoneByLevel
                ps.ticketsDoneByLevel().forEach((level, overtimeSalary) -> {
                    System.out.printf("Level: %s Tickets by level: %d\n", level, overtimeSalary);
                });
                break;
            case 5: //Testing getFirstNEmployeesByBonus (int n)
                ps.getFirstNEmployeesByBonus(Integer.parseInt(sc.nextLine())).forEach(System.out::println);
                break;
        }

    }
}
class PayrollSystem{
    List<Employee> employees;
    Map<String,Double> hourlyRateByLevel;
    Map<String,Double> ticketRateByLevel;

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }

    public Employee createEmployee(String s) throws BonusNotAllowedException {
        Employee e = BuilderEmployee.buildEmployee(s.split(";"),hourlyRateByLevel,ticketRateByLevel);
        employees.add(e);
        return e;
    }
//    Map<String, Double> getOvertimeSalaryForLevels (){
//        //groupByLevels and the amount of overtime that the company payed for that employee
//        return employees.stream().filter(employee -> employee.getOvertimeSalaryForEmplyee()!=-1).collect(Collectors.groupingBy(Employee::getLevel,Collectors.summingDouble(Employee::getOvertimeSalaryForEmplyee)));
//    }
    Map<String, Double> getOvertimeSalaryForLevels() {
        Map<String, Double> result = employees.stream().collect(Collectors.groupingBy(
                Employee::getLevel,
                Collectors.summingDouble(Employee::getOvertimeSalaryForEmplyee)
        ));

        List<String> keysWithZeros = result.keySet().stream().filter(key -> result.get(key) == -1).collect(Collectors.toList());

        keysWithZeros.forEach(result::remove);

        return result;
    }
//    void printStatisticsForOvertimeSalary (){
//       DoubleSummaryStatistics dss= employees.stream()
//               .filter(e->e.getOvertimeSalaryForEmplyee()!=1)
//               .mapToDouble(employee->employee.getOvertimeSalaryForEmplyee())
//               .summaryStatistics();
//        System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f", dss.getMin(), dss.getAverage(), dss.getMax(), dss.getSum());
//    }
void printStatisticsForOvertimeSalary() {
    DoubleSummaryStatistics dss = employees.stream()
            .filter(e -> e.getOvertimeSalaryForEmplyee() != -1)
            .mapToDouble(Employee::getOvertimeSalaryForEmplyee)
            .summaryStatistics();

    System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f", dss.getMin(), dss.getAverage(), dss.getMax(), dss.getSum());
}
    Map<String, Integer> ticketsDoneByLevel(){
       return employees.stream().filter(e->e.getPoints()!=-1).collect(Collectors.groupingBy(Employee::getLevel,Collectors.summingInt(Employee::getNumTickets)));
    }
    static Comparator<Employee> employeeComparator=Comparator.comparing(Employee::getBonus).reversed();
    Collection<Employee> getFirstNEmployeesByBonus (int n){
       return employees.stream().sorted(employeeComparator).limit(n).collect(Collectors.toList());

    }
}
class BuilderEmployee{
    static Employee buildEmployee(String[] info,Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) throws BonusNotAllowedException {

        String[] bonus = info[info.length-1].split("\\s+");
        Employee e=null;
        if(info[0].equals("H")){
            e=new HourlyEmployee(info[1],info[2],hourlyRateByLevel.get(info[2]),Double.parseDouble(bonus[0]));
        }else{
            List<Integer> tickets=new ArrayList<>();
            for (int i = 3; i <info.length -1; i++) {
                tickets.add(Integer.parseInt(info[i]));
            }
            tickets.add(Integer.parseInt(bonus[0]));
            e=new FreelanceEmplayee(info[1],info[2],ticketRateByLevel.get(info[2]),tickets);
        }
       if(bonus.length>1){
           if(bonus[1].contains("%")){
               double bonus_double=Double.parseDouble(bonus[1].replace("%",""));
               if(bonus_double>20.0){
                   throw new BonusNotAllowedException(String.format("Bonus of %.2f%% is not allowed",bonus_double));
               }
                e=new PercentageBonusEmployee(e,bonus_double);
           }else{
               double bonus_double=Double.parseDouble(bonus[1]);
               if(bonus_double>1000){
                   throw new BonusNotAllowedException(String.format("Bonus of %.0f$ is not allowed",bonus_double));
               }
                e=new FixedBonusEmployee(e,bonus_double);
           }
       }
       return e;
    }
}
class  BonusNotAllowedException extends Exception {
    public BonusNotAllowedException(String message) {
        super(message);
    }
}
interface Employee{
    double getOvertimeSalaryForEmplyee();
    String getLevel();
    double getBonus();
    double getSalary();
    int getPoints();
    int getNumTickets();
    void updateBonus(double amount);
}

abstract class BaseEmployee implements Employee {
    String id;
    String level;
    double rate;
    double updatedBonus;

    public BaseEmployee(String id, String level, double rate) {
        this.id = id;
        this.level = level;
        this.rate = rate;
        updatedBonus=0.0;
    }
    public String getLevel() {
        return level;
    }

    @Override
    public int getPoints() {
        return -1;
    }

    @Override
    public int getNumTickets() {
        return -1;
    }
    @Override
    public double getBonus() {
        return -1;
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f",id,level,getSalary()+updatedBonus);

    }
    @Override
    public void updateBonus(double amount){
        updatedBonus+=amount;
    }
}
class HourlyEmployee extends BaseEmployee {
    double hours;
    double overtime;
    double regular;

    public HourlyEmployee(String id, String level, double rate, double hours) {
        super(id, level, rate);
        this.hours = hours;
        this.overtime = Math.max(0,hours-40.0);
        this.regular = hours-overtime;
    }


    @Override
    public double getOvertimeSalaryForEmplyee() {
        return overtime*rate*1.5;
    }


    @Override
    public String toString() {
        return super.toString()+String.format(" Regular hours: %.2f Overtime hours: %.2f",regular,overtime);
    }

    @Override
    public double getSalary() {
        return regular*rate+getOvertimeSalaryForEmplyee();
    }
}
class FreelanceEmplayee extends BaseEmployee {
    List<Integer> tickets;

    public FreelanceEmplayee(String id, String level, double rate, List<Integer> tickets) {
        super(id, level, rate);
        this.tickets = tickets;
    }
        @Override
    public String toString() {
        return super.toString()+ String.format(" Tickets count: %d Tickets points: %d",tickets.size(),getPoints());
    }

    @Override
    public int getPoints() {
        return tickets.stream().mapToInt(Integer::intValue).sum();
    }
    public double getOvertimeSalaryForEmplyee() {
        return -1;
    }
    @Override
    public double getSalary() {
         return tickets.stream().mapToInt(point->point).sum()*rate;
    }
    @Override
    public int getNumTickets() {
        return tickets.size();
    }
}
abstract class BonusDecorator implements Employee {
    Employee e;

    public BonusDecorator(Employee e) {
        this.e = e;
    }
    public double getOvertimeSalaryForEmplyee() {
        return e.getOvertimeSalaryForEmplyee();
    }

    @Override
    public String getLevel() {
        return e.getLevel();
    }


    @Override
    public int getPoints() {
        return e.getPoints();
    }
    @Override
    public int getNumTickets() {
        return e.getNumTickets();
    }

    @Override
    public void updateBonus(double amount) {
        e.updateBonus(amount);
    }

    @Override
    public double getSalary() {
        return e.getSalary()+e.getBonus();
    }

    @Override
    public String toString() {
        return e.toString()+String.format(" Bonus: %.2f",getBonus());
    }
}
class PercentageBonusEmployee  extends BonusDecorator {
    double percentage;
    double bonus;

    public PercentageBonusEmployee(Employee e, double percentage) {
        super(e);
        this.percentage = percentage/100.0;
        bonus = this.percentage*e.getSalary();
        super.updateBonus(bonus);
    }

    @Override
    public double getBonus() {
        return bonus;
    }
}
class FixedBonusEmployee  extends BonusDecorator {
    double amount;

    public FixedBonusEmployee(Employee e, double amount) {
        super(e);
        this.amount = amount;
        super.updateBonus(amount);
    }

    @Override
    public double getBonus() {
        return amount;
    }
}
