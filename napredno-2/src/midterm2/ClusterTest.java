package midterm2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * January 2016 Exam problem 2
 */
public class ClusterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cluster<Point2D> cluster = new Cluster<>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            long id = Long.parseLong(parts[0]);
            float x = Float.parseFloat(parts[1]);
            float y = Float.parseFloat(parts[2]);
            cluster.addItem(new Point2D(id, x, y));
        }
        int id = scanner.nextInt();
        int top = scanner.nextInt();
        cluster.near(id, top);
        scanner.close();
    }
}
interface Clusterable<T>{
    long id();
    double distance(T item);
}

class Cluster<T extends Clusterable<T>>{
    Map<Long, T> items;

    public Cluster() {
        items = new HashMap<>();
    }

    void addItem(T item){
    items.put(item.id(), item);
    }
    public void near(long id, int top){
        top++;
        T element = items.get(id);
        List<T> elements=items.values().stream().sorted((b,a)->Double.compare(b.distance(element),a.distance(element)))
                .limit(top).collect(Collectors.toList());

        for (int i = 1; i<elements.size() ; i++) {
            System.out.printf("%d. %d -> %.3f\n", i , elements.get(i).id(),elements.get(i).distance(element));

        }

    }
}
class Point2D implements Clusterable<Point2D> {
    long id;
    float x;
    float y;

    public Point2D(long id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }


    @Override
    public long id() {
        return id;
    }

    @Override
    public double distance(Point2D item) {
        return Math.sqrt(Math.pow(this.x - item.x,2) + Math.pow(this.y - item.y,2));
    }
}

// your code here