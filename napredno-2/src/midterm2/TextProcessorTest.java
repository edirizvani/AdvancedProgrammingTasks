//package midterm2;
//
//import java.io.*;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class CosineSimilarityCalculator {
//    public static double cosineSimilarity (Collection<Integer> c1, Collection<Integer> c2) {
//        int [] array1;
//        int [] array2;
//        array1 = c1.stream().mapToInt(i -> i).toArray();
//        array2 = c2.stream().mapToInt(i -> i).toArray();
//        double up = 0.0;
//        double down1=0, down2=0;
//
//        for (int i=0;i<c1.size();i++) {
//            up+=(array1[i] * array2[i]);
//        }
//
//        for (int i=0;i<c1.size();i++) {
//            down1+=(array1[i]*array1[i]);
//        }
//
//        for (int i=0;i<c1.size();i++) {
//            down2+=(array2[i]*array2[i]);
//        }
//
//        return up/(Math.sqrt(down1)*Math.sqrt(down2));
//    }
//}
//
//public class TextProcessorTest {
//
//    public static void main(String[] args) {
//        TextProcessor textProcessor = new TextProcessor();
//
//        textProcessor.readText(System.in);
//
//        System.out.println("===PRINT VECTORS===");
//        textProcessor.printTextsVectors(System.out);
//
//        System.out.println("PRINT FIRST 20 WORDS SORTED ASCENDING BY FREQUENCY ");
//        textProcessor.printCorpus(System.out,  20, true);
//
//        System.out.println("PRINT FIRST 20 WORDS SORTED DESCENDING BY FREQUENCY");
//        textProcessor.printCorpus(System.out, 20, false);
//
//        System.out.println("===MOST SIMILAR TEXTS===");
//        textProcessor.mostSimilarTexts(System.out);
//    }
//}
//class TextProcessor {
//    Set<String> textsVectors = new HashSet<>();
//    List<Map<String,Integer>> occurences_per_line=new ArrayList<>();
//    List<String> data_raw=new ArrayList<>();
//
//    public TextProcessor() {
//
//    }
//
//    public void readText(InputStream in) {
//        Scanner scanner = new Scanner(new InputStreamReader(in));
//        int i=0;
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            data_raw.add(line);
//            String[] words = line.replaceAll("[^A-Za-z\\s+]", "").toLowerCase().split(" ");
//            occurences_per_line.add(i, new HashMap<>());
//            for (String word : words) {
//                textsVectors.add(word);
//                occurences_per_line.get(i).put(word, occurences_per_line.get(i).getOrDefault(word,0) + 1);
//            }
//            i++;
//        }
//    }
//
//    public void printTextsVectors (OutputStream os) {
//        PrintWriter pw = new PrintWriter(os);
//        List<String> textVectorsSorted = textsVectors.stream().sorted().collect(Collectors.toList());
//        List<List<Integer>> matrix = new ArrayList<>();
//
//        for (int i=0;i<occurences_per_line.size();i++) {
//            matrix.add(new ArrayList<>());
//            for (int j=0;j<textVectorsSorted.size();j++) {
//                matrix.get(i).add(j,occurences_per_line.get(i).getOrDefault(textVectorsSorted.get(j),0));
//            }
//        }
//        matrix.forEach(pw::println);
//        pw.flush();
//    }
//
//    public void printCorpus(OutputStream os, int n, boolean ascending) {
//        PrintWriter pw = new PrintWriter(os);
//
//        occurences_per_line.
//
//
//        pw.flush();
//    }
//
//    public void mostSimilarTexts (OutputStream os) {
//        PrintWriter pw = new PrintWriter(os);
//        String similar1="",similar2="";
//        double maxSimilarity=-1;
//        for (int i=0;i<occurences_per_line.size();i++) {
//            for (int j=0;j<occurences_per_line.size();j++) {
//                if(i!=j){
//                    double max=CosineSimilarityCalculator.cosineSimilarity(occurences_per_line.get(i).values(),occurences_per_line.get(i).values());
//                    if(max>maxSimilarity){
//                        maxSimilarity=max;
//                        similar1=data_raw.get(i);
//                        similar2=data_raw.get(j);
//                    }
//                }
//            }
//        }
//        pw.println(similar1);
//        pw.println(similar2);
//        pw.println(String.format("%.10f", maxSimilarity));
//        pw.flush();
//
//        pw.flush();
//    }
//}
