
import java.util.*;

public class StadiumTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] sectorNames = new String[n];
        int[] sectorSizes = new int[n];
        String name = scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            sectorNames[i] = parts[0];
            sectorSizes[i] = Integer.parseInt(parts[1]);
        }
        Stadium stadium = new Stadium(name);
        stadium.createSectors(sectorNames, sectorSizes);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            try {
                stadium.buyTicket(parts[0], Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
            } catch (SeatNotAllowedException e) {
                System.out.println("SeatNotAllowedException");
            } catch (SeatTakenException e) {
                System.out.println("SeatTakenException");
            }
        }
        stadium.showSectors();
    }
}
enum TYPE {
    NEUTRAL,HOME,GUESTS,UNKNOWN
}
class SeatNotAllowedException extends Exception {
    public SeatNotAllowedException() {
    }
}

class Sector{
    String name;
    int size;
    Map<Integer,Boolean> seats;
    int type;
    public Sector(String code, int size) {
        this.name = code;
        this.size = size;
        seats = new HashMap<>();
        type=-1;
    }
    int available() {
        return size-seats.size();
    }
    float availablePercentage(){
        return (float) ((1.0-((float)available()/(float)size))*100.0);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s\t%d/%d\t%.1f%%", name, available(),size,availablePercentage());
    }
}
class Stadium{
    String name;
    Map<String,Sector> sectors;

    public static Comparator<Sector> compare=Comparator.comparing(Sector::available).reversed().thenComparing(Sector::getName);

    public Stadium(String name) {
        this.name = name;
        sectors=new HashMap<>();
    }
    void createSectors(String[] sectorNames, int[] sizes){
        for (int i = 0; i < sectorNames.length; ++i) {
            sectors.put(sectorNames[i],new Sector(sectorNames[i],sizes[i]));
        }
    }

    void buyTicket(String sectorName, int seat, int type) throws SeatTakenException, SeatNotAllowedException {
        Sector sector = sectors.get(sectorName);
        if(sector.seats.containsKey(seat)){
            throw new SeatTakenException();
        }
        if(type==0){
            //do nothing
        } else if(sector.type==-1) {
            sector.type=type;
        }else{
            if(sector.type+type==3){
                throw new SeatNotAllowedException();
            }
        }
        sector.seats.put(seat,true);


    }
    void showSectors(){
        List<Sector> sectors=new ArrayList<>();
        sectors.addAll(this.sectors.values());
        sectors.stream().sorted(compare).forEach(System.out::println);
    }


}
class SeatTakenException extends Exception {
    public SeatTakenException() {
    }
}

