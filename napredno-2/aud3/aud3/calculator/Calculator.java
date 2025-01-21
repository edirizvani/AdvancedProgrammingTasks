package aud3.aud3.calculator;



public class Calculator {
    private double state;
    private Strategy strategy;


    public Calculator() {
        this.state = 0.0F;
    }
//    public String execute(char operator,double num) throws OperationNotSupported,DivizionByZero{
//        Strategy strategy1 = null;
//        switch (operator){
//            case '+': strategy1=new Addition(); break;
//            case '-': strategy1=new Substraction(); break;
//            case '*': strategy1=new Multiplication(); break;
//            case '^': strategy1=new Power(); break;
//            case '/': if(num!=0){ strategy1=new Division();}else{throw new DivizionByZero();}; break;
//            default:
//                throw new OperationNotSupported(operator);
//        }
//
//        state=strategy1.calculate(state,num);
//        return String.format("result %c %f=%.2f",operator,num,state);
//    }
    public String execute(char operator,double num) throws OperationNotSupported,DivizionByZero{
        Strategy strategy1 = null;
        switch (operator){
            case '+': strategy1=new Addition(); break;
            case '-': strategy1=new Substraction(); break;
            case '*': strategy1=new Multiplication(); break;
            case '^': strategy1=new Power(); break;
            case '/': if(num!=0){ strategy1=new Division();}else{throw new DivizionByZero();}; break;
            default:
                throw new OperationNotSupported(operator);
        }

        state=strategy1.calculate(state,num);
        return String.format("result %c %f=%.2f",operator,num,state);
    }

    public void init(){
        System.out.println("calculator is on");
    }

    @Override
    public String toString() {
        return String.format("updated result=%f",state);
    }
}
