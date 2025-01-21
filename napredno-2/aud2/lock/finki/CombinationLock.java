package aud2.lock.finki;

public class CombinationLock {

    private int combination;
    private boolean isOpen;
    private static int DEAFULT_COMBINATION=100;

    public CombinationLock(int combination){
        if(isCombinationValid(combination)) {
            this.combination = combination;
        }else{
            this.combination=DEAFULT_COMBINATION;
        }
        this.isOpen=false;
    }

    public boolean isCombinationValid(int combination){
        return  combination>=100 && combination<=999;
    }

    public boolean open(int combination){
        this.isOpen=(this.combination==combination);
        return isOpen;
    }

    public boolean changingCombination(int old,int newc){
        if(open(old) && isCombinationValid(newc)){
            this.combination=newc;
            return true;
        }
        return false;
    }

    public void lock(){
        this.isOpen=false;
    }

    public static void main(String[] args) {
        CombinationLock validLock=new CombinationLock(234);
        System.out.println(validLock.isOpen);
        System.out.println("TEST OPEN");
        System.out.println(validLock.open(243));
        System.out.println(validLock.open(234));

        validLock.lock();
        System.out.println("TEST CHANGE COMBINATOR");
        System.out.println(validLock.changingCombination(234,777));
        System.out.println(validLock.open(777));



        CombinationLock invalidLock=new CombinationLock(231233);
        System.out.println(invalidLock.isOpen);
        System.out.println(" invalid TEST OPEN");
        System.out.println(invalidLock.open(243));
        System.out.println(invalidLock.open(777));

        invalidLock.lock();
        System.out.println("invalid TEST CHANGE COMBINATOR");
        System.out.println(invalidLock.changingCombination(100,777));
        System.out.println(invalidLock.open(777));

    }

}
