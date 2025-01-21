//package LAB3;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.DoubleSummaryStatistics;
//import java.util.Scanner;
//
//public class MojDDVTest {
//
//    public static void main(String[] args) {
//
//        MojDDV mojDDV = new MojDDV();
//
//        System.out.println("===READING RECORDS FROM INPUT STREAM===");
//        mojDDV.readRecords(System.in);
//
//        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
//        mojDDV.printTaxReturns(System.out);
//
//        System.out.println("===PRINTING SUMMARY STATISTICS FOR TAX RETURNS TO OUTPUT STREAM===");
//        mojDDV.printStatistics(System.out);
//
//    }
//}
//enum Type{
//    A,B,V
//}
//class MojDDV{
//    ArrayList<Record> records;
//
//
//    public MojDDV() {
//        this.records = new ArrayList<>();
//    }
//
//    void readRecords (InputStream inputStream){
//        Scanner sc=new Scanner(new InputStreamReader(inputStream));
//        while (sc.hasNextLine()){
//            try {
//                Record rec=new Record(sc.nextLine());
//                records.add(rec);
//            }catch (AmountNotAllowedException ex){
//                System.out.println(ex.getMessage());
//            }
//        }
//        sc.close();
//    }
//    void printTaxReturns (OutputStream outputStream){
//        PrintWriter pr=new PrintWriter(outputStream);
//        for (Record rec:records) {
//            pr.printf("%10d\\t%10d\\t%10.5f\n", rec.id, rec.sum_money, rec.getTaxReturn());
//        }
//        pr.flush();
//    }
//    void printStatistics (OutputStream outputStream){
//        PrintWriter pr=new PrintWriter(outputStream);
//        DoubleSummaryStatistics summaryStatistics=records.stream().mapToDouble(Record::getTaxReturn).summaryStatistics();
//        pr.println(String.format("min:\t%05.03f\nmax:\t%05.03f\nsum:\t%05.03f\ncount:\t%-5d\navg:\t%05.03f",
//                summaryStatistics.getMin(),
//                summaryStatistics.getMax(),
//                summaryStatistics.getSum(),
//                summaryStatistics.getCount(),
//                summaryStatistics.getAverage()));
//
//        pr.flush();
//    }
//}
//class Record{
//    int id;
//    ArrayList<Smetka> list;
//    int sum_money;
//
//    public Record(String line) throws AmountNotAllowedException {
//        list=new ArrayList<>();
//        sum_money=0;
//        String everything[]=line.split(" ");
//        id=Integer.parseInt(everything[0]);
//        for (int i = 1; i <everything.length ; i++) {
//            try {
//                int sum=Integer.parseInt(everything[i]);
//                list.add(new Smetka(sum,Type.valueOf(everything[++i])));
//                sum_money+=sum;
//            } catch (AmountNotAllowedException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        if(sum_money>30000){
//           throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned",sum_money));
//        }
//    }
//    double getTaxReturn(){
//        return list.stream().mapToDouble(rec->rec.getValue()).sum();
//    }
//
//
//}
//class AmountNotAllowedException extends Exception{
//    public AmountNotAllowedException(String s) {
//        super(s);
//    }
//}
//class Smetka{
//    Type type;
//    int amount;
//
//    public Smetka(int amount,Type type) throws AmountNotAllowedException {
//        this.type = type;
//        if(amount>30000){
//            throw new AmountNotAllowedException(String.format("Receipt with amount %d is not allowed to be scanned",amount));
//        }
//        this.amount = amount;
//    }
//    double getValue(){
//        if(type==Type.A){
//            return amount*0.18*0.15;
//        } else if (type==Type.B) {
//            return amount*0.05*0.15;
//        }
//            return 0;
//
//    }
//}
//
