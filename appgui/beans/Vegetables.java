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
public class Vegetables {
    private int id;
    private String foodType;
    private String typeOfVegetable;
    private int legume;
    private int complexCarb;

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
     * @return the typeOfVegetable
     */
    public String getTypeOfVegetable() {
        return typeOfVegetable;
    }

    /**
     * @param typeOfVegetable the typeOfVegetable to set
     */
    public void setTypeOfVegetable(String typeOfVegetable) {
        this.typeOfVegetable = typeOfVegetable;
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

    /**
     * @return the complexCarb
     */
    public int getComplexCarb() {
        return complexCarb;
    }

    /**
     * @param complexCarb the complexCarb to set
     */
    public void setComplexCarb(int complexCarb) {
        this.complexCarb = complexCarb;
    }
}
