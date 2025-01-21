package aud2.lock.finki;

public class RefactoringExample {
    int countAllNumbersDivisibleWithDigitSum(int start,int end){
        int count=0;
        for(int i=start;i<end;i++) {
            int sumOfDigits = getSumOfDigits(i );
            if (i % sumOfDigits == 0) {
                count++;
            }
        }
        return count;
    }

    private static int getSumOfDigits(int temp) {
        int sumOfDigits = 0 ;
        while (temp > 0) {
            sumOfDigits += (temp % 10);
            temp /= 10;
        }
        return sumOfDigits;
    }


}
