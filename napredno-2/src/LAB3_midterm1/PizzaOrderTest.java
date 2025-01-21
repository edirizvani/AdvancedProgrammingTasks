//package LAB3;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Scanner;
//
//class InvalidPizzaTypeException extends Exception{
//    public InvalidPizzaTypeException() {
//        super("InvalidPizzaTypeException");
//    }
//}
//class InvalidExtraTypeException extends Exception{
//    public InvalidExtraTypeException() {
//        super("InvalidExtraTypeException");
//    }
//}
//class  ItemOutOfStockException extends Exception{
//    public ItemOutOfStockException(String type) {
//        super("Item out of order:"+type);
//    }
//}
//interface Item{
//    int getPrice();
//    String getName();
//}
//enum Extra_Item_Type{
//    Coke, Ketchup
//}
//
//enum Pizza_Type{
//    Standard , Pepperoni , Vegetarian
//}
//class ExtraItem implements Item{
//    Extra_Item_Type type;
//    String name;
//
//    public ExtraItem(String type) throws InvalidExtraTypeException {
//
//        if(Extra_Item_Type.Ketchup.toString().equals(type)) {
//            this.type=Extra_Item_Type.Ketchup;
//        } else if (Extra_Item_Type.Coke.toString().equals(type)) {
//            this.type=Extra_Item_Type.Coke;
//        }else{
//            throw new InvalidExtraTypeException();
//        }
//        this.name=type;
//
//
//    }
//
//    @Override
//    public int getPrice() {
//
//       switch (type) {
//           case Ketchup : return  3;
//           case Coke : return  5;
//        };
//return 0;
//
//    }
//
//    @Override
//    public String getName() {
//      return name;
//    }
//}
//class PizzaItem implements Item{
//    String name;
//    Pizza_Type type;
//    public PizzaItem(String type) throws InvalidPizzaTypeException{
//
//        if(Pizza_Type.Standard.toString().equals(type) ) {
//            this.type=Pizza_Type.Standard;
//        }else if(Pizza_Type.Pepperoni.toString().equals(type)){
//            this.type=Pizza_Type.Pepperoni;
//        }else if(Pizza_Type.Vegetarian.toString().equals(type)){
//            this.type=Pizza_Type.Vegetarian;
//        }else{
//            throw new InvalidPizzaTypeException();
//        }
//        this.name=type;
//    }
//
//    @Override
//    public int getPrice() {
//         switch (type) {
//            case Standard:
//                return 10;
//             case Pepperoni :return  12;
//             case Vegetarian : return 8;
//
//        };
//         return 0;
//    }
//    @Override
//    public String getName() {
//        return name;
//    }
//}
//class ArrayIndexOutOfBoundsException extends Exception {
//    public ArrayIndexOutOfBoundsException(int idx) {
//        super("Index out of bound " + idx);
//    }
//}
//class OrderLockedException extends Exception {
//    public OrderLockedException() {
//        super("EmptyOrder OrderLockedException");
//    }
//}
//class Order{
//    boolean lock;
//    private final ArrayList<Item> items;
//    HashMap<String,Integer> map=new HashMap<>();
//
//    public Order() {
//        this.items = new ArrayList<>();
//        this.lock=false;
//    }
//
//    void addItem(Item item, int count) throws ItemOutOfStockException, OrderLockedException {
//        if(lock){
//            throw new OrderLockedException();
//        }
//        if(count>10){
//            throw new ItemOutOfStockException(item.getName());
//        }
//        if(map.containsKey(item.getName())){
//            map.replace(item.getName(),map.get(item.getName()),count);
//        }else {
//            map.put(item.getName(),count);
//            items.add(item);
//        }
//    }
//    void displayOrder() {
//        int i = 1;
//        int totalCost = 0;
//        StringBuilder orderOutput = new StringBuilder();
//
//        for (Item item : items) {
//            int quantity = map.get(item.getName());
//            int itemTotal = item.getPrice() * quantity;
//            totalCost += itemTotal;
//
//            // Format each item line
//            orderOutput.append(String.format("  %d.%-15sx%2d%5d$\n", i++, item.getName(), quantity, itemTotal));
//        }
//
//        // Append total cost line
//        orderOutput.append(String.format("Total:%-16s%5d$\n", "", totalCost));
//
//        System.out.print(orderOutput.toString());
//    }
//    int getPrice(){
//        return items.stream().mapToInt(item -> item.getPrice()*map.get(item.getName())).sum();
//    }
//    void removeItem(int idx) throws ArrayIndexOutOfBoundsException, OrderLockedException {
//        if(lock){
//            throw new OrderLockedException();
//        }
//        if(items.size()-1<idx || idx<0){
//            throw new ArrayIndexOutOfBoundsException(idx);
//        }
//        map.remove(items.get(idx).getName());
//        items.remove(idx);
//
//    }
//    void lock() throws OrderLockedException, EmptyOrder {
//        if(items.size()>0){
//            this.lock=true;
//        }else{
//            throw new EmptyOrder();
//        }
//
//    }
//
//}
//
//
//class EmptyOrder extends Exception {
//
//    public EmptyOrder() {
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//public class PizzaOrderTest {
//
//    public static void main(String[] args) {
//        Scanner jin = new Scanner(System.in);
//        int k = jin.nextInt();
//        if (k == 0) { //test Item
//            try {
//                String type = jin.next();
//                String name = jin.next();
//                Item item = null;
//                if (type.equals("Pizza")) item = new PizzaItem(name);
//                else item = new ExtraItem(name);
//                System.out.println(item.getPrice());
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//        }
//        if (k == 1) { // test simple order
//            Order order = new Order();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            jin.next();
//            System.out.println(order.getPrice());
//            order.displayOrder();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            System.out.println(order.getPrice());
//            order.displayOrder();
//        }
//        if (k == 2) { // test order with removing
//            Order order = new Order();
//            while (true) {
//                try {
//                    String type = jin.next();
//                    String name = jin.next();
//                    Item item = null;
//                    if (type.equals("Pizza")) item = new PizzaItem(name);
//                    else item = new ExtraItem(name);
//                    if (!jin.hasNextInt()) break;
//                    order.addItem(item, jin.nextInt());
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            jin.next();
//            System.out.println(order.getPrice());
//            order.displayOrder();
//            while (jin.hasNextInt()) {
//                try {
//                    int idx = jin.nextInt();
//                    order.removeItem(idx);
//                } catch (Exception e) {
//                    System.out.println(e.getClass().getSimpleName());
//                }
//            }
//            System.out.println(order.getPrice());
//            order.displayOrder();
//        }
//        if (k == 3) { //test locking & exceptions
//            Order order = new Order();
//            try {
//                order.lock();
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.addItem(new ExtraItem("Coke"), 1);
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.lock();
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//            try {
//                order.removeItem(0);
//            } catch (Exception e) {
//                System.out.println(e.getClass().getSimpleName());
//            }
//        }
//    }
//
//}