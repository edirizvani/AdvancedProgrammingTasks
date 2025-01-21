//package LAB3;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.PriorityQueue;
//import java.util.Scanner;
//
//enum Color {
//    RED, GREEN, BLUE
//}
//enum Type {
//    C,R
//}
//interface Scalable{
//    void scale(float scaleFactor);
//
//}
//interface Stackable{
//    float weight();
//}
//
//abstract class Shape implements Stackable,Scalable{
//    private String id;
//    private Color color;
//    private Type type;
//
//    public Shape(String id, Color color) {
//        this.id = id;
//        this.color = color;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    public Type getType() {
//        return type;
//    }
//}
//class Circle extends Shape{
//
//    private float radius;
//
//    public Circle(String id, Color color, float radius) {
//        super(id, color);
//        this.radius = radius;
//    }
//
//    @Override
//    public void scale(float scaleFactor) {
//        radius*=scaleFactor;
//    }
//
//    @Override
//    public float weight() {
//        return (float) (Math.PI*(radius*radius));
//    }
//
//    @Override
//    public String toString() {
//        return String.format("C: %-5s%-10s%10.2f", getId(), getColor(), weight());
//    }
//}
//class Rectangle extends Shape{
//    private float width;
//    private float height;
//
//    public Rectangle(String id, Color color, float width, float height) {
//        super(id, color);
//        this.width = width;
//        this.height = height;
//    }
//
//    @Override
//    public void scale(float scaleFactor) {
//        height*=scaleFactor;
//        width*=scaleFactor;
//    }
//
//    @Override
//    public float weight() {
//        return width*height;
//    }
//    @Override
//    public String toString() {
//        return String.format("R: %-5s%-10s%10.2f", getId(), getColor(), weight());
//    }
//}
//
//
//class Canvas{
//    ArrayList<Shape> shapes;
//
//    public Canvas() {
//        shapes=new ArrayList<Shape>();
//    }
//    void add(String id, Color color, float radius){
//        Circle c=new Circle(id,color,radius);
//
//        for (int i = 0; i < shapes.size(); i++) {
//            if(c.weight()>shapes.get(i).weight()){
//                shapes.add(i,c);
//                return;
//            }
//        }
//        shapes.add(c);
//
//    }
//
//    void add(String id, Color color, float width, float height){
//
//        Rectangle c=new Rectangle(id,color,width,height);
//        for (int i = 0; i < shapes.size(); i++) {
//            if(c.weight()>shapes.get(i).weight()){
//                shapes.add(i,c);
//                return;
//            }
//        }
//        shapes.add(c);
//    }
//    void add(Shape c){
//        for (int i = 0; i < shapes.size(); i++) {
//            if(c.weight()>shapes.get(i).weight()){
//                shapes.add(i,c);
//                return;
//            }
//        }
//        shapes.add(c);
//    }
//    Shape getElementId(String id){
//        for(Shape shape:shapes){
//            if(shape.getId().equals(id)){
//                return shape;
//            }
//        }
//        return null;
//    }
//
//    public void scale(String id,float scaleFactor) {
//        Shape now= getElementId(id);
//        if(now==null){
//            System.out.println("We don't have that id");
//        }else{
//            shapes.remove(now);
//            now.scale(scaleFactor);
//            this.add(now);
//        }
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder br=new StringBuilder();
//
//        // Iterate over the temporary queue and print each shape
//        for (int i=0;i<shapes.size();i++) {
//            br.append(shapes.get(i).toString()).append("\n");
//        }
//        return br.toString();
//    }
//}
//public class ShapesTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Canvas canvas = new Canvas();
//        while (scanner.hasNextLine()) {
//            String line = scanner.nextLine();
//            String[] parts = line.split(" ");
//            int type = Integer.parseInt(parts[0]);
//            String id = parts[1];
//            if (type == 1) {
//                Color color = Color.valueOf(parts[2]);
//                float radius = Float.parseFloat(parts[3]);
//                canvas.add(id, color, radius);
//            } else if (type == 2) {
//                Color color = Color.valueOf(parts[2]);
//                float width = Float.parseFloat(parts[3]);
//                float height = Float.parseFloat(parts[4]);
//                canvas.add(id, color, width, height);
//            } else if (type == 3) {
//                float scaleFactor = Float.parseFloat(parts[2]);
//                System.out.println("ORIGNAL:");
//                System.out.print(canvas);
//                canvas.scale(id, scaleFactor);
//                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
//                System.out.print(canvas);
//            }
//
//        }
//    }
//}
