import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum COMPARATOR_TYPE {
    NEWEST_FIRST,
    OLDEST_FIRST,
    LOWEST_PRICE_FIRST,
    HIGHEST_PRICE_FIRST,
    MOST_SOLD_FIRST,
    LEAST_SOLD_FIRST
}

class ProductNotFoundException extends Exception {
    ProductNotFoundException(String message) {
        super(message);
    }
}


class Product {
    String category;
    String id;
    String name;
    LocalDateTime createdAt;
    double price;
    int quantity;

    public Product(String category, String id, String name, LocalDateTime createdAt, double price) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        quantity=0;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }
    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + quantity +
                '}';
    }
}


class OnlineShop {
    Map<String,Product> products;

    OnlineShop() {
        products = new HashMap<>();
    }

    void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        if(!products.containsKey(id)) {
            products.put(id, new Product(category, id, name, createdAt, price));
        }else{
//            System.out.println("Product already exists");
        }
    }

    double buyProduct(String id, int quantity) throws ProductNotFoundException {
        if(!products.containsKey(id)) {
            throw new ProductNotFoundException(String.format("Product with id %s does not exist in the online shop!",id));
        }
        Product product = products.get(id);
        product.updateQuantity(quantity);
        return product.price*quantity;

    }

    List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<Product> filteredProducts=null;
        if(category==null){
            filteredProducts= new ArrayList<>(products.values());
            filteredProducts.sort(GetComparator.getComparator(comparatorType));
        }else{
            filteredProducts = products.values().stream().filter(product -> product.getCategory().equals(category)).sorted(GetComparator.getComparator(comparatorType)).collect(Collectors.toList());
        }
        int c=0;
        List<List<Product>> result = new ArrayList<>();
        for (int i = 0; i < filteredProducts.size(); i+=pageSize) {
            result.add(new ArrayList<>());
            result.get(c).addAll(filteredProducts.subList(i, Math.min(i+pageSize, filteredProducts.size())));
            c++;
        }
        return result;
    }

}
class GetComparator{
    static Comparator<Product> NEWEST_FIRST=Comparator.comparing(Product::getCreatedAt);
    static Comparator<Product> MOST_SOLD_FIRST=Comparator.comparing(Product::getQuantity);
    static Comparator<Product> LOWEST_PRICE_FIRST=Comparator.comparing(Product::getPrice);


    static Comparator<Product> getComparator(COMPARATOR_TYPE comparatorType) {
        switch (comparatorType) {
            case NEWEST_FIRST: return NEWEST_FIRST.reversed();
            case OLDEST_FIRST: return NEWEST_FIRST;
            case MOST_SOLD_FIRST: return MOST_SOLD_FIRST.reversed();
            case LEAST_SOLD_FIRST: return MOST_SOLD_FIRST;
            case LOWEST_PRICE_FIRST: return LOWEST_PRICE_FIRST;
            case HIGHEST_PRICE_FIRST: return LOWEST_PRICE_FIRST.reversed();
        }
        return null;
    }
}

public class OnlineShopTest {

    public static void main(String[] args) {
        OnlineShop onlineShop = new OnlineShop();
        double totalAmount = 0.0;
        Scanner sc = new Scanner(System.in);
        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equalsIgnoreCase("addproduct")) {
                String category = parts[1];
                String id = parts[2];
                String name = parts[3];
                LocalDateTime createdAt = LocalDateTime.parse(parts[4]);
                double price = Double.parseDouble(parts[5]);
                onlineShop.addProduct(category, id, name, createdAt, price);
            } else if (parts[0].equalsIgnoreCase("buyproduct")) {
                String id = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                try {
                    totalAmount += onlineShop.buyProduct(id, quantity);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                String category = parts[1];
                if (category.equalsIgnoreCase("null"))
                    category = null;
                String comparatorString = parts[2];
                int pageSize = Integer.parseInt(parts[3]);
                COMPARATOR_TYPE comparatorType = COMPARATOR_TYPE.valueOf(comparatorString);
                printPages(onlineShop.listProducts(category, comparatorType, pageSize));
            }
        }
        System.out.println("Total revenue of the online shop is: " + totalAmount);

    }

    private static void printPages(List<List<Product>> listProducts) {
        for (int i = 0; i < listProducts.size(); i++) {
            System.out.println("PAGE " + (i + 1));
            listProducts.get(i).forEach(System.out::println);
        }
    }
}
