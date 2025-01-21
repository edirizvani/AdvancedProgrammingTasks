package midterm2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class FactoryCanvas{
    static Shape createShape(String[] line) throws InvalidIDException, InvalidDimensionException {
        checkIfIdGoodForm(line[1]);
        double x = Double.parseDouble(line[2]);
        checkDimension(x);
        if(line[0].equals("1")){
            return new Circle(x);
        }else if(line[0].equals("2")){
            return new Square(x);
        }else if(line[0].equals("3")){
            double y = Double.parseDouble(line[3]);
            checkDimension(y);
            return new Rectangle(x,y);
        }
        return null;
    }

    private static void checkDimension(double x) throws InvalidDimensionException {
        if(x==0){
            throw new InvalidDimensionException("Dimension 0 is not allowed!");
        }
    }

    public static boolean checkIfIdGoodForm(String id) throws InvalidIDException {
        if(id.length()!=6){
            throw new InvalidIDException(String.format("ID %s is not valid",id));
        }
        for (int i = 0; i < id.length(); i++) {
            if(!Character.isDigit(id.charAt(i)) && !Character.isLetter(id.charAt(i))){
                throw new InvalidIDException(String.format("ID %s is not valid",id));
            }
        }
        return true;
    }
}
class InvalidIDException extends Exception {
    public InvalidIDException(String message) {
        super(message);
    }
}
class InvalidDimensionException extends Exception {
    public InvalidDimensionException(String message) {
        super(message);
    }
    //we dont read more here
}


class Canvas {
    Map<String, Set<Shape>> shapes = new HashMap<>();
//    Map<String,Double> coefs = new HashMap<>();
    Set<Shape> setShapes=new TreeSet<>(Comparator.comparing(Shape::getArea));

    void readShapes (InputStream is) throws InvalidDimensionException {
        Scanner scanner = new Scanner(is);
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().split(" ");
            Shape shape=null;
            try {
                shape=FactoryCanvas.createShape(line);
            } catch (InvalidIDException e) {
                System.out.println(e.getMessage());

            }
            if(shape!=null){
                shapes.putIfAbsent(line[1], new TreeSet<>(Comparator.comparing(Shape::getPerimeter)));
                shapes.get(line[1]).add(shape);
                setShapes.add(shape);
            }
//            coefs.put(line[0], 1.0);


        }
    }
    void scaleShapes (String userID, double coef){
//        coefs.replace(userID,coef);
        if(shapes.containsKey(userID)){
            shapes.get(userID).forEach(shape->{shape.setDimensions(coef);});
        }
    }
    void printAllShapes (OutputStream os){
        PrintWriter writer = new PrintWriter(os);
        setShapes.forEach(shape->{writer.print(shape.toString());});
        writer.flush();
    }
    void printByUserId (OutputStream os){
        PrintWriter writer = new PrintWriter(os);
        Comparator<Map.Entry<String,Set<Shape>>> comparator = Comparator.comparing(entry->entry.getValue().size());
        shapes.entrySet().stream().sorted(comparator.reversed().thenComparing(entry -> entry.getValue().stream().mapToDouble(Shape::getArea).sum())).forEach(entry->{
            writer.print(String.format("Shapes of user: %s\n",entry.getKey()));
            entry.getValue().forEach(shape->{writer.print(shape.toString());});
        });
        writer.flush();
    }
    void statistics (OutputStream os){
        PrintWriter writer = new PrintWriter(os);
        DoubleSummaryStatistics dss = setShapes.stream().mapToDouble(Shape::getArea).summaryStatistics();
        writer.print(String.format("count: %d\nsum: %.2f\nmin: %.2f\naverage: %.2f\nmax: %.2f",dss.getCount(),dss.getSum(),dss.getMin(),dss.getAverage(),dss.getMax()));
        writer.flush();
    }

}

interface Shape{
  double getArea();
  double getPerimeter();
  String getType();

    void setDimensions(double coef);
}

class Circle implements Shape {
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2*Math.PI * radius;
    }

    @Override
    public String getType() {
        return "CIRCLE";
    }

    @Override
    public void setDimensions(double coef) {
        radius = coef*radius;
    }

    public String toString() {
        return String.format("Circle -> Radius: %.2f Area: %.2f Perimeter: %.2f\n",radius,getArea(),getPerimeter());
    }
}
class Rectangle implements Shape{
    double x;
    double y;

    public Rectangle(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public double getArea() {
        return x*y;
    }

    @Override
    public double getPerimeter() {
        return 2*(x+y);
    }

    @Override
    public String getType() {
        return "RECTANGLE";
    }

    @Override
    public void setDimensions(double coef) {
        x=x*coef;
        y=y*coef;
    }

    @Override
    public String toString() {
        return String.format("Rectangle: -> Sides: %.2f, %.2f Area: %.2f Perimeter: %.2f\n",x,y,getArea(),getPerimeter());
    }

}
class Square implements Shape {
    double x;

    public Square(double width) {
        this.x = width;
    }
    @Override
    public double getArea() {
        return x*x;
    }

    @Override
    public double getPerimeter() {
        return 4*x;
    }

    @Override
    public String toString() {
        return String.format("Square: -> Side: %.2f Area: %.2f Perimeter: %.2f\n",x,getArea(),getPerimeter());
    }


    @Override
    public String getType() {
        return "SQUARE";
    }

    @Override
    public void setDimensions(double coef) {
        x=x*coef;
    }
}
public class CanvasTest {

    public static void main(String[] args) {
        Canvas canvas = new Canvas();

        System.out.println("READ SHAPES AND EXCEPTIONS TESTING");
        try {
            canvas.readShapes(System.in);
        } catch (InvalidDimensionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("BEFORE SCALING");
        canvas.printAllShapes(System.out);
        canvas.scaleShapes("123456", 1.5);
        System.out.println("AFTER SCALING");
        canvas.printAllShapes(System.out);

        System.out.println("PRINT BY USER ID TESTING");
        canvas.printByUserId(System.out);

        System.out.println("PRINT STATISTICS");
        canvas.statistics(System.out);
    }
}

