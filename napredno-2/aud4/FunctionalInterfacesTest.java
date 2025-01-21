package aud4;

import java.util.Random;
import java.util.function.*;

public class FunctionalInterfacesTest {
    public static void main(String[] args) {
        //supplier doesnt give arguments just gives a random result
//        Supplier<Integer> integerSupplier=new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return new Random().nextInt(100);
//            }
//        };
//        Supplier<Integer> integerSupplier= () -> {
//            int number =new Random().nextInt(100);
//            return number%10;
//        };
        Supplier<Integer> integerSupplier= () -> new Random().nextInt(100);
        //consumer->prima eden argument, ne vraka rezultat
//        Consumer<Integer> integerConsumer=new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        };
        Consumer<Integer> consumer=(integer)-> System.out.println(integer);

        //Predicate -> prima
//        Predicate<Integer> lessThen=new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) {
//                return integer<10;
//            }
//        };
        Predicate<Integer> predicateLessThen=integer->integer<10;
        //Function -> prima eden argument, a vraka nekoj drug argument, .map , anonimna krasta, da mapirame nekoja vlezna vrednost so nekoja druga
        Function<Integer,Integer> addFiveToTheNumbers=new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer+5;
            }
        };
        Function<Integer,Integer> addFiveLambda=integer->integer+5;

        //BiFunction
        BiFunction<Integer,Integer,String> biFunction=new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) {
                return String.valueOf(integer%integer2);
            }
        };
        BiFunction<Integer,Integer,String> biFunctionL=(x,x1)->String.valueOf(x%x1);
    }
}
