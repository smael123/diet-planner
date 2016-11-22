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
    private String dayTime;
    private int calorie;
    private int totalCalorie; //aggregate
    private int totalFat; //row
    private int carb;
    private int protein;
    private double vitaminC;
    private double calcium;

    /**
     * @return the foodName
     */
    public String getFoodName() {
        return foodName;
    }

    /**
     * @param foodName the foodName to set
     */
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    /**
     * @return the foodType
     */
    public String getFoodType() {
        return foodType;
    }

    /**
     * @param foodType the foodType to set
     */
    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    /**
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the calorie
     */
    public int getCalorie() {
        return calorie;
    }

    /**
     * @param calorie the calorie to set
     */
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    /**
     * @return the totalCalorie
     */
    public int getTotalCalorie() {
        return totalCalorie;
    }

    /**
     * @param totalCalorie the totalCalorie to set
     */
    public void setTotalCalorie(int totalCalorie) {
        this.totalCalorie = totalCalorie;
    }

    /**
     * @return the totalFat
     */
    public int getTotalFat() {
        return totalFat;
    }

    /**
     * @param totalFat the totalFat to set
     */
    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    /**
     * @return the carb
     */
    public int getCarb() {
        return carb;
    }

    /**
     * @param carb the carb to set
     */
    public void setCarb(int carb) {
        this.carb = carb;
    }

    /**
     * @return the protien
     */
    public int getProtein() {
        return protein;
    }

    /**
     * @param protein the protien to set
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     * @return the vitaminC
     */
    public double getVitaminC() {
        return vitaminC;
    }

    /**
     * @param vitaminC the vitaminC to set
     */
    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    /**
     * @return the calcium
     */
    public double getCalcium() {
        return calcium;
    }

    /**
     * @param calcium the calcium to set
     */
    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    /**
     * @return the dayTime
     */
    public String getDayTime() {
        return dayTime;
    }

    /**
     * @param dayTime the dayTime to set
     */
    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }
    
}
