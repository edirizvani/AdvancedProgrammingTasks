//import javax.print.DocFlavor;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class LabExercisesTest {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        LabExercises labExercises = new LabExercises();
//        while (sc.hasNextLine()) {
//            String input = sc.nextLine();
//            String[] parts = input.split("\\s+");
//            String index = parts[0];
//            List<Integer> points = Arrays.stream(parts).skip(1)
//                    .mapToInt(Integer::parseInt)
//                    .boxed()
//                    .collect(Collectors.toList());
//
//            labExercises.addStudent(new Student(index, points));
//        }
//
//        System.out.println("===printByAveragePoints (ascending)===");
//        labExercises.printByAveragePoints(true, 100);
//        System.out.println("===printByAveragePoints (descending)===");
//        labExercises.printByAveragePoints(false, 100);
//        System.out.println("===failed students===");
//        labExercises.failedStudents().forEach(System.out::println);
//        System.out.println("===statistics by year");
//        labExercises.getStatisticsByYear().entrySet().stream()
//                .map(entry -> String.format("%d : %.2f", entry.getKey(), entry.getValue()))
//                .forEach(System.out::println);
//
//    }
//}
//class LabExercises{
//    List<Student> students;
//
//    public LabExercises() {
//        this.students =  new ArrayList<>();
//    }
//    public void addStudent (Student student){
//        students.add(student);
//    }
//    Comparator<Student> comparator1=Comparator.comparing(Student::getAverage).thenComparing(Student::getIndex);
//    public void printByAveragePoints (boolean ascending, int n){
//        Comparator<Student> comparator=null;
//        if(ascending){
//            comparator=comparator1;
//        }else {
//            comparator=comparator1.reversed();
//        }
//        students.stream().sorted(comparator).limit(n).forEach(System.out::println);
//    }
//    public List<Student> failedStudents (){
//       return students.stream().filter(student -> student.points.size()<8).sorted(Comparator.comparing(Student::getIndex)).collect(Collectors.toList());
//    }
//    public Map<Integer,Double> getStatisticsByYear(){
//       return students.stream().filter(student -> student.points.size()>7).collect(Collectors.groupingBy(Student::getYearOfStudent,Collectors.averagingDouble(Student::getAverage)));
//    }
//}
//class Student{
//    String index;
//    List<Integer> points;
//
//    public Student(String index, List<Integer> points) {
//        this.index = index;
//        this.points = points;
//    }
//    double getAverage(){
//        return points.stream().mapToDouble(Integer::doubleValue).sum()/10;
//    }
//    private String hasPotpis(){
//        if(points.size()>7){
//            return "YES";
//        }else{
//            return "NO";
//        }
//    }
//
//    public String getIndex() {
//        return index;
//    }
//
//    @Override
//    public String toString() {
//        if(points.size()==10){
//            return String.format("%s %s %.2f",this.index,hasPotpis(),this.getAverage());
//        }else{
//            return String.format("%s %s %.2f",this.index,hasPotpis(),this.getAverage());
//        }
//    }
//    int getYearOfStudent(){
//        return 20-Integer.parseInt(index.substring(0,2));
//    }
//}
//
