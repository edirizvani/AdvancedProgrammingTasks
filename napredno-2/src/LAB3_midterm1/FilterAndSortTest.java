package LAB3_midterm1;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Student implements Comparable<Student> {
    String id;
    List<Integer> grades;

    public Student(String id, List<Integer> grades) {
        this.id = id;
        this.grades = grades;
    }

    public double average() {
        return grades.stream().mapToDouble(i -> i).average().getAsDouble();
    }

    public int getYear() {
        return (24 - Integer.parseInt(id.substring(0, 2)));
    }

    public int totalCourses() {
        return Math.min(getYear() * 10, 40);
    }

    public double labAssistantPoints() {
        return average() * ((double) grades.size() / totalCourses()) * (0.8 + ((getYear()-1)*0.2)/3.0);
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::labAssistantPoints)
                .thenComparing(Student::average)
                .compare(this, o);
    }

    @Override
    public String toString() {
        return String.format("Student %s (%d year) - %d/%d passed exam, average grade %.2f.\nLab assistant points: %.2f", id, getYear(), grades.size(), totalCourses(), average(), labAssistantPoints());
    }
}


public class FilterAndSortTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        if (testCase == 1) { // students
            int studentScenario = Integer.parseInt(sc.nextLine());
            List<Student> students = new ArrayList<>();
            while (n > 0) {

                String line = sc.nextLine();
                String[] parts = line.split("\\s+");
                String id = parts[0];
                List<Integer> grades = Arrays.stream(parts).skip(1).map(Integer::parseInt).collect(Collectors.toList());
                students.add(new Student(id, grades));
                --n;
            }

            if (studentScenario == 1) {
                List<Student> result=null;
                //TODO filter and sort all students who have at least 8.0 points and are at least 3rd year student
                try {
                    result=FilterAndSort.execute(students,student -> student.labAssistantPoints()>=8.0 && student.getYear()>=3);
                } catch (EmptyResultException e) {
                    System.out.println(e.getMessage());;
                }
                if (result!=null){
                    result.stream().forEach(System.out::println);
                }

            } else {
                List<Student> result=null;
                //TODO filter and sort all students who have passed at least 90% of their total courses with an average grade of at least 9.0
                try {
                    result=FilterAndSort.execute(students,student -> ((double)student.grades.size()/student.totalCourses())>0.9 && student.average()>=9.0);
                } catch (EmptyResultException e) {
                    System.out.println(e.getMessage());;
                }
                if (result!=null){
                    result.stream().forEach(System.out::println);
                }
            }
        } else { //integers
            List<Integer> integers = new ArrayList<>();
            while (n > 0) {
                integers.add(Integer.parseInt(sc.nextLine()));
                --n;
            }
            List<Integer> integers1=null;
            //TODO filter and sort all even numbers divisible with 15
            try {
                integers1=FilterAndSort.execute(integers,integer-> integer%2==0 && integer%15==0);
            } catch (EmptyResultException e) {
                System.out.println(e.getMessage());;
            }
            if(integers1!=null){
                integers1.stream().forEach(System.out::println);
            }



        }

    }
}
class EmptyResultException extends Exception{
    public EmptyResultException(String message) {
        super(message);
    }
}
class FilterAndSort{
    static <T extends Comparable<T>> List<T> execute(List<T> list,Predicate<T> predicate) throws EmptyResultException {
        List<T> result=list.stream().filter(predicate).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        if(result.isEmpty()){
            throw new EmptyResultException("No element met the criteria");

        }
        return result;
    }
}
