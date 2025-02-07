//package LAB3;
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//class Student {
//    String id;
//    List<Integer> grades;
//
//    public Student(String id, List<Integer> grades) {
//        this.id = id;
//        this.grades = grades;
//    }
//
//    public static Student create(String line) {
//        String[] parts = line.split("\\s+");
//        String id = parts[0];
//        List<Integer> grades = Arrays.stream(parts).skip(1).map(Integer::parseInt).collect(Collectors.toList());
//        return new Student(id, grades);
//    }
//
//    @Override
//    public String toString() {
//        return "Student{" +
//                "id='" + id + '\'' +
//                ", grades=" + grades +
//                '}';
//    }
//}
//
//public class RuleTester {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int testCase = Integer.parseInt(sc.nextLine());
//
//        if (testCase == 1) { //Test for String,Integer
//            List<Rule<String, Integer>> rules = new ArrayList<>();
//
//            /*
//            TODO: Add a rule where if the string contains the string "NP", the result would be index of the first occurrence of the string "NP"
//            * */
//            rules.add(new Rule<>(str->str.contains("NP"),str->str.indexOf("NP")));
//
//
//            /*
//            TODO: Add a rule where if the string starts with the string "NP", the result would be length of the string
//            * */
//            rules.add(new Rule<>(str->str.startsWith("NP"),str->str.length()));
//
//            List<String> inputs = new ArrayList<>();
//            while (sc.hasNext()) {
//                inputs.add(sc.nextLine());
//            }
//
//            RuleProcessor.process(inputs, rules);
//
//
//        } else { //Test for Student, Double
//            List<Rule<Student, Double>> rules = new ArrayList<>();
//
//            //TODO Add a rule where if the student has at least 3 grades, the result would be the max grade of the student
//            rules.add(new Rule<>(student -> student.grades.size()>=3,student -> student.grades.stream().mapToDouble(Integer::doubleValue).max().orElse(0.0)));
//
//            //TODO Add a rule where if the student has an ID that starts with 20, the result would be the average grade of the student
//            //If the student doesn't have any grades, the average is 5.0
//            rules.add(new Rule<>(student -> student.id.startsWith("20"),student -> student.grades.stream().mapToDouble(Integer::doubleValue).average().orElse(5.0)));
//
//
//            List<Student> students = new ArrayList<>();
//            while (sc.hasNext()){
//                students.add(Student.create(sc.nextLine()));
//            }
//
//            RuleProcessor.process(students, rules);
//        }
//    }
//}
//class Rule<T,U>{
//    final Predicate<T> condition;
//    final Function<T,U> function;
//
//    public Rule(Predicate<T> predicate, Function<T, U> function) {
//        this.condition = predicate;
//        this.function = function;
//    }
//    Optional<U> apply(T input){
//        if(condition.test(input)){
//            return Optional.of(function.apply(input));
//        }else {
//            return Optional.empty();
//        }
//    }
//}
//class RuleProcessor{
//    static<T,U> void process(List<T> list,List<Rule<T,U>> rules){
//
//       for(T item:list){
//           System.out.println("Input: "+item);
//           rules.forEach(rule->rule.apply(item).ifPresentOrElse(x-> System.out.println("Result: "+x),()-> System.out.println("Condition not met")));
//
//       }
//
//    }
//}
