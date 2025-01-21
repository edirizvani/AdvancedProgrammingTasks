//package aud3.calculator;
//
//import java.util.Scanner;
//
//public class CalculatorTest {
//    public static char getCharLower(String line){
//        if(line.trim().length()>0){
//            return Character.toLowerCase(line.charAt(0));
//        }else return '?';
//    }
//    public static void main(String[] args) {
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("Calculator is on.");
//        while (true){
//            Calculator calc=new Calculator();
//            System.out.println(calc.init());
//            while (true){
//                String line=scanner.nextLine();
//                char choice=getCharLower(line);
//                if(choice=='r'){
//                    System.out.println(String.format("Final result = %f",calc.getResult()));
//                    break;
//                }
//                String[] parts=line.split("\\s+");
//                char operator=parts[0].charAt(0);
//                double value=Double.parseDouble(parts[1]);
//                try {
//                        String result = calc.execute(operator, value);
//                    System.out.println(result);
//                    System.out.println(calc);
//                    } catch (UnknownOperator e) {
//                    System.out.println(e.getMessage());
//                }
//
//            }
//
//            System.out.println("(y/n)");
//            String line=scanner.nextLine();
//            char choice=getCharLower(line);
//            if(choice=='n'){
//                break;
//            }
//
//        }
//    }
//}
