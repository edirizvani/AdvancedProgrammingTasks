//package LAB2.second;


import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
class InsufficientElementsException extends Exception {
    public InsufficientElementsException(String s) {
        super(s);
    }
}

class InvalidRowNumberException extends Exception {
    public InvalidRowNumberException() {
        super("Invalid row number");
    }
}

class InvalidColumnNumberException extends Exception {
    public InvalidColumnNumberException() {
        super("Invalid column number");
    }
}
final class DoubleMatrix{

    final double[][]  a;


    DoubleMatrix(double[] arr,int m, int n) throws InsufficientElementsException {
        if(arr.length<m*n) {
            throw new InsufficientElementsException("Insufficient number of elements");
        }
        a= new double[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                this.a[i][j]=arr[arr.length-(m*n)+(i*n)+j];

            }
        }
    }
    public String getDimensions(){
        return String.format("[%d x %d]",a.length,a[0].length);
    }
    public int rows(){
        return a.length;
    }
    public int columns(){
        return a[0].length;
    }
    public double maxElementAtRow(int row) throws InvalidRowNumberException {
        if(row-1>a.length || row-1<0){
            throw new InvalidRowNumberException();
        }
//        double max=a[row-1][0];
//        for (int j = 1; j < a[0].length; j++) {
//            if(a[row-1][j]>max){
//                max=a[row-1][j];
//            }
//        }
//        return max;
        return Arrays.stream(a[row-1]).max().orElseThrow(()->new InvalidRowNumberException());
    }
    public double maxElementAtColumn(int column) throws InvalidColumnNumberException {
        if(column>a[0].length || column<0){
            throw new InvalidColumnNumberException();
        }
//        double max=a[0][column-1];
//        for (int i = 1; i < a.length; i++) {
//            if(a[i][column-1]>max){
//                max=a[i][column-1];
//            }
//        }
        return Arrays.stream(a).mapToDouble(row->row[column-1]).max().orElseThrow(()->new InvalidColumnNumberException());
    }
    public double sum(){
//        double sum=0;
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[0].length; j++) {
//                sum+=a[i][j];
//            }
//        }
        return Arrays.stream(a).flatMapToDouble(Arrays::stream).sum();
    }
    public  double[] toSortedArray(){
        double[] newArr = new double[a.length * a[0].length];
        int index = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                newArr[index++] = a[i][j];
            }
        }
        for (int i = 0; i < newArr.length - 1; i++) {
            for (int j = 0; j < newArr.length - 1 - i; j++) {
                if (newArr[j] < newArr[j + 1]) { // Change to > for ascending
                    // Swap
                    double temp = newArr[j];
                    newArr[j] = newArr[j + 1];
                    newArr[j + 1] = temp;
                }
            }
        }
        return newArr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleMatrix that = (DoubleMatrix) o;
        // Check dimensions first
        if (this.rows() != that.rows() || this.columns() != that.columns()) return false;

        // Check contents of the 2D arrays
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(this.a[i], that.a[i])) {
                return false; // If any row does not match, return false
            }
        }
        return true; // All rows match
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(a);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int j=0;
        for (double[] row : a) {
            for (int i = 0; i < row.length; i++) {
                sb.append(String.format("%.2f", row[i]));
                if (i < row.length - 1) { // Avoid adding tab after the last element in the row
                    sb.append("\t");
                }
            }
            if(j==a.length-1){
            }else{
                sb.append(System.lineSeparator()); // New line after each row
            }
            ++j;
        }
        return sb.toString();
    }
}









class MatrixReader{

    public static DoubleMatrix read(ByteArrayInputStream in){
        Scanner scanner=new Scanner(in);
        int n=scanner.nextInt();
        int m= scanner.nextInt();
        scanner.nextLine();
        ArrayList<Double> arr=new ArrayList<>();
        while(scanner.hasNextDouble()){
            arr.add(scanner.nextDouble());
        }
        DoubleMatrix fm=null;
        try {
            fm= new DoubleMatrix(arr.stream().mapToDouble(Double::doubleValue).toArray(),n,m);
        } catch (InsufficientElementsException e) {
            throw new RuntimeException(e);
        }
        return fm;
    }

}



public class DoubleMatrixTester {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int tests = scanner.nextInt();
        DoubleMatrix fm = null;

        double[] info = null;

        DecimalFormat format = new DecimalFormat("0.00");

        for (int t = 0; t < tests; t++) {

            String operation = scanner.next();

            switch (operation) {
                case "READ": {
                    int N = scanner.nextInt();
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    double[] f = new double[N];

                    for (int i = 0; i < f.length; i++)
                        f[i] = scanner.nextDouble();

                    try {
                        fm = new DoubleMatrix(f, R, C);
                        info = Arrays.copyOf(f, f.length);

                    } catch (InsufficientElementsException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }

                    break;
                }

                case "INPUT_TEST": {
                    int R = scanner.nextInt();
                    int C = scanner.nextInt();

                    StringBuilder sb = new StringBuilder();

                    sb.append(R + " " + C + "\n");

                    scanner.nextLine();

                    for (int i = 0; i < R; i++)
                        sb.append(scanner.nextLine() + "\n");

                    fm = MatrixReader.read(new ByteArrayInputStream(sb
                            .toString().getBytes()));

                    info = new double[R * C];
                    Scanner tempScanner = new Scanner(new ByteArrayInputStream(sb
                            .toString().getBytes()));
                    tempScanner.nextDouble();
                    tempScanner.nextDouble();
                    for (int z = 0; z < R * C; z++) {
                        info[z] = tempScanner.nextDouble();
                    }

                    tempScanner.close();

                    break;
                }

                case "PRINT": {
                    System.out.println(fm.toString());
                    break;
                }

                case "DIMENSION": {
                    System.out.println("Dimensions: " + fm.getDimensions());
                    break;
                }

                case "COUNT_ROWS": {
                    System.out.println("Rows: " + fm.rows());
                    break;
                }

                case "COUNT_COLUMNS": {
                    System.out.println("Columns: " + fm.columns());
                    break;
                }

                case "MAX_IN_ROW": {
                    int row = scanner.nextInt();
                    try {
                        System.out.println("Max in row: "
                                + format.format(fm.maxElementAtRow(row)));
                    } catch (InvalidRowNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "MAX_IN_COLUMN": {
                    int col = scanner.nextInt();
                    try {
                        System.out.println("Max in column: "
                                + format.format(fm.maxElementAtColumn(col)));
                    } catch (InvalidColumnNumberException e) {
                        System.out.println("Exception caught: " + e.getMessage());
                    }
                    break;
                }

                case "SUM": {
                    System.out.println("Sum: " + format.format(fm.sum()));
                    break;
                }

                case "CHECK_EQUALS": {
                    int val = scanner.nextInt();

                    int maxOps = val % 7;

                    for (int z = 0; z < maxOps; z++) {
                        double work[] = Arrays.copyOf(info, info.length);

                        int e1 = (31 * z + 7 * val + 3 * maxOps) % info.length;
                        int e2 = (17 * z + 3 * val + 7 * maxOps) % info.length;

                        if (e1 > e2) {
                            double temp = work[e1];
                            work[e1] = work[e2];
                            work[e2] = temp;
                        }

                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(work, fm.rows(),
                                fm.columns());
//                        System.out.println("HashCode of f1: " + f1.hashCode());
//                        System.out.println("HashCode of f2: " + f2.hashCode());(f1.hashCode() == f2.hashCode() &&
                        System.out
                                .println("Equals check 1: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }

                    if (maxOps % 2 == 0) {
                        DoubleMatrix f1 = fm;
                        DoubleMatrix f2 = new DoubleMatrix(new double[]{3.0, 5.0,
                                7.5}, 1, 1);

                        System.out
                                .println("Equals check 2: "
                                        + f1.equals(f2)
                                        + " "
                                        + f2.equals(f1)
                                        + " "
                                        + (f1.hashCode() == f2.hashCode() && f1
                                        .equals(f2)));
                    }//

                    break;
                }

                case "SORTED_ARRAY": {
                    double[] arr = fm.toSortedArray();

                    String arrayString = "[";

                    if (arr.length > 0)
                        arrayString += format.format(arr[0]) + "";

                    for (int i = 1; i < arr.length; i++)
                        arrayString += ", " + format.format(arr[i]);

                    arrayString += "]";

                    System.out.println("Sorted array: " + arrayString);
                    break;
                }

            }

        }

        scanner.close();
    }
}
