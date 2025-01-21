//package LAB3;
//import java.util.Map;
//import java.util.Scanner;
//
//public class GenericFractionTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        double n1 = scanner.nextDouble();
//        double d1 = scanner.nextDouble();
//        float n2 = scanner.nextFloat();
//        float d2 = scanner.nextFloat();
//        int n3 = scanner.nextInt();
//        int d3 = scanner.nextInt();
//        try {
//            GenericFraction<Double, Double> gfDouble = new GenericFraction<Double, Double>(n1, d1);
//            GenericFraction<Float, Float> gfFloat = new GenericFraction<Float, Float>(n2, d2);
//            GenericFraction<Integer, Integer> gfInt = new GenericFraction<Integer, Integer>(n3, d3);
//            System.out.printf("%.2f\n", gfDouble.toDouble());
//            System.out.println(gfDouble.add(gfFloat));
//            System.out.println(gfInt.add(gfFloat));
//            System.out.println(gfDouble.add(gfInt));
//            gfInt = new GenericFraction<Integer, Integer>(n3, 0);
//        } catch(ZeroDenominatorException e) {
//            System.out.println(e.getMessage());
//        }
//
//        scanner.close();
//    }
//
//}
//class ZeroDenominatorException extends Exception{
//    public ZeroDenominatorException() {
//        super("Denominator cannot be zero");
//    }
//}
//
//// вашиот код овде
//class GenericFraction<T extends Number,U extends Number> {
//    private T numerator;
//    private U denominator;
//
//    public GenericFraction(T numerator, U denominator) throws ZeroDenominatorException {
//        if(denominator.doubleValue()==0.0){
//            throw new ZeroDenominatorException();
//        }
//        this.numerator = numerator;
//        this.denominator = denominator;
//    }
//    // Euclidean algorithm to compute GCD
//    private double gcd(double a, double b) {
//        while (b != 0) {
//            double temp = b;
//            b = a % b;
//            a = temp;
//        }
//        return a;
//    }
//    GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf) throws ZeroDenominatorException {
//
//        if(gf.denominator==this.denominator){
//            return new GenericFraction<Double,Double>(gf.numerator.doubleValue()+this.numerator.doubleValue(),gf.denominator.doubleValue());
//        }else{
//            double commonDenominator=this.denominator.doubleValue()*gf.denominator.doubleValue();
//            double newNumerator=(this.numerator.doubleValue()*gf.denominator.doubleValue())+(gf.numerator.doubleValue()*this.denominator.doubleValue());
//            double gcd=gcd(Math.abs(newNumerator),Math.abs(commonDenominator));
//            return new GenericFraction<Double,Double>(newNumerator/gcd,commonDenominator/gcd);
//
//        }
//    }
//    double toDouble(){
//        return numerator.doubleValue()/denominator.doubleValue();
//    }
//
//    @Override
//    public String toString() {
//        return String.format("%.2f / %.2f",numerator.doubleValue(),denominator.doubleValue());
//    }
//}