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
public class Grains {
    private int id;
    private String foodType;
    private int gluten;
    private String typeOfGrain;
    private int whiteBread;
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
     * @return the gluten
     */
    public int getGluten() {
        return gluten;
    }

    /**
     * @param gluten the gluten to set
     */
    public void setGluten(int gluten) {
        this.gluten = gluten;
    }

    /**
     * @return the typeOfGrain
     */
    public String getTypeOfGrain() {
        return typeOfGrain;
    }

    /**
     * @param typeOfGrain the typeOfGrain to set
     */
    public void setTypeOfGrain(String typeOfGrain) {
        this.typeOfGrain = typeOfGrain;
    }

    /**
     * @return the whiteBread
     */
    public int getWhiteBread() {
        return whiteBread;
    }

    /**
     * @param whiteBread the whiteBread to set
     */
    public void setWhiteBread(int whiteBread) {
        this.whiteBread = whiteBread;
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
