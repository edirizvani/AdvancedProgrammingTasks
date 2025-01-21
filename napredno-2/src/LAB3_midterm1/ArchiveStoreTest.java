//package LAB3;
//
//import java.util.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class ArchiveStoreTest {
//    public static void main(String[] args) {
//        ArchiveStore store = new ArchiveStore();
//        LocalDate date = LocalDate.of(2013, 10, 7);
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
//        int n = scanner.nextInt();
//        scanner.nextLine();
//        scanner.nextLine();
//        int i;
//        for (i = 0; i < n; ++i) {
//            int id = scanner.nextInt();
//            long days = scanner.nextLong();
//
//            LocalDate dateToOpen = date.atStartOfDay().plusSeconds(days * 24 * 60 * 60).toLocalDate();
//            LockedArchive lockedArchive = new LockedArchive(id, dateToOpen);
//            store.archiveItem(lockedArchive, date);
//        }
//        scanner.nextLine();
//        scanner.nextLine();
//        n = scanner.nextInt();
//        scanner.nextLine();
//        scanner.nextLine();
//        for (i = 0; i < n; ++i) {
//            int id = scanner.nextInt();
//            int maxOpen = scanner.nextInt();
//            SpecialArchive specialArchive = new SpecialArchive(id, maxOpen);
//            store.archiveItem(specialArchive, date);
//        }
//        scanner.nextLine();
//        scanner.nextLine();
//        while(scanner.hasNext()) {
//            int open = scanner.nextInt();
//            try {
//                store.openItem(open, date);
//            } catch(NonExistingItemException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//        System.out.println(store.getLog());
//    }
//}
//abstract class Archive{
//    protected int id;
//    protected LocalDate dateArchived;
//
//    public void archive(LocalDate date){
//        dateArchived=date;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public LocalDate getDateArchived() {
//        return dateArchived;
//    }
//
//}
//class LockedArchive extends Archive{
//    private LocalDate dateToOpen;
//
//    LockedArchive(int id, LocalDate dateToOpen) {
//        this.id=id;
//        this.dateToOpen = dateToOpen;
//    }
//    public boolean open(LocalDate date){
//        if(date.isAfter(dateToOpen)){
//            return true;
//        }
//        return false;
//    }
//
//    public LocalDate getDateToOpen() {
//        return dateToOpen;
//    }
//}
//class SpecialArchive extends Archive{
//    private int maxOpen;
//    private int numOpen;
//
//
//
//    SpecialArchive(int id, int maxOpen){
//        this.id=id;
//        this.maxOpen=maxOpen;
//        this.numOpen=0;
//    }
//
//    public int getMaxOpen() {
//        return maxOpen;
//    }
//
//    public int getNumOpen() {
//        return numOpen;
//    }
//    public boolean open(){
//        if(numOpen<maxOpen){
//            numOpen++;
//            return true;
//        }
//        return false;
//    }
//}
//
//class ArchiveStore{
//    ArrayList<Archive> list=new ArrayList<>();
//    private StringBuilder br;
//    public ArchiveStore() {
//        list=new ArrayList<>();
//        br=new StringBuilder();
//    }
//    void archiveItem(Archive item, LocalDate date){
//        item.archive(date);
//        list.add(item);
//        br.append(String.format("Item %d archived at %s\n",item.id,date));
//    }
//    void openItem(int id, LocalDate date) throws NonExistingItemException{
//        for (Archive item:list){
//            if(item instanceof SpecialArchive){
//                if(item.getId()==id){
//                    if(((SpecialArchive) item).open()){
//                        br.append(String.format("Item %s opened at %s\n",id,date.toString()));
//                    }else{
//                        br.append(String.format("Item %s cannot be opened more than %d times\n",id,((SpecialArchive) item).getMaxOpen()));
//                    }
//                    return;
//                }
//            }else{
//                if(item.getId()==id){
//                    if(((LockedArchive) item).open(date)){
//                        br.append(String.format("Item %s opened at %s\n",id,date.toString()));
//                    }else{
//                        br.append(String.format("Item %s cannot be opened before %s\n",id,((LockedArchive) item).getDateToOpen()));
//                    }
//                    return;
//                }
//            }
//        }
//         throw new NonExistingItemException(String.format("Item with id %d doesn't exist",id));
//    }
//    String getLog(){
//        return br.toString();
//    }
//
//
//}
// class NonExistingItemException extends Exception{
//    public NonExistingItemException(String format) {
//        super(format);
//    }
//}