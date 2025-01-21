////package LAB3;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//import java.util.LinkedList;
//class ArrayIndexOutOfBoundsException extends Exception{
//    public ArrayIndexOutOfBoundsException(String str) {
//        super(str);
//    }
//}
//class ResizableArray<T>{
//
//    private T[] arrayList;
//    private int count;
//    @SuppressWarnings("unchecked")
//    public ResizableArray() {
//        arrayList=(T[]) new Object[1000];
//        count=0;
//    }
//    void addElement(T element){
//        if(count==arrayList.length){
//            arrayList= Arrays.copyOf(arrayList,count+1000);
//        }
//        arrayList[count]=element;
//        count++;
//
//
//    }
//    boolean removeElement(T element){
//        for (int i=0;i<count;i++){
//            if(arrayList[i].equals(element)){
//                for (int j = i; j <count-1 ; j++) {
//                    arrayList[j]=arrayList[j+1];
//                }
//                arrayList[count-1]=null;
//                count--;
//                if(arrayList.length-count>100){
//                    arrayList=Arrays.copyOf(arrayList,count);
//                }
//
//                return true;
//            }
//        }
//
//        return false;
//    }
//    boolean contains(T element){
//        for (int i = 0; i < count; i++) {
//            if(arrayList[i].equals(element)){
//                return true;
//            }
//        }
//        return false;
//    }
//    Object[] toArray(){
//        return arrayList;
//    }
//    boolean isEmpty(){
//        return count==0;
//    }
//    int count(){
//        return count;
//    }
//    T elementAt(int idx) throws ArrayIndexOutOfBoundsException {
//        if(count<=idx){
//            throw new ArrayIndexOutOfBoundsException(String.format(String.format("%d",count)));//"елементите во полето се наоѓаат на позиции [0, %d])",
//        }
//        return arrayList[idx];
//    }
//    static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src){
//        int count= src.count;
//        for (int i = 0; i < count; i++) {
//            try {
//                dest.addElement(src.elementAt(i));
//                //System.out.println("element at "+i+" with value"+src.elementAt(i)+"from src added to dect");
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//}
//class IntegerArray extends ResizableArray<Integer>{
//
//
//    public double sum() {
//       double total=0;
//        for (int i = 0; i < count(); i++) {
//            try {
//                total+=elementAt(i);
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return total;
//    }
//
//    public int countNonZero() {
//        int nonZeroCount = 0;
//        for (int i = 0; i < count(); i++) {
//            try {
//                if (elementAt(i) != 0) {
//                    nonZeroCount++;
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        return nonZeroCount;
//    }
//
//    public double mean() {
//        if(count()==0){
//            return 0;
//        }
//        return (double) sum()/count();
//    }
//
//    public IntegerArray distinct() {
//        IntegerArray o =new IntegerArray();
//        for (int i = 0; i < count(); i++) {
//            try {
//                if(!o.contains(this.elementAt(i))){
//                    o.addElement(elementAt(i));
//                }
//            } catch (ArrayIndexOutOfBoundsException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return o;
//    }
//
//    public IntegerArray increment(int offset) {
//        IntegerArray o = new IntegerArray();
//        for (int i = 0; i < count(); i++) {
//            try {
//                o.addElement(this.elementAt(i) + offset);
//            } catch (ArrayIndexOutOfBoundsException e) {
//                System.out.println("Index out of bounds at i = " + i + ", count = " + count());
//                e.printStackTrace();  // Print the full stack trace for debugging.
//            }
//        }
//        return o;
//    }
//}
//
//
//public class ResizableArrayTest {
//
//    public static void main(String[] args) {
//        Scanner jin = new Scanner(System.in);
//        int test = jin.nextInt();
//        if ( test == 0 ) { //test ResizableArray on ints
//            ResizableArray<Integer> a = new ResizableArray<Integer>();
//            System.out.println(a.count());
//            int first = jin.nextInt();
//            a.addElement(first);
//            System.out.println(a.count());
//            int last = first;
//            while ( jin.hasNextInt() ) {
//                last = jin.nextInt();
//                a.addElement(last);
//            }
//            System.out.println(a.count());
//            System.out.println(a.contains(first));
//            System.out.println(a.contains(last));
//            System.out.println(a.removeElement(first));
//            System.out.println(a.contains(first));
//            System.out.println(a.count());
//        }
//        if ( test == 1 ) { //test ResizableArray on strings
//            ResizableArray<String> a = new ResizableArray<String>();
//            System.out.println(a.count());
//            String first = jin.next();
//            a.addElement(first);
//            System.out.println(a.count());
//            String last = first;
//            for ( int i = 0 ; i < 4 ; ++i ) {
//                last = jin.next();
//                a.addElement(last);
//            }
//            System.out.println(a.count());
//            System.out.println(a.contains(first));
//            System.out.println(a.contains(last));
//            System.out.println(a.removeElement(first));
//            System.out.println(a.contains(first));
//            System.out.println(a.count());
//            ResizableArray<String> b = new ResizableArray<String>();
//            ResizableArray.copyAll(b, a);
//            System.out.println(b.count());
//            System.out.println(a.count());
//            System.out.println(a.contains(first));
//            System.out.println(a.contains(last));
//            System.out.println(b.contains(first));
//            System.out.println(b.contains(last));
//            ResizableArray.copyAll(b, a);
//            System.out.println(b.count());
//            System.out.println(a.count());
//            System.out.println(a.contains(first));
//            System.out.println(a.contains(last));
//            System.out.println(b.contains(first));
//            System.out.println(b.contains(last));
//            System.out.println(b.removeElement(first));
//            System.out.println(b.contains(first));
//            System.out.println(b.removeElement(first));
//            System.out.println(b.contains(first));
//
//            System.out.println(a.removeElement(first));
//            ResizableArray.copyAll(b, a);
//            System.out.println(b.count());
//            System.out.println(a.count());
//            System.out.println(a.contains(first));
//            System.out.println(a.contains(last));
//            System.out.println(b.contains(first));
//            System.out.println(b.contains(last));
//            System.out.println();
//
//        }
//        if ( test == 2 ) { //test IntegerArray
//            IntegerArray a = new IntegerArray();
//            System.out.println(a.isEmpty());
//            while ( jin.hasNextInt() ) {
//                a.addElement(jin.nextInt());
//            }
//            jin.next();
//            System.out.println(a.sum());
//            System.out.println(a.mean());
//            System.out.println(a.countNonZero());
//            System.out.println(a.count());
//            IntegerArray b = a.distinct();
//            System.out.println(b.sum());
//            IntegerArray c = a.increment(5);
//            System.out.println(c.sum());
//            if ( a.sum() > 100 )
//                ResizableArray.copyAll(a, a);
//            else
//                ResizableArray.copyAll(a, b);
//            System.out.println(a.sum());
//            System.out.println(a.removeElement(jin.nextInt()));
//            System.out.println(a.sum());
//            System.out.println(a.removeElement(jin.nextInt()));
//            System.out.println(a.sum());
//            System.out.println(a.removeElement(jin.nextInt()));
//            System.out.println(a.sum());
//            System.out.println(a.contains(jin.nextInt()));
//            System.out.println(a.contains(jin.nextInt()));
//
//        }
//        if ( test == 3 ) { //test insanely large arrays
//            LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
//            for ( int w = 0 ; w < 500 ; ++w ) {
//                ResizableArray<Integer> a = new ResizableArray<Integer>();
//                int k =  2000;
//                int t =  1000;
//                for ( int i = 0 ; i < k ; ++i ) {
//                    a.addElement(i);
//                }
//
//                a.removeElement(0);
//                for ( int i = 0 ; i < t ; ++i ) {
//                    a.removeElement(k-i-1);
//                }
//                resizable_arrays.add(a);
//            }
//            System.out.println("You implementation finished in less then 3 seconds, well done!");
//        }
//    }
//
//}
