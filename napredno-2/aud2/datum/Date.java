package aud2.datum;

import java.util.Arrays;

public class Date {
    private static final int FIRST_YEAR=1800;
    private static final int LAST_YEAR=2500;
    private static final int YEAR_DAYS=365;
    public int days;
    private static final int[] months_year={0,31,28,31,30,31,30,31,31,30,31,30,31};//0 in first to access it better in forms of 1 month
    private static final int[] days_till_first_of_years;
    private static final int[] getDays_till_first_of_month;

    static {
        days_till_first_of_years=new int[LAST_YEAR-FIRST_YEAR+1];
        getDays_till_first_of_month=new int[12];
        for (int i = 1; i < 12; i++) {
            getDays_till_first_of_month[i]=getDays_till_first_of_month[i-1]+months_year[i];
        }
        for (int i = 1; i < days_till_first_of_years.length; i++) {
            int year=FIRST_YEAR+i;
            if((year%4==0 && year%100!=0) || (year%400==0)){
                days_till_first_of_years[i]=days_till_first_of_years[i-1]+YEAR_DAYS+1;
            }else {
                days_till_first_of_years[i]=days_till_first_of_years[i-1]+YEAR_DAYS;
            }
        }
    }


    public static int get_days_from_date(String date){
        int day=0,month=0,year=0;
        day=Integer.parseInt(date.substring(0,2));
        month=Integer.parseInt(date.substring(3,5));
        year=Integer.parseInt(date.substring(6,10));
        return (days_till_first_of_years[year-FIRST_YEAR]+getDays_till_first_of_month[month-1]+(day-1));

    }
    public void incrementDays(int how){
        days+=how;
    }
    public void subtractDays(int how){
        days-=how;
    }
    public Date(int day,int month,int year){
    if(isInValidDate(day,month,year)){
        throw new RuntimeException();
    }

    int total=(days_till_first_of_years[year-FIRST_YEAR]+getDays_till_first_of_month[month-1]+(day-1));
    if(month>=2 && (year%4==0 && year%100!=0) || (year%400==0)) {
        total++;
    }
    this.days=total;
    }
    public boolean isInValidDate(int day,int month,int year){
         if(day<1  || year<FIRST_YEAR || year>LAST_YEAR) {
             return true;
         } else if (month==2) {
             if((year%4==0 && year%100!=100) || (year%400==0)){
                 if(day>29){
                     return true;
                 }
             }else {
                 if(day>28){
                     return true;
                 }
             }
         } else if(day>months_year[month]) {
             return true;
         }
         return false;

    }

    @Override
    public String toString() {
       int alldays=days,i=0;
        for (i=0; i < days_till_first_of_years.length; i++) {
            if(alldays<=days_till_first_of_years[i]) break;
        }

        int years=i-1,j;
        int current=years+FIRST_YEAR;
        if((current%4==0 && current%100!=0) || (current%400==0)){
            alldays--;
        }
        alldays-=days_till_first_of_years[years];
        for (j=0; j < getDays_till_first_of_month.length; j++) {
            if (alldays<=getDays_till_first_of_month[j]) break;
        }
        int months=j-1;
        alldays-=getDays_till_first_of_month[months];
        StringBuilder br=new StringBuilder("");
        br.append((++alldays)+":"+(++months)+":"+(years+FIRST_YEAR));
        return br.toString();
    }

    public static void main(String[] args) {
        System.out.println(get_days_from_date("26.02.1901"));
        Date date=new Date(28,2,2023);
        date.subtractDays(60);
        System.out.println(date);
        date.incrementDays(100);
        System.out.println(date);
    }


}
