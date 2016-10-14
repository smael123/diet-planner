
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carrie Dumit
 */


public class registration extends Application{
    Button regButton;
    Button loginButton;
    Button loginAdmin;
    //Button ok;
    Scene registrationScene;
    Scene loginScene;
    Scene adminScene;
    Scene windowScene;
    
    Stage mainWindow;
    @Override
    public void start(Stage primaryStage){
        mainWindow= primaryStage;
        VBox layout1 = new VBox(20);
        
        regButton = new Button("Register");
        loginButton= new Button("Login");
        loginAdmin = new Button("Login Admin");
        
        Label label1 = new Label("Start Your New Diet!");
        layout1.getChildren().addAll(label1,regButton,loginButton,loginAdmin);
        layout1.setAlignment(Pos.CENTER);
        regButton.setOnAction(e->{
              boolean confirmation= popUpWindow.userReg("Enter Information Please", "Registration");
              System.out.println(confirmation);
        });
//layout for regitration scene
        /*VBox layout3 = new VBox (20);
        //back= new Button ("Go Back");
        Label label3 = new Label("Please Enter your Information");
        layout3.getChildren().addAll(label3,back);
        layout3.setAlignment(Pos.CENTER);
        registrationScene = new Scene(layout3,400,400);
        //back.setOnAction(e->popUpWindow.userReg(STYLESHEET_MODENA, STYLESHEET_MODENA));
        */
        
        loginButton.setOnAction(e->{
             boolean confirmation= popUpWindow.login("Enter username and password", "Login");
              System.out.println(confirmation);
        }
        );
        
        //layout for login scene
        /*
        VBox layout2 = new VBox (20);
        back = new Button ("Go Back");
        Label label2 = new Label("Please Enter Username and Password");
        layout2.getChildren().addAll(label2,back);
        layout2.setAlignment(Pos.CENTER);
        loginScene = new Scene(layout2,400,400);
        back.setOnAction(e->mainWindow.setScene(windowScene));
     */
        loginAdmin.setOnAction(e->popUpWindow.admin("Welcome Admin", "Login"));
        
        //layout for admin scene
        /*
        VBox layout4 = new VBox (20);
        back = new Button ("Go Back");
        Label label4 = new Label("Enter Username and Password");
        layout4.getChildren().addAll(label4,back);
        layout4.setAlignment(Pos.CENTER);
        adminScene = new Scene(layout4,400,400);
        back.setOnAction(e->mainWindow.setScene(windowScene));
*/
        
       windowScene= new Scene(layout1, 500, 500);
       mainWindow.setTitle("Welcome to the Diet Planner!");
        
        mainWindow.setScene(windowScene);
        mainWindow.show();
       
        
    
        
    }
   // public void mainWindow(){
       // start(mainWindow);
        
   // }


    
    
    public static void main(String args[]){
        Application.launch(args);
    }
}
