//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.*;
//
///**
// * January 2016 Exam problem 1
// */
//public class StudentRecordsTest {
//    public static void main(String[] args) {
//        System.out.println("=== READING RECORDS ===");
//        StudentRecords studentRecords = new StudentRecords();
//        int total = studentRecords.readRecords(System.in);
//        System.out.printf("Total records: %d\n", total);
//        System.out.println("=== WRITING TABLE ===");
//        studentRecords.writeTable(System.out);
//        System.out.println("=== WRITING DISTRIBUTION ===");
//        studentRecords.writeDistribution(System.out);
//    }
//}
//
//// your code here
//
//class StudentRecords {
//
//    public int readRecords(InputStream in) {
//        Scanner sc = new Scanner(new InputStreamReader(in));
//        String[] words = sc.nextLine().split(" ");
//    }
//
//    void writeTable(OutputStream outputStream){
//
//    }
//    void  writeDistribution(OutputStream outputStream){
//
//    }
//}