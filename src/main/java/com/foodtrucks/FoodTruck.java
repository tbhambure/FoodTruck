package main.java.com.foodtrucks;

import lombok.Data;

import java.io.Serializable;

@Data
public class FoodTruck implements Serializable {

    private String applicant;

    private String dayofweekstr;

    private String dayorder;

    private String location;

    private String start24;

    private String end24;

}
