package lab4;

import java.util.*;

public class SuperStringTest {

    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        if (  k == 0 ) {
            SuperString s = new SuperString();
            while ( true ) {
                int command = jin.nextInt();
                if ( command == 0 ) {//append(String s)
                    s.append(jin.next());
                }
                if ( command == 1 ) {//insert(String s)
                    s.insert(jin.next());
                }
                if ( command == 2 ) {//contains(String s)
                    System.out.println(s.contains(jin.next()));
                }
                if ( command == 3 ) {//reverse()
                    s.reverse();
                }
                if ( command == 4 ) {//toString()
                    System.out.println(s);
                }
                if ( command == 5 ) {//removeLast(int k)
                    s.removeLast(jin.nextInt());
                }
                if ( command == 6 ) {//end
                    break;
                }
            }
        }
    }

}
class SuperString{
    LinkedList<String> list;
    Stack<Integer> stack_list;
    public SuperString() {
        list=new LinkedList<>();
        stack_list=new Stack<>();
    }
    void append(String s){
        list.addLast(s);
        stack_list.push(list.size()-1);
    }
    void insert(String s){
        list.addFirst(s);
        for (int i = 0; i < stack_list.size(); i++) {
            stack_list.set(i, stack_list.get(i) + 1);
        }
        stack_list.push(0);
    }

    boolean contains(String s){
        StringBuilder list_new= new StringBuilder();
        for (String str:list) {
            list_new.append(str);
        }
        return list_new.toString().contains(s);
    }
    public void reverse() {
        LinkedList<String> reversedList = new LinkedList<>();

        for (int i=0,j=list.size()-1;i<list.size();i++,j--) {
            reversedList.addFirst(new StringBuilder(list.get(i)).reverse().toString());
        }
        Stack<Integer> updatedStack = new Stack<>();
        int n = list.size();
        while (!stack_list.isEmpty()) {
            updatedStack.push(n - 1 - stack_list.pop());
        }
        stack_list = updatedStack;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String str : list) {
            result.append(str);
        }
        return result.toString();
    }

    public void removeLast(int k) {
        if (k <= 0 || k > list.size()) {
            return;
        }

        for (int i = 0; i < k; i++) {
            int index=stack_list.pop();
            list.remove(index);
        }
    }
}