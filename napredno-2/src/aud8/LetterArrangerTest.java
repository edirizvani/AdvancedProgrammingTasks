package aud8;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


class LetterArranger
{
    public static String arrangeSentence(String sentence){
        return Arrays.stream(sentence.split("\\s+")).map(LetterArranger::arrangeWords).collect(Collectors.joining(" "));
    }

    public static String arrangeWords(String word){
        return word.chars().sorted().mapToObj(ch->String.valueOf((char)ch)).collect(Collectors.joining());
    }
}




public class LetterArrangerTest {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String sentence=sc.nextLine();

        System.out.println();
    }
}
