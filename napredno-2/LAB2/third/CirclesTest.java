package LAB2.third;

import java.util.ArrayList;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}
 class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(String message) {
        super(message);
    }
}
 class MovableObjectNotFittableException extends Exception {
    public MovableObjectNotFittableException(String message) {
        super(message);
    }
}

interface Movable{



    public int getCurrentXPosition();
    public int getCurrentYPosition();

    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    TYPE getType();
}
class MovableCircle implements Movable {
    private int radius;
    MovablePoint point;

    public MovableCircle(int radius, MovablePoint point) {
        this.radius = radius;
        this.point = point;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates ("+point.getCurrentXPosition()+","+point.getCurrentYPosition()+") and radius "+radius;
    }

    @Override
    public int getCurrentXPosition() {
        return point.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return point.getCurrentYPosition();
    }

    public void moveUp() throws ObjectCanNotBeMovedException {
        if(point.getCurrentYPosition()+point.getySpeed()<=MovablesCollection.maxX){
            point.moveUp();
        }else{
            throw new ObjectCanNotBeMovedException(this+"  can not be fitted into the collection");
        }
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if(point.getCurrentYPosition()-point.getySpeed()>=0){
            point.moveDown();
        }else{
            throw new ObjectCanNotBeMovedException(this+"  can not be fitted into the collection");
        }
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if(point.getCurrentXPosition()+point.getxSpeed()<MovablesCollection.maxX){
            point.moveRight();
        }else{
            throw new ObjectCanNotBeMovedException(this+"  can not be fitted into the collection");
        }
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if(point.getCurrentXPosition()-point.getxSpeed()>=0){
            point.moveLeft();
        }else{
            throw new ObjectCanNotBeMovedException(this+"  can not be fitted into the collection");
        }
    }
    @Override
    public TYPE getType(){
        return TYPE.CIRCLE;
    }

    public MovablePoint getPoint() {
        return point;
    }
}
class MovablePoint implements Movable{
    private int x,y;
    private
    int xSpeed,ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        if(y+ySpeed<=MovablesCollection.maxY){
            y+=ySpeed;
        }else{
            throw new ObjectCanNotBeMovedException("Point ("+x+","+(y+ySpeed)+") is out of bounds");
        }
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        if(y-ySpeed>=0){
            y-=ySpeed;
        }else{
            throw new ObjectCanNotBeMovedException("Point ("+x+","+(y-ySpeed)+") is out of bounds");
        }
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        if(x+xSpeed<MovablesCollection.maxX){
            x+=xSpeed;
        }else{
            throw new ObjectCanNotBeMovedException("Point ("+(x+xSpeed)+","+y+") is out of bounds");
        }
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        if(x-xSpeed>=0){
            x-=xSpeed;
        }else{
            throw new ObjectCanNotBeMovedException("Point ("+(x-xSpeed)+","+y+") is out of bounds");
        }
    }
    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates (" + x +
                "," + y +
                ')';
    }
    public TYPE getType(){
        return TYPE.POINT;
    }

}
class MovablesCollection{
    ArrayList<Movable> movables;
    static int maxX;
    static int maxY;


    public MovablesCollection(int x, int y) {
       maxX=x;
       maxY=y;
       movables=new ArrayList<>();
    }

    public static void setxMax(int i) {
        maxX=i;
    }

    public static void setyMax(int i) {
        maxY=i;
    }

    void addMovableObject(Movable m) throws MovableObjectNotFittableException{

        if(m.getType()==TYPE.POINT){

            if(m.getCurrentXPosition()>=maxX || m.getCurrentYPosition()>=maxY || m.getCurrentXPosition()<=0 || m.getCurrentYPosition()<=0){
                throw new MovableObjectNotFittableException(m+" can not be fitted into the collection");
            }else{
                movables.add(m);
            }
        }else{

            MovableCircle mc=(MovableCircle)m;
            int radius=mc.getRadius();
            if(m.getCurrentXPosition()+radius>maxX || m.getCurrentYPosition()+radius>maxY || m.getCurrentXPosition()-radius<0 || m.getCurrentYPosition()-radius<0){
                throw new MovableObjectNotFittableException("Movable circle with center ("+mc.point.getCurrentXPosition()+","+mc.point.getCurrentYPosition()+") and radius "+mc.getRadius()+" can not be fitted into the collection");
            }else{
                movables.add(m);
            }
        }

    }
    void move(DIRECTION direction,Movable mov,TYPE type){
        switch (direction){
            case UP :
                try {
                    if(type==TYPE.CIRCLE){
                        ((MovableCircle)mov).getPoint().moveUp();
                    }else{
                        mov.moveUp();
                    }
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DOWN :
                try {
                    if(type==TYPE.CIRCLE){
                    ((MovableCircle)mov).getPoint().moveDown();
                }else{
                    mov.moveDown();
                }

                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case LEFT:
                try {
                    if(type==TYPE.CIRCLE){
                        ((MovableCircle)mov).getPoint().moveLeft();
                    }else{
                        mov.moveLeft();
                    }
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case RIGHT:
                try {
                    if(type==TYPE.CIRCLE){
                        ((MovableCircle)mov).getPoint().moveRight();
                    }else{
                        mov.moveRight();
                    }
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }
    }
    void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction){
//        for (Movable mov: movables){
//            if(mov.getType()==type){
//              move(direction,mov,type);
//            }
//        }
        movables.stream().filter(movable -> movable.getType()==type).forEach(movie->move(direction,movie,type));
    }

    @Override
    public String toString() {
        StringBuilder br=new StringBuilder();
        br.append("Collection of movable objects with size "+movables.size()+":\n");
        movables.stream().forEach(movable -> br.append(movable).append("\n"));


        return br.toString();
    }
}
public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }


}