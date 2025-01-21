//package LAB3;
//
//import java.util.Comparator;
//import java.util.Scanner;
//
//class MinMax<T extends Comparable<T>>{
//    T min;
//    T max;
//    int total;
//    int minCount;
//    int maxCount;
//    public MinMax() {
//        total=0;
//        minCount=0;
//        maxCount=0;
//    }
//    void update(T element){
//        if(total==0){
//            min=element;
//            max=element;
//
//        }
//        ++total;
//        if(element.compareTo(min)<0){
//            minCount=1;
//            min=element;
//        }else{
//            if(min.compareTo(element)==0){
//                minCount++;
//            }
//        }
//
//        if(element.compareTo(max)>0){
//            maxCount=1;
//            max=element;
//        }else {
//            if(max.compareTo(element)==0){
//                maxCount++;
//            }
//        }
//    }
//
//    public T min() {
//        return min;
//    }
//
//    public T max() {
//        return max;
//    }
//
//    @Override
//    public String toString() {
//        return String.format(min+" "+max+" "+(total-(minCount+maxCount))+"\n");
//    }
//}
//
//
//
//public class MinAndMax {
//    public static void main(String[] args) throws ClassNotFoundException {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        MinMax<String> strings = new MinMax<String>();
//        for(int i = 0; i < n; ++i) {
//            String s = scanner.next();
//            strings.update(s);
//        }
//        System.out.println(strings);
//        MinMax<Integer> ints = new MinMax<Integer>();
//        for(int i = 0; i < n; ++i) {
//            int x = scanner.nextInt();
//            ints.update(x);
//        }
//        System.out.println(ints);
//    }
//}