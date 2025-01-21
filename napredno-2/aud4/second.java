package aud4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class second {
    public static void main(String[] args) {
        ArrayList<Integer> Arraylist=new ArrayList<>();

        long end,start=System.currentTimeMillis();

        for (int i = 0; i < 100000000; i++) {
            Arraylist.add(i);
        }
        end=System.currentTimeMillis();
        System.out.println(Arraylist.size());
        System.out.println(end-start);

    }
}
