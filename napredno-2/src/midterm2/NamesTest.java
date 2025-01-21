//import java.util.*;
//
//public class NamesTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        Names names = new Names();
//        for (int i = 0; i < n; ++i) {
//            String name = scanner.nextLine();
//            names.addName(name);
//        }
//        n = scanner.nextInt();
//        System.out.printf("===== PRINT NAMES APPEARING AT LEAST %d TIMES =====\n", n);
//        names.printN(n);
//        System.out.println("===== FIND NAME =====");
//        int len = scanner.nextInt();
//        int index = scanner.nextInt();
//        System.out.println(names.findName(len, index));
//        scanner.close();
//
//    }
//}
//
//// vashiot kod ovde
//class Names {
//    Map<String,Integer> map;
//
//    public Names() {
//        map = new HashMap<>();
//    }
//
//    public void addName(String name){
//        map.putIfAbsent(name,0);
//        map.put(name,map.get(name)+1);
//    }
//    public void printN(int n){
//
//    }
//}