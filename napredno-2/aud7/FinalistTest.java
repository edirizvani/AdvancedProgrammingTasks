package aud7;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


class InvalidPickerArguments extends Exception{
    public InvalidPickerArguments(String message) {
        super(message);
    }
}

class FinalisttPicker{
    int finalists;
    static Random RANDOM=new Random();

    public FinalisttPicker(int finalists) {
        this.finalists = finalists;
    }

    public List<Integer> pick(int n) throws InvalidPickerArguments {

        if(n>finalists || n<=0){
            throw new InvalidPickerArguments("The number n cannot exceed the number of finalists");
        }

        List<Integer> pickedFinalists=new ArrayList<>();
        return RANDOM.ints(2*n,1,finalists+1).boxed().distinct().limit(n).collect(Collectors.toList());
//        while(pickedFinalists.size()!=n){
//            int pick=RANDOM.nextInt(finalists)+1;
//            if(!pickedFinalists.contains(pick)){
//                pickedFinalists.add(pick);
//            }
//        }
   //     return pickedFinalists;
    }
}


public class FinalistTest {
    public static void main(String[] args) {
        FinalisttPicker picker=new FinalisttPicker(5);
        try {
            System.out.println(picker.pick(3));
        } catch (InvalidPickerArguments e) {
            System.out.println(e.getMessage());
        }
    }
}
