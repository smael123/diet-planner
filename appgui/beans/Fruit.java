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
public class Fruit {
    private int id;
    private String foodType;
    private int nuts;
    private int citrus;
    private int legume;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the nuts
     */
    public int getNuts() {
        return nuts;
    }

    /**
     * @param nuts the nuts to set
     */
    public void setNuts(int nuts) {
        this.nuts = nuts;
    }

    /**
     * @return the citrus
     */
    public int getCitrus() {
        return citrus;
    }

    /**
     * @param citrus the citrus to set
     */
    public void setCitrus(int citrus) {
        this.citrus = citrus;
    }

    /**
     * @return the legume
     */
    public int getLegume() {
        return legume;
    }

    /**
     * @param legume the legume to set
     */
    public void setLegume(int legume) {
        this.legume = legume;
    }
           
}
