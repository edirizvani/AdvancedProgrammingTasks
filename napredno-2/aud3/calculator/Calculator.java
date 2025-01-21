//package aud3.calculator;
//
//public class Calculator {
//
//    private double result;
//    Strategy strategy;
//    public Calculator(){
//        result=0.0;
//    }
//
//    public double getResult() {
//        return result;
//    }
//    public String init(){
//        return String.format(" result= %.2f",result);
//    }
//    @Override
//    public String toString() {
//        return String.format("updated result = %.2f",result);
//    }
//
//    public String execute(char operation,double value) throws UnknownOperator {
//        switch (operation){
//            case '+' :
//                 strategy=new Addition();
//                break;
//            case '-' :
//                strategy=new Subtraction();
//                break;
//            case '*' :
//                strategy=new Multiplication();
//                break;
//            case '/' :
//
//                strategy=new Division();
//                break;
//            default:
//                throw new UnknownOperator(operation);
//
//        }
//        result=strategy.calculate(result,value);
//        return String.format("result %c %.2f = %.2f",operation,value,result);
//
//    }
//
//}
