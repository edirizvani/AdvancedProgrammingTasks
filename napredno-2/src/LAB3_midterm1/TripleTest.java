//package LAB3;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class TripleTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int a = scanner.nextInt();
//        int b = scanner.nextInt();
//        int c = scanner.nextInt();
//        Triple<Integer> tInt = new Triple<Integer>(a, b, c);
//        System.out.printf("%.2f\n", tInt.max());
//        System.out.printf("%.2f\n", tInt.avarage());
//        tInt.sort();
//        System.out.println(tInt);
//        float fa = scanner.nextFloat();
//        float fb = scanner.nextFloat();
//        float fc = scanner.nextFloat();
//        Triple<Float> tFloat = new Triple<Float>(fa, fb, fc);
//        System.out.printf("%.2f\n", tFloat.max());
//        System.out.printf("%.2f\n", tFloat.avarage());
//        tFloat.sort();
//        System.out.println(tFloat);
//        double da = scanner.nextDouble();
//        double db = scanner.nextDouble();
//        double dc = scanner.nextDouble();
//        Triple<Double> tDouble = new Triple<Double>(da, db, dc);
//        System.out.printf("%.2f\n", tDouble.max());
//        System.out.printf("%.2f\n", tDouble.avarage());
//        tDouble.sort();
//        System.out.println(tDouble);
//    }
//}
//// vasiot kod ovde
//// class Triple
//class Triple<T extends Number>{
//
//    private T num1;
//    private T num2;
//    private T num3;
//
//    public Triple(T num1, T num2, T num3) {
//        this.num1 = num1;
//        this.num2 = num2;
//        this.num3 = num3;
//    }
//
//    public double max(){
//        if(num1.doubleValue()>num2.doubleValue()){
//            if(num1.doubleValue()>num3.doubleValue()){
//                return num1.doubleValue();
//            }else{
//                return num3.doubleValue();
//            }
//        }else{
//            if(num2.doubleValue()>num3.doubleValue()){
//                return num2.doubleValue();
//            }else{
//                return num3.doubleValue();
//            }
//        }
//
//    }
//    public double avarage(){
//            double sum=num1.doubleValue()+num2.doubleValue()+num3.doubleValue();
//            return sum/3.0;
//    }
//    public void sort(){
//        if (num1.doubleValue() > num2.doubleValue()) {
//            T temp = num2;
//            num2 = num1;
//            num1 = temp;
//        }
//        if (num2.doubleValue() > num3.doubleValue()) {
//            T temp = num3;
//            num3 = num2;
//            num2 = temp;
//        }
//        if (num1.doubleValue() > num2.doubleValue()) {
//            T temp = num2;
//            num2 = num1;
//            num1 = temp;
//        }
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%.2f %.2f %.2f",num1.doubleValue(),num2.doubleValue(),num3.doubleValue());
//    }
//}