//package LAB3;
//
//import java.util.*;
//
//public class ComponentTest {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.nextLine();
//        Window window = new Window(name);
//        Component prev = null;
//        while (true) {
//            try {
//                int what = scanner.nextInt();
//                scanner.nextLine();
//                if (what == 0) {
//                    int position = scanner.nextInt();
//                    window.addComponent(position, prev);
//                } else if (what == 1) {
//                    String color = scanner.nextLine();
//                    int weight = scanner.nextInt();
//                    Component component = new Component(color, weight);
//                    prev = component;
//                } else if (what == 2) {
//                    String color = scanner.nextLine();
//                    int weight = scanner.nextInt();
//                    Component component = new Component(color, weight);
//                    prev.addComponent(component);
//                    prev = component;
//                } else if (what == 3) {
//                    String color = scanner.nextLine();
//                    int weight = scanner.nextInt();
//                    Component component = new Component(color, weight);
//                    prev.addComponent(component);
//                } else if(what == 4) {
//                    break;
//                }
//
//            } catch (InvalidPositionException e) {
//                System.out.println(e.getMessage());
//            }
//            scanner.nextLine();
//        }
//
//        System.out.println("=== ORIGINAL WINDOW ===");
//        System.out.println(window);
//        int weight = scanner.nextInt();
//        scanner.nextLine();
//        String color = scanner.nextLine();
//        window.changeColor(weight, color);
//        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
//        System.out.println(window);
//        int pos1 = scanner.nextInt();
//        int pos2 = scanner.nextInt();
//        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
//        window.swichComponents(pos1, pos2);
//        System.out.println(window);
//    }
//}
//class Component implements Comparable<Component>{
//    private String color;
//    private int weight;
//    Set<Component> inner;
//
//    public Component(String color, int weight) {
//        this.color = color;
//        this.weight = weight;
//        inner=new TreeSet<Component>();
//    }
//    void addComponent(Component component){
//        inner.add(component);
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//    void changeColor(int weight,String color){
//        if(this.weight<weight){
//            this.color=color;
//        }
//        for (Component con:inner){
//            if(con.weight<weight){
//                con.changeColor(weight,color);
//            } else if (con.inner.size()!=0) {
//                con.changeColor(weight,color);
//            }
//        }
//    }
//
//
//
//
//    public String getColor() {
//        return color;
//    }
//
//    static void createString(StringBuilder br,Component com,int indient){
//        for (int i = 0; i < indient; i++) {
//            br.append("---");
//        }
//        br.append(String.format("%d:%s\n", com.weight, com.color));
//        for (Component con : com.inner) {
//            createString(br,con,indient+1);
//        }
//    }
//
//
//    @Override
//    public int compareTo(Component o2) {
//        if(this.getWeight()> o2.getWeight()){
//            return 1;
//        } else if (this.getWeight()<o2.getWeight()) {
//            return -1;
//        }
//        if(this.color.charAt(0)>=o2.color.charAt(0)){
//            return 1;
//        }else{
//            return -1;
//        }
//    }
//
//}
//class InvalidPositionException extends Exception{
//    public InvalidPositionException(String s) {
//        super(s);
//    }
//}
//class Window{
//    String name;
//    HashMap<Integer,Component> components;
//
//
//    public Window(String name) {
//        this.name = name;
//         components=new HashMap<>();
//    }
//    void addComponent(int position, Component component) throws InvalidPositionException {
//        if(components.containsKey(position)){
//            throw new InvalidPositionException(String.format("Invalid position %d, alredy taken!",position));
//        }
//        components.put(position,component);
//    }
//    void changeColor(int weight, String color){
//        for (Map.Entry<Integer,Component> entry:components.entrySet()){
//            Component com=entry.getValue();
//            com.changeColor(weight,color);
//
//        }
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder br=new StringBuilder();
//        br.append(String.format("WINDOWS %s\n",name));
//        for (Map.Entry<Integer,Component> entry:components.entrySet()){
//            br.append(String.format("%d:",entry.getKey()));
//            Component.createString(br,entry.getValue(),0);
//        }
//        return br.toString();
//    }
//    void swichComponents(int pos1, int pos2){
//        Component one=components.remove(pos1);
//        Component two=components.remove(pos2);
//        components.put(pos1,two);
//        components.put(pos2,one);
//    }
//}
//
//
//// вашиот код овде