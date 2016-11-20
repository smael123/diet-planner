/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.Food;
import appgui.beans.Person;
import static appgui.popUpWindow.confirmation;
import appgui.tables.ManagerFood;
import appgui.tables.ManagerPerson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;


/**
 *
 * @author Carrie Dumit
 */
public class Profile {
    dietPlan dietPlanObj= new dietPlan();
    //user's profile with personal information, restrictions, and diet plan
    public void displayProfile (Person personBean){
        
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Welcome " + personBean.getUsername());
        window.setMinWidth(500);
        
        Button logout= new Button("Logout");
        GridPane.setConstraints(logout,2,2);
        logout.setOnAction(e->window.close());
        
        //confirm and send button
        Button chooseRestrictions = new Button("Choose Restrictions");
        GridPane.setConstraints(chooseRestrictions,2,2);
        //save al users selection when done
        chooseRestrictions.setOnAction(e->{
            dietPlanObj.restrictions("Restrictions");
            dietPlanObj.handleRestrictions();
            
             //window.close();
        });
        
        //confirm and send button
        Button modifyDiet = new Button("Modify Diet");
        GridPane.setConstraints(modifyDiet,2,2);
        //save al users selection when done
        modifyDiet.setOnAction(e->{
            dietPlanObj.modifyDiet("Change Diet");
            dietPlanObj.handleChangesDiet();
            
             //window.close();
        });
        
        Hyperlink musclePreference;
        Hyperlink loseWeightPreference;
        Hyperlink stayHealthyPreference;
        
        Label choosePreferenceLabel = new Label("Choose your diet preference");
        
        musclePreference = new Hyperlink("Get buff!");
        loseWeightPreference = new Hyperlink("Lose Weight");
        stayHealthyPreference = new Hyperlink("Stay Healthy");
        
        //hyperlink event listeners
        musclePreference.setOnAction(e-> {
            try {
                ApplicableFoodGUI.display(personBean.getId(), personBean.getUsername());
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        
        });
        
        
        VBox layout = new VBox (20);
        layout.setPadding(new Insets (20,20,20,20));
        layout.getChildren().addAll(logout, chooseRestrictions,modifyDiet, choosePreferenceLabel, musclePreference, loseWeightPreference, stayHealthyPreference);
                   
 
        
        Scene scene = new Scene(layout,500,500);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
    public void modifyDiet (String title ){
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        Button logout= new Button("Logout");
        GridPane.setConstraints(logout,2,2);
        logout.setOnAction(e->window.close());
        
        //confirm and send button
        Button chooseRestrictions = new Button("Choose Restrictions");
        GridPane.setConstraints(chooseRestrictions,2,2);
        //save al users selection when done
        chooseRestrictions.setOnAction(e->{
            dietPlanObj.restrictions("Restrictions");
            dietPlanObj.handleRestrictions();
            
             //window.close();
        });
           VBox layout = new VBox (20);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(logout, chooseRestrictions);
                   
 
        
        Scene scene = new Scene(layout,500,500);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
    public void adminWindow(String title){
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        Button logout= new Button("Logout");
        logout.setOnAction(e->window.close());
        
        //confirm and send button
        Button deleteFood = new Button("Delete food item");
        TextField food = new TextField();
        food.setPromptText("Food item to delete");
        
        
        Button addFood = new Button ("Add food item");
        TextField food2 = new TextField();
        food2.setPromptText("Food itemt to add");
        TextField foodType = new TextField();
        foodType.setPromptText("Food Category");
        
        
        addFood.setOnAction(e->{
            System.out.println("addFood called");
            Food bean  = new Food();
            bean.setFoodName(food2.getText());
            bean.setFoodType(foodType.getText());
            
            try {
                boolean result = ManagerFood.addFood(bean);
                System.out.println(result);
                if(result){
                    Util.alertBox("Success","Food added");
                    System.out.println("New food with primary key "+ bean.getId() + " was inserted");   
                }
                else
                {
                    System.out.println("It didn't work...");
                }
            } catch (SQLException ex) {
                System.err.println("exception caught: " + ex.getMessage());
            }
             
            window.close();
        });
        
   
         
        TextField username = new TextField();
        username.setPromptText("Username to Delete");
        Button deleteUser = new Button ("Delete User");
        deleteUser.setOnAction(e->{
            
           
            
            
        });
        
        
        
        //save al users selection when done
        deleteFood.setOnAction(e->{
           // methods from diet table  here 
            
            
             
        });
        
        
        
        
        deleteUser.setOnAction(e->{
            try {
                
                if(ManagerPerson.deletePerson(username.getText()))
                {
                    Util.alertBox("Sucess", "User was deleted");
                    System.out.println("User deleted");
                }
                
            } catch (Exception ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                Util.alertBox("Error", "User was not found");
                System.err.println("No user found");
            }
            
            
            
            
        });
        
        
           VBox layout = new VBox (20);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(food,deleteFood,food2,foodType,addFood,username,deleteUser);
           layout.getChildren().add(logout);
           
                   
 
        
        Scene scene = new Scene(layout,500,500);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
    
    public void preferences()
    {
        Hyperlink musclePreference;
        Hyperlink loseWeightPreference;
        Hyperlink stayHealthyPreference;
        
        Label choosePreferenceLabel = new Label("Choose your diet preference");
        
        musclePreference = new Hyperlink("Get buff!");
        loseWeightPreference = new Hyperlink("Lose Weight");
        stayHealthyPreference = new Hyperlink("Stay Healthy");
        
        
        
    }
            
    }
   
    
       
          
        
        
        
        
    
 
    
    

