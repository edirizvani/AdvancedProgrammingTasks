//package LAB3;
//
//import java.util.Scanner;
//
///**
// * I partial exam 2016
// */
//public class ApplicantEvaluationTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//        int creditScore = scanner.nextInt();
//        int employmentYears = scanner.nextInt();
//        boolean hasCriminalRecord = scanner.nextBoolean();
//        int choice = scanner.nextInt();
//        Applicant applicant = new Applicant(name, creditScore, employmentYears, hasCriminalRecord);
//        Evaluator.TYPE type = Evaluator.TYPE.values()[choice];
//        Evaluator evaluator = null;
//        try {
//            evaluator = EvaluatorBuilder.build(type);
//            System.out.println("Applicant");
//            System.out.println(applicant);
//            System.out.println("Evaluation type: " + type.name());
//            if (evaluator.evaluate(applicant)) {
//                System.out.println("Applicant is ACCEPTED");
//            } else {
//                System.out.println("Applicant is REJECTED");
//            }
//        } catch (InvalidEvaluation invalidEvaluation) {
//            System.out.println("Invalid evaluation");
//        }
//    }
//}
//
//class Applicant {
//    private String name;
//
//    private int creditScore;
//    private int employmentYears;
//    private boolean hasCriminalRecord;
//
//    public Applicant(String name, int creditScore, int employmentYears, boolean hasCriminalRecord) {
//        this.name = name;
//        this.creditScore = creditScore;
//        this.employmentYears = employmentYears;
//        this.hasCriminalRecord = hasCriminalRecord;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getCreditScore() {
//        return creditScore;
//    }
//
//    public int getEmploymentYears() {
//        return employmentYears;
//    }
//
//    public boolean hasCriminalRecord() {
//        return hasCriminalRecord;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("Name: %s\nCredit score: %d\nExperience: %d\nCriminal record: %s\n",
//                name, creditScore, employmentYears, hasCriminalRecord ? "Yes" : "No");
//    }
//}
//class InvalidEvaluation extends Exception{
//    public InvalidEvaluation() {
//        super("InvalidEvaluation");
//    }
//}
//interface Evaluator {
//    enum TYPE {
//        NO_CRIMINAL_RECORD,
//        MORE_EXPERIENCE,
//        MORE_CREDIT_SCORE,
//        NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE,
//        MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE,
//        NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE,
//        INVALID // should throw exception
//    }
//
//    boolean evaluate(Applicant applicant);
//}
//
//class EvaluatorBuilder {
//    public static Evaluator build(Evaluator.TYPE type) throws InvalidEvaluation {
//
//        switch (type){
//            case INVALID: throw new InvalidEvaluation();
//            case MORE_EXPERIENCE : return new Experience();
//            case NO_CRIMINAL_RECORD : return new CriminalRecord();
//            case MORE_CREDIT_SCORE : return new CREDIT_SCORE();
//            case MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE : return new MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE(new CREDIT_SCORE());
//            case NO_CRIMINAL_RECORD_AND_MORE_EXPERIENCE : return new CRIMINAL_RECORD_AND_MORE_EXPERIENCE(new Experience());
//            case NO_CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE : return new CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE( new CREDIT_SCORE());
//            default :
//                throw  new InvalidEvaluation();
//
//        }
//        //TODO
//    }
//}
//
//abstract class chainEvaluator implements Evaluator{
//
//
//    Evaluator next;
//    protected  chainEvaluator(){
//
//    }
//
//    public chainEvaluator(Evaluator next) {
//        this.next = next;
//    }
//
//
//
//}
//
//class CriminalRecord extends chainEvaluator{
//    protected CriminalRecord() {
//        super();
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        return a.hasCriminalRecord();
//    }
//}
//class Experience extends chainEvaluator{
//    protected Experience() {
//        super();
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        if(a.getEmploymentYears()>=10){
//            return true;
//        }else{
//            return false;
//        }
//    }
//}
//class CREDIT_SCORE extends chainEvaluator{
//    protected CREDIT_SCORE() {
//        super();
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        if(a.getCreditScore()>=500){
//            return true;
//        }
//        return false;
//    }
//}
//class CRIMINAL_RECORD_AND_MORE_EXPERIENCE extends chainEvaluator{
//    protected CRIMINAL_RECORD_AND_MORE_EXPERIENCE(Evaluator next) {
//        super(next);
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        if( !a.hasCriminalRecord()){
//            if(next!=null && next.evaluate(a)){
//                return true;
//            }
//
//        }
//        return false;
//    }
//}
//class MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE extends chainEvaluator{
//    protected MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE(Evaluator next) {
//        super(next);
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        if(a.getEmploymentYears()>=10 ){
//            if(next!=null && next.evaluate(a)){
//                return true;
//            }
//        }
//        return false;
//    }
//}
//class CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE extends chainEvaluator{
//    protected CRIMINAL_RECORD_AND_MORE_CREDIT_SCORE(Evaluator next) {
//        super(next);
//    }
//    @Override
//    public boolean evaluate(Applicant a){
//        if( !a.hasCriminalRecord()){
//            if(next!=null && next.evaluate(a)){
//                return true;
//            }
//        }
//        return false;
//    }
//}
//// имплементација на евалуатори овде
//
//
