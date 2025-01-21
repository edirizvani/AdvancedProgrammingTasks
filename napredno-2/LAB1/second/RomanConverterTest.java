package LAB1.second;

import java.util.Scanner;
import java.util.stream.IntStream;

public class RomanConverterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        IntStream.range(0, n)
                .forEach(x -> System.out.println(RomanConverter.toRoman(scanner.nextInt())));
        scanner.close();
    }
}


class RomanConverter {
    /**
     * Roman to decimal converter
     *
     * @param n number in decimal format
     * @return string representation of the number in Roman numeral
     */
    public static String toRoman(int n) {
        int[] values ={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] roman_letters={"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder roman_final=new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if((float)n/values[i]>=1.0){
                int multiplier=n/values[i];
                roman_final.append(roman_letters[i].repeat(multiplier));
                n-=multiplier*values[i];
            }
        }

        return roman_final.toString();
    }

}
