package aud3.aud3.calculator;



public class Substraction implements Strategy
{
    @Override
    public double calculate(double num1, double num2) {
        return num1-num2;
    }
}
