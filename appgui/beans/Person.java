/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.beans;

/**
 *
 * @author Carrie Dumit
 */
public class Person {
    private int id; 
    private String username;
    private String pword;
    private String pword2;
    private int age;
    private String gender;
    private int height; 
    private int weight;
    private double BMI;
    private boolean athletic;
    private int admin;
    
    
   public Person(){}
   
   
  /* public Person (String username, String password, String password2){
       this.username  = username;
       if(password2==password)
       this.pword= password;
   }
*/
   public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPword() {
        return pword;
    }
    public String getPword2(){
        return pword2;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }
    public void setPword2(String pword2){
        if (pword2 ==pword)
        this.pword2=pword2;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public void setAge(String age) {
        int age2 = Integer.parseInt(age);
        this.age = age2;
    }

    public String getGender() {
        return gender;
    }
    public int getAdmin(){
        return admin;
    }
    public void setAdmin(int admin){
        this.admin = admin;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setHeight (int height){
        this.height = height;
    }
    public void setHeight(String height) {
        int height2  = Integer.parseInt(height);
        this.height = height2;
    }
    public int getHeight(){
        return height;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public void setWeight(String weight) {
        int weight2= Integer.parseInt(weight);
        this.weight = weight2;
    }

    public double getBMI() {
        return BMI;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public boolean isAthletic() {
        return athletic;
    }

    public void setAthletic(boolean athletic) {
        this.athletic = athletic;
    }
    
    
    
}
