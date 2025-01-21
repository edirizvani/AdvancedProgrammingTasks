package aud3.aud3.calculator;



import java.util.Scanner;

public class CalculatorTest {

    private static char getFirstCharacter(String string) {
        return string.trim().toLowerCase().charAt(0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Calculator calculator = new Calculator();
            calculator.init();
            while (true) {
                String line = scanner.nextLine();
                if (getFirstCharacter(line) == 'r')
                    break;

                String[] parts = line.split("\s++");
                try {
                    calculator.execute(parts[0].charAt(0), Double.parseDouble(parts[1]));
                    System.out.println(calculator);
                } catch (OperationNotSupported exception) {
                    System.out.println(exception.getMessage());
                }catch (DivizionByZero ex){
                    System.out.println(ex.getMessage());
                }
            }

            System.out.println(calculator);
            System.out.println("Again ? (Y/N)");
            String line = scanner.nextLine();
            if (getFirstCharacter(line) == 'n')
                break;
        }

    }
}