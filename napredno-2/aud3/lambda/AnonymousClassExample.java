package aud3.lambda;

public class AnonymousClassExample {
    FunctionalInterface Aaddition=new FunctionalInterface() {
        @Override
        public double doOperation(double a, double b) {
            return a+b;
        }

    };
    public static void main(String[] args) {
        AnonymousClassExample example=new AnonymousClassExample();
        System.out.println(example.Aaddition.doOperation(4,6));
    }
}
