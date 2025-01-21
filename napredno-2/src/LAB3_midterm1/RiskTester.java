package LAB3_midterm1;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class RiskTester {
    public static void main(String[] args) {
        Risk risk = new Risk();
        risk.processAttacksData(System.in);
    }
}
class Risk{

    void processAttacksData (InputStream is){
        Scanner sc=new Scanner(new InputStreamReader(is));
        int times_won=0;
        while (sc.hasNextLine()){
            String[] info=sc.nextLine().split(";");

            List<Integer> attacker=Arrays.stream(info[0].split(" ")).mapToInt(Integer::parseInt).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
            List<Integer> receiver=Arrays.stream(info[1].split(" ")).mapToInt(Integer::parseInt).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());

           int flag=0;
            for (int i = 0; i < 3; i++) {
                if(attacker.get(i)<=receiver.get(i)){
                    flag=1;
                    break;
                }
            }
            if(flag==0){
                times_won++;
            }


        }
        System.out.println(times_won);

    }
}