//import java.util.*;
//
///*
//YOUR CODE HERE
//DO NOT MODIFY THE interfaces and classes below!!!
//*/
//
//interface Location {
//    int getX();
//
//    int getY();
//
//    default int distance(Location other) {
//        int xDiff = Math.abs(getX() - other.getX());
//        int yDiff = Math.abs(getY() - other.getY());
//        return xDiff + yDiff;
//    }
//}
//
//class LocationCreator {
//    public static Location create(int x, int y) {
//
//        return new Location() {
//            @Override
//            public int getX() {
//                return x;
//            }
//
//            @Override
//            public int getY() {
//                return y;
//            }
//        };
//    }
//}
//
//public class DeliveryAppTester {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String appName = sc.nextLine();
//        DeliveryApp app = new DeliveryApp(appName);
//        while (sc.hasNextLine()) {
//            String line = sc.nextLine();
//            String[] parts = line.split(" ");
//
//            if (parts[0].equals("addUser")) {
//                String id = parts[1];
//                String name = parts[2];
//                app.addUser(id, name);
//            } else if (parts[0].equals("registerDeliveryPerson")) {
//                String id = parts[1];
//                String name = parts[2];
//                int x = Integer.parseInt(parts[3]);
//                int y = Integer.parseInt(parts[4]);
//                app.registerDeliveryPerson(id, name, LocationCreator.create(x, y));
//            } else if (parts[0].equals("addRestaurant")) {
//                String id = parts[1];
//                String name = parts[2];
//                int x = Integer.parseInt(parts[3]);
//                int y = Integer.parseInt(parts[4]);
//                app.addRestaurant(id, name, LocationCreator.create(x, y));
//            } else if (parts[0].equals("addAddress")) {
//                String id = parts[1];
//                String name = parts[2];
//                int x = Integer.parseInt(parts[3]);
//                int y = Integer.parseInt(parts[4]);
//                app.addAddress(id, name, LocationCreator.create(x, y));
//            } else if (parts[0].equals("orderFood")) {
//                String userId = parts[1];
//                String userAddressName = parts[2];
//                String restaurantId = parts[3];
//                float cost = Float.parseFloat(parts[4]);
//                app.orderFood(userId, userAddressName, restaurantId, cost);
//            } else if (parts[0].equals("printUsers")) {
//                app.printUsers();
//            } else if (parts[0].equals("printRestaurants")) {
//                app.printRestaurants();
//            } else {
//                app.printDeliveryPeople();
//            }
//
//        }
//    }
//}
