package aud3.aud3.calculator;

public class Power implements Strategy {
    @Override
    public double calculate(double num, double num2) {
        return Math.pow(num,num2);
    }
}
