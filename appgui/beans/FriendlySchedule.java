/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.beans;

/**
 *
 * @author lucy
 */
public class FriendlySchedule {
    
    private String foodName;
    private String foodType;
    private String day;
    private String timeOfDay;
    private int calorie;
    private int totalFat;
    private int carb;
    private int protien;
    private double vitaminC;
    private double calcium;
    
    public FriendlySchedule(MealSchedule mealBean, Food foodBean, NutritionalValue nutritionBean)
    {
        //who needs joins?
        foodName = foodBean.getFoodName();
        foodType = foodBean.getFoodType();
        
        calorie = nutritionBean.getCalorieCount();
        switch (mealBean.getDayTime())
        {
            case 0:
                timeOfDay = "Breakfast";
            case 1:
                timeOfDay = "Lunch";
            default:
                timeOfDay = "Dinner";
        }
        switch (mealBean.getDay())
        {
            case 0:
                day = "Sunday";
            case 1:
                day = "Monday";
            case 2:
                day = "Tuesday";
            case 3:
                day = "Wednesday";
            case 4:
                day = "Thursday";
            case 5:
                day = "Friday";
            case 6:
                day = "Saturday";
        }
            
        
    }
    
}
