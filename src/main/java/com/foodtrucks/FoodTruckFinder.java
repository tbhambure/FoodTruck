package main.java.com.foodtrucks;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FoodTruckFinder {

    private List<FoodTruck> foodTrucks = new ArrayList<>();

    public FoodTruckFinder() {
        populateAllOpenFoodTrucks();
    }

    public List<FoodTruck> getAllOpenFoodTrucks() {
        return foodTrucks;
    }

    public void populateAllOpenFoodTrucks() {
        int page = 0;
        while (populateOpenFoodTrucksForPage(page)) {
            page++;
        }
    }

    private boolean populateOpenFoodTrucksForPage(int page) {

        int limit = 1000;
        int offset = page * limit;

        FoodTruckClient foodTruckClient = new FoodTruckClient();
        FoodTruck[] result = foodTruckClient.getFoodTrucks(offset,limit);

        Stream.of(result)
                .filter(x -> isOpenCurrently(x))
                .forEach(foodTrucks::add);

//        System.out.println("==========================Populated page: " + page + " Count: " + result.length);
        return result.length > 0;
    }

    private boolean isOpenCurrently(FoodTruck foodTruck) {
        LocalDateTime dateTime = LocalDateTime.now();
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();

        LocalTime startTime = LocalTime.parse(foodTruck.getStart24());
        LocalTime endTime = foodTruck.getEnd24().equals("24:00") ? LocalTime.MAX : LocalTime.parse(foodTruck.getEnd24());

        return foodTruck.getDayofweekstr().toUpperCase().equals(dayOfWeek.toString()) &&
                isInBetween(startTime, endTime, dateTime.toLocalTime());
    }

    private boolean isInBetween(LocalTime start, LocalTime end, LocalTime current) {
        return !current.isBefore(start) && current.isBefore(end);
    }
}
