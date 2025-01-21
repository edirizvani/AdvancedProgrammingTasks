package midterm2;//package mk.ukim.finki.vtor_kolokvium;

import javax.naming.OperationNotSupportedException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class OperationNotAllowedException extends Exception {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
class Faculty {
    Map<String, Student> students;
    StringBuilder logs;
    Map<String,Course> courses;
    public Faculty() {
        students = new HashMap<>();
        logs = new StringBuilder();
        courses = new LinkedHashMap<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        if (!students.containsKey(id)) {
            students.put(id, new Student(id, yearsOfStudies));
        }
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        if(!courses.containsKey(courseName)) {
            courses.put(courseName, new Course(courseName));
        }
        Student student = students.get(studentId);
        if (student != null) {
            Grade gradeToAdd = new Grade(courseName, grade);
            boolean iff=student.addGrade(term,gradeToAdd);
            if(iff){
                logs.append(String
                        .format("Student with ID %s graduated with average grade %.2f in %d years.\n",student.id,student.getAverage(),student.yearsOfStudies));
                students.remove(studentId);
            }

            courses.get(courseName).addStudent(student);
        }

    }

    String getFacultyLogs() {
        return logs.toString();
    }

    String getDetailedReportForStudent(String id) throws NullPointerException {
        Student student = students.get(id);
        if (student != null) {
            return student.getDetailedReport();
        }
        throw  new NullPointerException();
    }

    void printFirstNStudents(int n) {
        students.values().stream().sorted(Comparator.comparing(Student::getPassedCourses).thenComparing(Student::getAverage).reversed().thenComparing(Student::getName))
                .limit(n).forEach(System.out::print);
    }
    void printCourses() {
        Comparator<Course> comparator=Comparator.comparing(Course::getCount).thenComparing(Course::getAverage).thenComparing(Course::getCourseName);
        TreeSet<Course> courses_here = new TreeSet<>(comparator);
        courses_here.addAll(courses.values());
        courses_here.forEach(System.out::println);
    }
}
class Course{
    String courseName;
    List<Student> students;

    public Course(String courseName) {
        this.courseName = courseName;
        students = new ArrayList<>();
    }
    void addStudent(Student s){
        students.add(s);
    }
    double getAverage(){
        return students.stream().mapToDouble(Student::getAverage).average().orElse(5.0);
    }
    int getCount(){
        return students.size();
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f",courseName,getCount(),getAverage());
    }
}
class Student {
    String id;
    int yearsOfStudies;
    Map<Integer,List<Grade>> gradesPerSemester;
    Set<String> courses;
 //grades for each semester

    public Student(String id, int yearsOfStudies) {
        this.id = id;
        this.yearsOfStudies = yearsOfStudies;
        gradesPerSemester = new TreeMap<>();
        courses = new TreeSet<>();
        createTerms();
    }
    void createTerms() {
        for (int i = 1; i <=yearsOfStudies*2 ; i++) {
            gradesPerSemester.put(i, new ArrayList<>());
        }
    }
    public boolean addGrade(int term,Grade grade) throws OperationNotAllowedException {
        if((yearsOfStudies==3 && term>6) || (yearsOfStudies==4 && term>8)){
            throw new OperationNotAllowedException(String
                    .format("Term %d is not possible for student with ID %s",term,id));
        }
        if(gradesPerSemester.get(term).size()<3){
            gradesPerSemester.get(term).add(grade);
        }else{
            throw new OperationNotAllowedException(String
                    .format("Student %s already has 3 grades in term %s",id,term));
        }

        courses.add(grade.getCourseName());
        return checkIfStudentDiplomira();
    }
    boolean checkIfStudentDiplomira() {
        int gradesPassed=(int)this.gradesPerSemester.values().stream().flatMap(List::stream).count();
        if((yearsOfStudies==3 && gradesPassed>=18)||(yearsOfStudies==4 && gradesPassed>=24)){
            return true;
        }
        return false;
    }

    public double getAverage() {
        return gradesPerSemester.values().stream().flatMap(List::stream).mapToDouble(Grade::getGradeDouble).average().orElse(5.0);
    }
    String getDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append(String.format("Student: %s\n",id));
        gradesPerSemester.entrySet().stream().forEach(e -> {
            double average=e.getValue().stream().mapToDouble(Grade::getGradeDouble).average().orElse(5.0);

           report.append(String.format("Term %d \nCourses: %d\nAverage grade for term: %.2f\n",e.getKey(),e.getValue().size(),average));
        });
        report.append(String.format("Average grade: %.2f\n",getAverage()));
        report.append("Courses attended: ").append(String.join(",",courses));

        return report.toString();
    }

    int getPassedCourses() {
        return (int) gradesPerSemester.values().stream().flatMap(List::stream).count();
    }



    @Override
    public String toString() {
        return String.format("Student: %s Courses passed: %s Average grade: %.2f\n",id,getPassedCourses(),getAverage());
    }

    public String getName() {
        return id;
    }
}
class Grade {
    String courseName;
    int grade;

    public Grade( String courseName, int grade) {
        this.courseName = courseName;
        this.grade = grade;
    }

    public double getGradeDouble() {
        return (double) grade;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return grade+" ";
    }
}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
