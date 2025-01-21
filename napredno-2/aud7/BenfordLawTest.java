package aud7;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Counter{
    int countingArray[];

    public Counter(){
        countingArray=new int[10];
    }
    public void addCounter(int digit){
        countingArray[digit]++;
    }
}
class BenfordLawExperiment{
    List<Integer> number;
    Counter counter;

    public BenfordLawExperiment() {
        this.number = new ArrayList<>();
        counter=new Counter();
    }

    public void readData(FileInputStream fileInputStream) {
//        Scanner sc=new Scanner(new InputStreamReader(fileInputStream));
//        while (sc.hasNextLine()){
//            int number=sc.nextInt();
//            this.number.add(number);
//        }
        BufferedReader br=new BufferedReader(new InputStreamReader(fileInputStream));
        this.number=br.lines().filter(line ->!line.equals("")).map(Integer::parseInt).collect(Collectors.toList());

    }

    public void conductExperiment() {
        number.stream().map(this::getFirstDigit).forEach(firstDigit->counter.addCounter(firstDigit));
    }
    private int getFirstDigit(int num){
        while (num>=10){
            num/=10;
        }
        return num;
    }

    @Override
    public String toString() {
       StringBuilder sb=new StringBuilder();
       int sum= Arrays.stream(counter.countingArray).sum();
        for (int i = 1; i < counter.countingArray.length; i++) {
            double percent=((double) counter.countingArray[i]/sum);
            sb.append(String.format("%d: %.2f\n",i,percent));
        }
        return sb.toString();
    }
}

public class BenfordLawTest {


    public static void main(String[] args) {
        BenfordLawExperiment experiment=new BenfordLawExperiment();
        Counter counter=new Counter();
        try {
            experiment.readData(new FileInputStream("C:\\Users\\Administrator\\eclipse-workspace\\Napredno-intro\\src\\aud7\\library_books.txt"));
            experiment.conductExperiment();
            System.out.println(experiment);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
