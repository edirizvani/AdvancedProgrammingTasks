//package lab5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = new TermFrequency(System.in,
                stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}
class TermFrequency {
    List<String> words;
    HashMap<String, Integer> freq;

    public TermFrequency() {
        this.words = new ArrayList<>();
        freq = new HashMap<>();
    }

    public TermFrequency(InputStream inputStream, String[] stopWords){
        this.words = new ArrayList<>();
        freq = new HashMap<>();
        Scanner scanner = new Scanner(inputStream);
        List<String> stopWordList = Arrays.asList(stopWords);
        while (scanner.hasNextLine()) {
            List<String> line = Arrays.stream(scanner.nextLine().split(" ")).collect(Collectors.toList());
            for (String word : line) {
                String word_cleaned =clean_word(word.toLowerCase()) ;
                if (stopWordList.contains(word_cleaned) || word_cleaned.isEmpty()) {
                    continue;
                }
                freq.put(word_cleaned, freq.getOrDefault(word_cleaned, 0) + 1);
                words.add(word);
            }
        }
        System.out.println(words);
    }
    int countTotal() {
        return words.size();
    }
    int countDistinct() {
        return freq.size();
    }
    List<String> mostOften(int k) {
        TreeSet<Map.Entry<String,Integer>> soredEntries=new TreeSet<>((e1,e2)->{
            int cmp = e2.getValue().compareTo(e1.getValue());
            if (cmp == 0) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return cmp;
        });
        soredEntries.addAll(freq.entrySet());
        int count=0;
        List<String> Top_K = new ArrayList<>();
        for (Map.Entry<String,Integer> entry : soredEntries) {
            if(count>=k){
                break;
            }
            count++;
            Top_K.add(entry.getKey());
        }
        return Top_K;
    }

    private String clean_word(String word) {

        return word.toLowerCase().replace(",", "").replace(".","").trim();
    }
}

// vasiot kod ovde
