package appgui;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.stage.*;
//import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import javafx.scene.control.CheckBox;

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
        TextField username2 = new TextField();
        username2.setPromptText("confirm username");
        GridPane.setConstraints(username2, 2, 1);
        
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
        
        //save to database?
         reg.setOnAction(e->{
             //data valiation
            isInt(age, age.getText());
            isInt(height,height.getText());
            isInt(weight, weight.getText());
            
            
            confirmation= true;//saved
            window.close();
        });
        
        
        grid.getChildren().addAll(username, username2, password,password2, age,gender,height
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
        
        //go back to main window button
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
        
        //confirm and send button
        Button login = new Button("Login");
       
        GridPane.setConstraints(login,1,2);
        //save to database?
   
        
         login.setOnAction(e->{
             //call function to start diet and select restrictions
             profileObj.restrictions("Profile");
              System.out.println("Restrictions chosen");
              
       
             
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
}
    
    
    
    
    

