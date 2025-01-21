////package midterm2;
//
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.*;
//import java.util.stream.Collectors;
//
//class Discounts{
//  List<Store> stores;
//
//    public Discounts() {
//        this.stores = new ArrayList<>();
//    }
//    static Comparator<Store> descendingPopustName = Comparator.comparingDouble(Store::get_averagePopust).reversed().thenComparing(Store::getName);
//    static Comparator<Store> ascendingAbsolutePopustName = Comparator.comparingDouble(Store::get_absolute_poputs).thenComparing(Store::getName);
//
//
//    public int readStores(InputStream in) {
//        Scanner sc=new Scanner( new InputStreamReader(in));
//        while(sc.hasNextLine()) {
//            String[] lines = sc.nextLine().split("\\s+");
//            List<Product> products = new ArrayList<>();
//            for (int i = 1; i <lines.length ; i++) {
//                String[] line = lines[i].split(":");
//                products.add(new Product(Integer.parseInt(line[0]),Integer.parseInt(line[1])));
//            }
//            stores.add(new Store(lines[0], products));
//        }
//        return stores.size();
//    }
//
//    public List<Store> byAverageDiscount() {
//        return stores.stream().sorted(descendingPopustName).limit(3).collect(Collectors.toList());
//    }
//
//    public List<Store> byTotalDiscount() {
//        return stores.stream().sorted(ascendingAbsolutePopustName).limit(3).collect(Collectors.toList());
//    }
//}
//
//class Store{
//    String name;
//    Set<Product> products;
//
//    public Store(String name, List<Product> products) {
//        this.name = name;
//        this.products = new TreeSet<>(products);
//        products.addAll(products);
//    }
//    public double get_averagePopust(){
//        return products.stream().mapToDouble(Product::get_discount).average().orElse(0.0);
//    }
//    public int get_absolute_poputs(){
//        return products.stream().mapToInt(Product::get_absolute_discount).sum();
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append(String.format("%s\nAverage discount: %.1f%%\nTotal discount: %d\n",name,this.get_averagePopust(),get_absolute_poputs()));
//        String productsStr=products.stream().map(Product::toString).collect(Collectors.joining("\n"));
//        return builder.toString()+productsStr;
//    }
//}
//class Product implements Comparable<Product> {
//    @Override
//    public int compareTo(Product o) {
//        if(this.get_discount() > o.get_discount()) {
//            return -1;
//        } else if (this.get_discount() < o.get_discount()) {
//            return 1;
//        }else{
//            return this.get_absolute_discount()>o.get_absolute_discount()?-1:1;
//        }
//    }
//
//    int price;
//    int discounted_price;
//
//    public Product(int discounted_price,int price) {
//        this.discounted_price = discounted_price;
//        this.price = price;
//    }
//    public int get_discount(){
//        return (price-discounted_price)*100/price;
//    }
//    public int get_absolute_discount(){
//        return price-discounted_price;
//    }
//    @Override
//    public String toString() {
//        return String.format("%2d%% %d/%d", get_discount(), discounted_price, price);
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
//
//public class DiscountsTest {
//    public static void main(String[] args) {
//        Discounts discounts = new Discounts();
//        int stores = discounts.readStores(System.in);
//        System.out.println("Stores read: " + stores);
//        System.out.println("=== By average discount ===");
//        discounts.byAverageDiscount().forEach(System.out::println);
//        System.out.println("=== By total discount ===");
//        discounts.byTotalDiscount().forEach(System.out::println);
//    }
//}
//
//// Vashiot kod ovde