/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import static appgui.popUpWindow.confirmation;
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
    public void restrictions (String title ){
        
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
        
        //confirm and send button
        Button modifyDiet = new Button("Modify Diet");
        GridPane.setConstraints(modifyDiet,2,2);
        //save al users selection when done
        modifyDiet.setOnAction(e->{
            dietPlanObj.modifyDiet("Change Diet");
            dietPlanObj.handleChangesDiet();
            
             //window.close();
        });
        
        
        
        
        
           VBox layout = new VBox (20);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(logout, chooseRestrictions,modifyDiet);
                   
 
        
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
    
    
            
    }
   
    
       
          
        
        
        
        
    
 
    
    

