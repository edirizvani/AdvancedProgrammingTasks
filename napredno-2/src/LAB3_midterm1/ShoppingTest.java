//package LAB3;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.util.*;
//
//public class ShoppingTest {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        ShoppingCart cart = new ShoppingCart();
//
//        int items = Integer.parseInt(sc.nextLine());
//        for (int i = 0; i < items; i++) {
//            try {
//                cart.addItem(sc.nextLine());
//            } catch (InvalidOperationException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//
//        List<Integer> discountItems = new ArrayList<>();
//        int discountItemsCount = Integer.parseInt(sc.nextLine());
//        for (int i = 0; i < discountItemsCount; i++) {
//            discountItems.add(Integer.parseInt(sc.nextLine()));
//        }
//
//        int testCase = Integer.parseInt(sc.nextLine());
//        if (testCase == 1) {
//            cart.printShoppingCart(System.out);
//        } else if (testCase == 2) {
//            try {
//                cart.blackFridayOffer(discountItems, System.out);
//            } catch (InvalidOperationException e) {
//                System.out.println(e.getMessage());
//            }
//        } else {
//            System.out.println("Invalid test case");
//        }
//    }
//}
//class ShoppingCart{
//    Set<Item> items;
//    HashMap<Integer,Item> map;
//
//    public ShoppingCart() {
//        items=new TreeSet<>();
//        map=new HashMap<>();
//    }
//    void addItem(String itemData) throws InvalidOperationException {
//        String info[]=itemData.split(";");
//        if(info[0].equals("PS")){
//            if(Double.parseDouble(info[4])==0.0){
//                throw new InvalidOperationException(String.format("The quantity of the product with id %s can not be 0.",info[1]));
//            }else {
//                Item item=new Item_gram(info[1],info[2],Double.parseDouble(info[3]),Double.parseDouble(info[4]));
//                map.put(Integer.parseInt(info[1]),item);
//                items.add(item);
//
//            }
//        }else if (info[0].equals("WS")){
//            if(Integer.parseInt(info[4])==0){
//                throw new InvalidOperationException(String.format("The quantity of the product with id %s can not be 0.",info[1]));
//            }else {
//                Item item=new Item_whole(info[1],info[2],Integer.parseInt(info[3]),Double.parseDouble(info[4]));
//                map.put(Integer.parseInt(info[1]),item);
//                items.add(item);
//
//                }
//        }
//
//    }
//    void printShoppingCart(OutputStream os){
//        PrintWriter pr=new PrintWriter(os);
//        for (Item item:items){
//            pr.printf("%s - %.2f\n",item.productId,item.ComputedPrice());
//        }
//        pr.flush();
//    }
//    List<String> discounted=new ArrayList<>();
//    void blackFridayOffer(List<Integer> discountItems, OutputStream os) throws InvalidOperationException {
//        if(discountItems.size()==0){
//            throw new InvalidOperationException("There are no products with discount.");
//        }
//        PrintWriter pr=new PrintWriter(os);
//        List<String> discounted=new ArrayList<>();
//        for (Integer hello:discountItems){
//           if(map.containsKey(hello)){
//                Item item= map.get(hello);
//                double zasteda=item.ComputedPrice()-(item.ComputedPrice()*0.9);
//                item.setPrice(item.getPrice()*0.9);
//                discounted.add(String.format("%s - %.2f",item.productId,zasteda));
//           }
//        }
//        for (String sr :discounted){
//            pr.println(sr);
//        }
//        pr.flush();
//
//    }
//}
//abstract class Item implements Comparable<Item>{
//    String productId;
//    String productName;
//
//    public Item(String productId, String productName) {
//        this.productId = productId;
//        this.productName = productName;
//    }
//
//    @Override
//    public int compareTo(Item o) {
//        if(this.ComputedPrice()>o.ComputedPrice()){
//            return -1;
//        }else{
//            return 1;
//        }
//    }
//
//
//    public abstract void setPrice(double price);
//
//    abstract  public double getPrice();
//    abstract public double ComputedPrice();
//}
//class Item_whole extends Item{
//   private double price;
//    private int quantity;
//
//    public Item_whole(String productId, String productName, int quantity,double price)  {
//        super(productId, productName);
//
//        this.price = price;
//        this.quantity = quantity;
//    }
//
//    @Override
//    public void setPrice(double price) {
//
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public double ComputedPrice(){
//        return price*quantity;
//    }
//
//
//
//}
//class Item_gram extends Item{
//    private double kg_price;
//    private double weight;
//
//    public Item_gram(String productId, String productName, double kg_price, double weight) {
//        super(productId, productName);
//        this.kg_price = kg_price;
//        this.weight = weight;
//    }
//
//
//    @Override
//    public void setPrice(double price) {
//        this.kg_price = kg_price;
//    }
//
//    @Override
//    public double getPrice() {
//        return kg_price;
//    }
//
//    public double ComputedPrice(){
//        double in_kg=weight/1000.0;
//        return in_kg*kg_price;
//    }
//
//
//
//
//
//}
//
//class InvalidOperationException extends Exception{
//    public InvalidOperationException(String message) {
//        super(message);
//    }
//}