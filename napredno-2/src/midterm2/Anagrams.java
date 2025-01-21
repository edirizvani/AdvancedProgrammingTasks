//package midterm2;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Anagrams {

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        // Vasiod kod ovde

        Map<String,Set<String>> map = new LinkedHashMap<>();
        Scanner sc=new Scanner(new InputStreamReader(inputStream));

        while (sc.hasNextLine()) {
            String line=sc.nextLine().trim();
            String word=getSortedWord(line);
            if(map.containsKey(word)){
               map.get(word).add(line);
            }else{
                Set<String> anagrams_of_this_word=new TreeSet<>();
                anagrams_of_this_word.add(line);
                map.put(word,anagrams_of_this_word);
            }
        }
//        List<List<String>> list_of_anagrams = new ArrayList<>();
//        for (int i=0;i<words.size();i++) {
//            String word1=getSortedWord(words.get(i));
//            Set<String> anagrams_of_this_word = new TreeSet<>();
//            for (int j = 0; j < words.size() ; j++) {
//                if(i==j){
//                    continue;
//                }
//                String word_final=getSortedWord(words.get(j));
//                if(word1.equals(word_final)){
//                    anagrams_of_this_word.add(words.get(j));
//                }
//            }
//            if(!anagrams_of_this_word.isEmpty()){
//                anagrams_of_this_word.add(words.get(i));
//                map.putIfAbsent(word1,anagrams_of_this_word);
//            }
//        }
        for (Set<String> anagrams : map.values()) {
            for (String anagram : anagrams) {
                System.out.printf("%s ", anagram);
            }
            System.out.println();
        }


    }
    static private String getSortedWord(String word){
        char[] word2=word.toCharArray();
        Arrays.sort(word2);
        return new String(word2);
    }
}
