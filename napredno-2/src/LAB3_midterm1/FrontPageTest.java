//package LAB3;
//import java.util.*;
//
//public class FrontPageTest {
//    public static void main(String[] args) {
//        // Reading
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        String[] parts = line.split(" ");
//        Category[] categories = new Category[parts.length];
//        for (int i = 0; i < categories.length; ++i) {
//            categories[i] = new Category(parts[i]);
//        }
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        FrontPage frontPage = new FrontPage(categories);
//        Calendar cal = Calendar.getInstance();
//        for (int i = 0; i < n; ++i) {
//            String title = scanner.nextLine();
//            cal = Calendar.getInstance();
//            int min = scanner.nextInt();
//            cal.add(Calendar.MINUTE, -min);
//            Date date = cal.getTime();
//            scanner.nextLine();
//            String text = scanner.nextLine();
//            int categoryIndex = scanner.nextInt();
//            scanner.nextLine();
//            TextNewsItem tni = new TextNewsItem(title, date, categories[categoryIndex], text);
//            frontPage.addNewsItem(tni);
//        }
//
//        n = scanner.nextInt();
//        scanner.nextLine();
//        for (int i = 0; i < n; ++i) {
//            String title = scanner.nextLine();
//            int min = scanner.nextInt();
//            cal = Calendar.getInstance();
//            cal.add(Calendar.MINUTE, -min);
//            scanner.nextLine();
//            Date date = cal.getTime();
//            String url = scanner.nextLine();
//            int views = scanner.nextInt();
//            scanner.nextLine();
//            int categoryIndex = scanner.nextInt();
//            scanner.nextLine();
//            MediaNewsItem mni = new MediaNewsItem(title, date, categories[categoryIndex], url, views);
//            frontPage.addNewsItem(mni);
//        }
//        // Execution
//        String category = scanner.nextLine();
//        System.out.println(frontPage);
//        for(Category c : categories) {
//            System.out.println(frontPage.listByCategory(c).size());
//        }
//        try {
//            System.out.println(frontPage.listByCategoryName(category).size());
//        } catch(CategoryNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
//class CategoryNotFoundException extends Exception{
//    public CategoryNotFoundException(String message) {
//        super(String.format("Category %s was not found",message));
//    }
//}
//class FrontPage{
//    ArrayList<NewsItem> newsItems;
//    ArrayList<Category> categories;
//
//    public FrontPage(Category[] cate) {
//        this.categories=new ArrayList<>();
//        newsItems=new ArrayList<>();
//        for (int i = 0; i < cate.length; i++) {
//            categories.add(cate[i]);
//        }
//    }
//    void addNewsItem(NewsItem newsItem){
//        newsItems.add(newsItem);
//    }
//    List<NewsItem> listByCategory(Category category){
//        List<NewsItem> newList=new ArrayList<>();
//        for (NewsItem item:newsItems){
//            if(item.category.name.equals(category.name)){
//                newList.add(item);
//            }
//        }
//        return newList;
//    }
//    List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException{
//        Category needed=null;
//        for (Category cat:categories){
//            if(cat.name.equals(category)){
//                needed=cat;
//                break;
//            }
//        }
//        if(needed==null){
//            throw new CategoryNotFoundException(category);
//        }
//        return listByCategory(needed);
//    }
//
//    @Override
//    public String toString() {
//      StringBuilder br=new StringBuilder();
//      for(NewsItem item:newsItems){
//          br.append(item.getTeaser());
//      }
//      return br.toString();
//    }
//}
//class Category{
//    String name;
//
//    public Category(String name) {
//        this.name = name;
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//}
//abstract class NewsItem implements Comparable<NewsItem> {
//    String naslov;
//    Date date;
//    Category category;
//
//    public NewsItem(String naslov, Date date, Category category) {
//        this.naslov = naslov;
//        this.date = date;
//        this.category = category;
//    }
//    public int when() {
//        Date now = new Date();
//        long ms = now.getTime() - date.getTime();
//        return (int) (ms / 1000) / 60;
//    }
//
//    abstract String getTeaser();
//
//}
//
//
//class TextNewsItem extends NewsItem{
//    String text;
//
//    public TextNewsItem(String naslov, Date date, Category category, String text) {
//        super(naslov, date, category);
//        this.text = text;
//    }
//
//    @Override
//    public int compareTo(NewsItem o) {
//        return 0;
//    }
//
//    String getTeaser(){
//        String hello=text;
//        if(text.length()>80){
//            hello=text.substring(0,80);
//        }
//
//        return String.format("%s\n%d\n%s\n",naslov,this.when(),hello);
//    }
//}
//
//class MediaNewsItem extends NewsItem{
//    String url;
//    int pogledi;
//    public MediaNewsItem(String title,Date date,Category category,String url,int views){
//        super(title,date,category);
//        this.url=url;
//        this.pogledi=views;
//
//    }
//
//    @Override
//    public int compareTo(NewsItem o) {
//        return 0;
//    }
//
//    String getTeaser(){
//
//        return String.format("%s\n%d\n%s\n%d\n",naslov,this.when(),url,pogledi);
//    }
//}
//// Vasiot kod ovde