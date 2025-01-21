package aud8;

import java.util.Collection;

public class CountOccurencesTest {
    public static int count1(Collection<Collection<String>> c,String str) {
        int counter=0;
        for (Collection<String> strings : c) {
            for(String s:strings) {
                if(s.equals(str)){
                    counter++;
                }
            }
        }
        return counter;
    }
    public static int count2(Collection<Collection<String>> c,String str) {
        return (int) c.stream().flatMap(Collection::stream).filter(string->string.equals(str)).count();
    }
}
