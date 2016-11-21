/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.Person;
import appgui.tables.ManagerPerson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Carrie Dumit
 */
public class Util {
    
     private static boolean isInt(TextField input, String message){
        try{
            int age= Integer.parseInt(input.getText());
            System.out.println("User is "+ age);
            return true;
        
    }catch (NumberFormatException e){
    System.out.println("Error" + message + "is not a number");
    return false;
        }
    }
    public static void alertBox(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button ("Close");
        closeButton.setOnAction(e->window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
    }
    public static boolean updatePword(String title,String enterUsername){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);
        
        Label username = new Label();
        username.setText(enterUsername);
        TextField password = new TextField();
        password.setPromptText("New password");
        TextField comfirmPassword = new TextField();
        comfirmPassword.setPromptText("Comfim password");
        
        Button done = new Button ("Done");
        
        done.setOnAction(e->{
            
            try {
            Person bean = ManagerPerson.getPerson(username.getText());
            System.out.println("User not found");
           
            // bean.setUsername(username.getText());
             bean.setPword(password.getText());
             bean.setPword2(comfirmPassword.getText());
             
                if(ManagerPerson.updatePword(bean)){
                    Util.alertBox("Password Update", "Sucess");
                }else {
                    Util.alertBox("Password Update", "Whoops not matching");
                }
            } catch (Exception ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
             
            
            window.close();
            
                    
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(username,password,comfirmPassword,done);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        
        return true;
    }
    
}
    
