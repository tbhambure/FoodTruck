package main.java.com.foodtrucks;

import java.util.List;
import java.util.Scanner;

public class FoodTruckReader {

    public static void main(String[] args) {

        FoodTruckFinder finder = new FoodTruckFinder();
        List<FoodTruck> foodTrucks = finder.getAllOpenFoodTrucks();

        int count = foodTrucks.size();
        int limit = 10;
        int offset = 0;

        System.out.println("\n\n\n\n\nList of all currently open foodtrucks:\n");
        System.out.printf("%-32s %s\n", "NAME", "ADDRESS");
        System.out.println("----------------------------------------\n");

        Scanner scanner = new Scanner(System.in);
        while (offset < count) {

            for (int i = offset; i < offset + limit; i++) {
                if (i >= count)
                    break;
                FoodTruck foodTruck = foodTrucks.get(i);
                System.out.printf("%-32s %s\n", foodTruck.getApplicant(), foodTruck.getLocation());
            }
            offset = offset + limit;

            System.out.println("\n\nPress `Enter` to read the next " + limit + " foodtrucks: ");
            String input = scanner.nextLine();

            if ("q".equals(input)) {
                System.out.println("Exit!");
                break;
            }
        }

        System.out.println("Completed showing all open foodtrucks!\n");

        scanner.close();
    }
}

// to run:
// 1. mvn package
// 2. java -jar target/foodtruck-finder-1.0.jar
