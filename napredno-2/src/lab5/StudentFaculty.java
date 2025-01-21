////package lab5;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Student{
//
//    public int compareTo1(Student o) {
//        if(this.equals(o)){
//            return 0;
//        }
//        if(this.getAverage() > o.getAverage()){
//            return -1;
//        }else if(this.getAverage() < o.getAverage()){
//            return 1;
//        }else{
//            if(this.grades.size()>o.grades.size()){
//                return -1;
//            }  else if(this.grades.size()<o.grades.size()){
//                return 1;
//            }else{
//                return this.id.compareTo(o.id);
//            }
//        }
//    }
//    public int compareTo2(Student o) {
//        if(this.equals(o)){
//            return 0;
//        }
//        if(this.grades.size() > o.grades.size()){
//            return -1;
//        }else if(this.grades.size() < o.grades.size()){
//            return 1;
//        }else{
//            if(this.getAverage()>o.getAverage()){
//                return -1;
//            }  else if(this.grades.size()<o.grades.size()){
//                return 1;
//            }else{
//                return this.id.compareTo(o.id);
//
//            }
//        }
//    }
//
//    double getAverage() {
//        return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
//    }
//
//    String id;
//    List<Integer> grades;
//
//
//    public Student(String id, List<Integer> grades) {
//        this.id = id;
//        this.grades = grades;
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
//class StudentExcists extends Exception{
//    public StudentExcists(String message) {
//        super(message);
//    }
//}
//class Faculty{
//    HashMap<String,Student> map;
//
//    public Faculty() {
//
//        map = new LinkedHashMap<>();
//    }
//    void addStudent(String id, List<Integer> grades) throws StudentExcists {
//        if(!map.containsKey(id)){
//            map.put(id,new Student(id, grades));
//
//        }else{
//            throw new StudentExcists(String.format("Student with ID %s already exists",id));
//        }
//    }
//    void addGrade(String id, int grade) throws StudentExcists {
//        Student s=map.get(id);
//        s.grades.add(grade);
//    }
//    Set<Student> getStudentsSortedByAverageGrade(){
//        List<Student> st=map.values().stream().sorted(Student::compareTo1).collect(Collectors.toList());
//        return new LinkedHashSet<>(st);
//    }
//    Set<Student> getStudentsSortedByCoursesPassed(){
//        List<Student> st=map.values().stream().sorted(Student::compareTo2).collect(Collectors.toList());
//        return new LinkedHashSet<>(st);  }
//
//}
//
//
//
//
//public class StudentFaculty {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        Faculty faculty = new Faculty();
//        while (in.hasNextLine()){
//            String[] info=in.nextLine().split(" ");
//            String command=info[0];
//           if(command.equals("addStudent")){
//               String id=info[1];
//               String[] grades = Arrays.copyOfRange(info, 2, info.length);
//               List<Integer> grades1 = Arrays.stream(grades).map(Integer::parseInt).collect(Collectors.toList());
//               try {
//                   faculty.addStudent(id,grades1);
//               } catch (StudentExcists e) {
//                   System.out.println(e.getMessage());
//               }
//           } else if (command.equals("getStudentsSortedByAverageGrade")) {
//               System.out.println("Sorting students by average grade");
//               Set<Student> hello=faculty.getStudentsSortedByAverageGrade();
//               for (Student student : hello) {
//                   System.out.println(student);
//               }
//           } else if (command.equals("getStudentsSortedByCoursesPassed")) {
//               System.out.println("Sorting students by courses passed");
//               Set<Student> hello=faculty.getStudentsSortedByCoursesPassed();
//               for (Student student : hello) {
//                   System.out.println(student);
//               }
//           }else if (command.equals("addGrade")){
//               String id=info[1];
//               int grade=Integer.parseInt(info[2]);
//               try {
//                   faculty.addGrade(id,grade);
//               } catch (StudentExcists e) {
//                   System.out.println(e.getMessage());
//               }
//           }
//           }
//        }
//    }
//
