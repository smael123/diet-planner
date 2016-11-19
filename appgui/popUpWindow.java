package appgui;

import appgui.beans.Person;
import appgui.tables.ManagerPerson;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.*;
//import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Carrie Dumit
 */
public class popUpWindow {
    static boolean confirmation= false;
    Profile profileObj = new Profile();
    
    public static boolean userReg( String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
       
        
        //user enter info here 
        GridPane grid  = new GridPane();
        grid.setPadding(new Insets(15,15,15,15));
        grid.setVgap(10);
        grid.setHgap(15);
       
        //enter username
        TextField username = new TextField();
        username.setPromptText("username");
        GridPane.setConstraints(username, 2,0);
        
        //confirm username
        //TextField username2 = new TextField();
        //username2.setPromptText("confirm username");
        //GridPane.setConstraints(username2, 2, 1);
        
        //password input
        TextField password = new TextField();
        password.setPromptText("password");
        GridPane.setConstraints(password, 2, 2);
        
        //confrim password
        TextField password2 = new TextField();
        password2.setPromptText(" confirm password");
        GridPane.setConstraints(password,2,3 );
        
        //age
        TextField age = new TextField();
        age.setPromptText("age");
        GridPane.setConstraints(age, 3, 3);
        
        TextField gender = new TextField();
        gender.setPromptText("gender");
        GridPane.setConstraints(gender, 3, 2);
        
        TextField height = new TextField();
        height.setPromptText("height");
        GridPane.setConstraints(height, 3, 4);
        
        TextField weight = new TextField();
        weight.setPromptText("weight");
        GridPane.setConstraints(weight,3,6);
        
        //restrictions menu here 
        //athletic yes no box here 
        
        //go back to main window button
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,4,4);
        cancel.setOnAction(e->window.close());
        
        //confirm and send button
        Button reg = new Button("Register");
        GridPane.setConstraints(reg,4,5);
        
        //insert given values to database
        
        //insert new row in database person
         reg.setOnAction(e->{
             //Person bean = new Person("carrie2","bambi","bambi");
            Person bean  = new Person();
            bean.setUsername(username.getText());
            bean.setPword(password.getText());
            bean.setPword2(password2.getText());
          
            
            //validating data 
            /*if(isInt(age,age.getText()))
                bean.setAge(age.getText());
            if(isInt(height,height.getText()));
                bean.setHeight(height.getText());
            if(isInt(weight, weight.getText()));
                bean.setWeight(weight.getText());
            */
                
            try {
                boolean result = ManagerPerson.insertPerson(bean);
                System.out.println(result);
                if(result){
                    System.out.println("New row with primary key "+ bean.getId() + " was inserted");   
                }
            } catch (SQLException ex) {
                Logger.getLogger(popUpWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
              
            confirmation= true;//saved
            
            window.close();
        });
        
        
        grid.getChildren().addAll(username, password,password2, age,gender,height
          ,cancel,reg);
        
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
        
        return confirmation;
        
    }
    
    public boolean login(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        
        
        //user enter info here 
        GridPane grid2  = new GridPane();
        grid2.setPadding(new Insets(10,10,10,10));
        grid2.setVgap(8);
        grid2.setHgap(10);
        
       
        
        //username input
        TextField username = new TextField("");
        username.setPromptText("username");
        GridPane.setConstraints(username,1,0);
        
 
        //password 
        TextField password = new TextField();
        password.setPromptText("password");
        GridPane.setConstraints(password, 1, 1);
        
        Hyperlink link = new Hyperlink("Forgot Password");
        link.setOnAction(e->{
            
            
        });
        //go back to main window button
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
        
        //confirm and send button
        Button login = new Button("Login");
       
        GridPane.setConstraints(login,1,2);
        //save to database?
   
        
         login.setOnAction(e->{

            
            try {
               Person bean= ManagerPerson.getPerson(username.getText());
               if(bean==null){
                   Util.alertBox("Error","User was not found");
             }else{
             System.out.println("Username: " + bean.getUsername());
             System.out.println("Password: " + bean.getPword());
             profileObj.restrictions("Profile");
             System.out.println("Restrictions chosen");
              
             }
            } catch (SQLException ex) {
                Logger.getLogger(popUpWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

            confirmation= true;//save to database here 

            window.close();
        });
        
        
        grid2.getChildren().addAll(username,
                password,cancel,login);
        
 
        
        Scene scene = new Scene(grid2);
        window.setScene(scene);
        window.showAndWait();
        
        return confirmation;
        
    }
   
    
    public void admin(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        
        //user enter info here 
        GridPane grid3  = new GridPane();
        grid3.setPadding(new Insets(10,10,10,10));
        grid3.setVgap(8);
        grid3.setHgap(10);
        
       
        //username input
        TextField adminUsername = new TextField("");
        adminUsername.setPromptText("username");
        GridPane.setConstraints(adminUsername,1,0);
       
        
        //password input
        TextField password = new TextField();
        password.setPromptText("password");
        GridPane.setConstraints(password, 1, 1);
        
        //go back to main window button
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
        
        //confirm and send button
        Button adminLog = new Button("Login");
        GridPane.setConstraints(adminLog,1,2);
        //save to database?
         adminLog.setOnAction(e->{
             
             try {
                
                Person bean= ManagerPerson.getPerson(adminUsername.getText());
               if(bean==null){
                   Util.alertBox("Error","Admin was not found");
             }else{
             System.out.println("Username: " + bean.getUsername());
             System.out.println("Password: " + bean.getPword());
             
             Profile adminProfile = new Profile();
             adminProfile.adminWindow("Welcome Admin");
            
             
             
             }
            } catch (SQLException ex) {
                Logger.getLogger(popUpWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
                         
             
             
             
            confirmation= true;//confimation
            //open new window to select plan
            //call function on det plan class
            
            window.close();
            
            
        });
        
        
        grid3.getChildren().addAll(adminUsername,
                password,cancel,adminLog);
        
        Scene scene = new Scene(grid3);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
   
}
    
    
    
    
    

