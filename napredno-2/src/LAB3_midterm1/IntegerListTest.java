//package LAB3;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
//class IntegerList{
//    ArrayList<Integer> list;
//
//    public IntegerList() {
//        this.list = new ArrayList<>();
//    }
//    public IntegerList(Integer...numbers){
//        list=new ArrayList<>();
//        list.addAll(Arrays.asList(numbers));
//
//    }
//    void add(int el, int idx){
//        if(idx<=list.size() && idx>=0){
//            list.add(idx,el);
//        } else if (idx>list.size()) {
//            for (int i = list.size(); i <idx ; i++) {
//                list.add(0);
//            }
//            list.add(el);
//        }else{
//            System.out.println("Index I think is negative");
//        }
//    }
//    int remove(int idx){
//        if(idx<0 || idx>=list.size()){
//            throw new ArrayIndexOutOfBoundsException("Out of bound");
//        }
//        int value=list.get(idx);
//        list.remove(idx);
//        return value;
//    }
//    void set(int el, int idx){
//        if(idx<0 || idx>=list.size()){
//            throw new ArrayIndexOutOfBoundsException("Out of bound");
//        }
//        list.set(idx,el);
//    }
//    int get(int idx){
//        if(idx<0 || idx>=list.size()){
//            throw new ArrayIndexOutOfBoundsException("Out of bound");
//        }
//        return list.get(idx);
//    }
//    int size(){
//        return list.size();
//    }
//    int count(int el){
//        int count=0;
//        for(Integer in:list){
//            if(in==el){
//                count++;
//            }
//        }
//        return count;
//    }
//    void removeDuplicates(){
//        for (int i = 0; i < list.size(); i++) {
//            for (int j = i+1; j < list.size(); j++) {
//                if(list.get(i)==list.get(j)){
//                    list.remove(i);
//                    i--;
//                    break;
//                }
//            }
//        }
//    }
//    int sumFirst(int k){
////        if(k<0 || k>list.size()){
////            throw new ArrayIndexOutOfBoundsException("Out of bound");
////        }
//        int sum=0;
//        for (int i = 0; i < k; i++) {
//            if(i==list.size()){
//                return sum;
//            }
//            sum+= list.get(i);
//        }
//        return sum;
//    }
//    int sumLast(int k){
////        if(k<0 || k>list.size()){
////            throw new ArrayIndexOutOfBoundsException("Out of bound");
////        }
//        int sum=0,c=0;
//        for (int i = list.size()-1; i >= 0; i--) {
//            if(i==list.size() || i<0 || c==k){
//                return sum;
//            }
//            c++;
//            sum+= list.get(i);
//        }
//        return sum;
//    }
//    void shiftRight(int idx, int k){
//        k = k % list.size();
//        int element=list.get(idx);
//        int new_index=(idx+k)%list.size();
//        list.remove(idx);
//        list.add(new_index,element);
//    }
//    void shiftLeft(int idx, int k) {
//        if (idx < 0 || idx >= list.size()) {
//            throw new ArrayIndexOutOfBoundsException("Invalid index: " + idx);
//        }
//        k = k % list.size();
//        int element=list.get(idx);
//        int new_index=(idx-k+ list.size())%list.size();
//        list.remove(idx);
//        list.add(new_index,element);
//
//    }
//   IntegerList addValue(int value){
//        IntegerList neww=new IntegerList();
//       for (int i = 0; i < list.size(); i++) {
//           neww.add(list.get(i)+value,i);
//       }
//       return neww;
//   }
//
//
//}
//
//public class IntegerListTest {
//
//    public static void main(String[] args) {
//        Scanner jin = new Scanner(System.in);
//        int k = jin.nextInt();
//        if ( k == 0 ) { //test standard methods
//            int subtest = jin.nextInt();
//            if ( subtest == 0 ) {
//                IntegerList list = new IntegerList();
//                while ( true ) {
//                    int num = jin.nextInt();
//                    if ( num == 0 ) {
//                        list.add(jin.nextInt(), jin.nextInt());
//                    }
//                    if ( num == 1 ) {
//                        list.remove(jin.nextInt());
//                    }
//                    if ( num == 2 ) {
//                        print(list);
//                    }
//                    if ( num == 3 ) {
//                        break;
//                    }
//                }
//            }
//            if ( subtest == 1 ) {
//                int n = jin.nextInt();
//                Integer a[] = new Integer[n];
//                for ( int i = 0 ; i < n ; ++i ) {
//                    a[i] = jin.nextInt();
//                }
//                IntegerList list = new IntegerList(a);
//                print(list);
//            }
//        }
//        if ( k == 1 ) { //test count,remove duplicates, addValue
//            int n = jin.nextInt();
//            Integer a[] = new Integer[n];
//            for ( int i = 0 ; i < n ; ++i ) {
//                a[i] = jin.nextInt();
//            }
//            IntegerList list = new IntegerList(a);
//            while ( true ) {
//                int num = jin.nextInt();
//                if ( num == 0 ) { //count
//                    System.out.println(list.count(jin.nextInt()));
//                }
//                if ( num == 1 ) {
//                    list.removeDuplicates();
//                }
//                if ( num == 2 ) {
//                    print(list.addValue(jin.nextInt()));
//                }
//                if ( num == 3 ) {
//                    list.add(jin.nextInt(), jin.nextInt());
//                }
//                if ( num == 4 ) {
//                    print(list);
//                }
//                if ( num == 5 ) {
//                    break;
//                }
//            }
//        }
//        if ( k == 2 ) { //test shiftRight, shiftLeft, sumFirst , sumLast
//            int n = jin.nextInt();
//            Integer a[] = new Integer[n];
//            for ( int i = 0 ; i < n ; ++i ) {
//                a[i] = jin.nextInt();
//            }
//            IntegerList list = new IntegerList(a);
//            while ( true ) {
//                int num = jin.nextInt();
//                if ( num == 0 ) { //count
//                    list.shiftLeft(jin.nextInt(), jin.nextInt());
//                }
//                if ( num == 1 ) {
//                    list.shiftRight(jin.nextInt(), jin.nextInt());
//                }
//                if ( num == 2 ) {
//                    System.out.println(list.sumFirst(jin.nextInt()));
//                }
//                if ( num == 3 ) {
//                    System.out.println(list.sumLast(jin.nextInt()));
//                }
//                if ( num == 4 ) {
//                    print(list);
//                }
//                if ( num == 5 ) {
//                    break;
//                }
//            }
//        }
//    }
//
//    public static void print(IntegerList il) {
//        if ( il.size() == 0 ) System.out.print("EMPTY");
//        for ( int i = 0 ; i < il.size() ; ++i ) {
//            if ( i > 0 ) System.out.print(" ");
//            System.out.print(il.get(i));
//        }
//        System.out.println();
//    }
//
//}