package aud4;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;


class LineCounter {
    int lines=0,words=0,chars=0;


    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }
    public LineCounter(String line){
        lines++;
        words=line.split("\\s+").length;
        chars=line.length();
    }

    public String toString() {
        return "LineConsumer{" +
                "lines=" + lines +
                ", words=" + words +
                ", chas=" + chars +
                '}';
    }
    public LineCounter sum(LineCounter other){
        return new LineCounter(this.lines+other.lines,this.words+ other.words,this.chars+ other.chars);
    }
}


public class WordCounterTest {


public static void calculateWithBufferReaderAndConsumer(InputStream inputStream)  {
    BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));

    LineCounter finale=br.lines().map(line -> new LineCounter(line)).reduce(new LineCounter(0,0,0),(one,two)->one.sum(two));
    System.out.println(finale);


}

//    class LineConsumer implements Consumer<String>{
//        int lines=0,words=0,chars=0;
//
//
//        @Override
//        public void accept(String s) {
//            ++lines;
//            this.words+=s.split("\\s+").length;
//            chars+=s.length();
//        }
//
//        @Override
//        public String toString() {
//            return "LineConsumer{" +
//                    "lines=" + lines +
//                    ", words=" + words +
//                    ", chas=" + chars +
//                    '}';
//        }
//    }
//
//
//    public class WordCounterTest {
//

//        public static void calculateWithBufferReaderAndConsumer(InputStream inputStream) throws IOException {
//            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
//            LineConsumer lineConsumer=new LineConsumer();
//            br.lines().forEach(line -> lineConsumer.accept(line));
//            System.out.println(lineConsumer);
//        }
//
//
//
//



    public static void calculateWithScanner(InputStream in){
        int lines=0,words=0,chars=0;
        Scanner sc=new Scanner(in);
        while(sc.hasNext()){
            String line=sc.nextLine();
            ++lines;
            words+=line.split("\\s+").length;
            chars+=line.length();
        }
        System.out.printf("Lines: %d Words: %d Chars: %d",lines,words,chars);
    }
    public static void main(String[] args) {
        File file=new File("/Users/Administrator/eclipse-workspace/Napredno-intro/src/aud4/text");
        try {
            calculateWithScanner(new FileInputStream(file));
            calculateWithBufferReaderAndConsumer(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
