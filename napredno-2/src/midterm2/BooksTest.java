//package midterm2;
import java.util.*;
import java.util.stream.Collectors;

class Book {
    String title;
    String categorija;
    float price;

    public Book(String title, String categorija, float price) {
        this.title = title;
        this.categorija = categorija;
        this.price = price;
    }

    public String getCategorija() {
        return categorija;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Book %s (%s) %.2f",title,categorija,price);
    }
}
class BookCollection{
    List<Book> books;


    private final Comparator<Book> titlAandPrice = Comparator.comparing(Book::getTitle)
            .thenComparing(Book::getPrice);
    private final Comparator<Book> priceAandTitle = Comparator.comparing(Book::getPrice)
            .thenComparing(Book::getTitle);

    public BookCollection() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }
    public void printByCategory(String category){
        books.stream().filter(book->book.getCategorija().equalsIgnoreCase(category)).sorted(titlAandPrice).forEach(System.out::println);
    }


    public List<Book> getCheapestN(int n){
        return books.stream().sorted(priceAandTitle).limit(n).collect(Collectors.toList());
    }
}


public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

// Вашиот код овде