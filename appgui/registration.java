package appgui;


import appgui.popUpWindow;
import appgui.tables.ManagerPerson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import java.sql.SQLException;
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
    popUpWindow popUpWindowObj = new popUpWindow();
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
              boolean confirmation= popUpWindowObj.userReg("Registration");
              System.out.println(confirmation);
        });

        
        loginButton.setOnAction(e->{
            
             boolean confirmation= popUpWindowObj.login("Login");
              System.out.println(confirmation);
        }
        );
        
       
        loginAdmin.setOnAction(e->popUpWindowObj.admin("Login Admin"));
        
      
        
       windowScene= new Scene(layout1, 500, 500);
       mainWindow.setTitle("Welcome to the Diet Planner!");
        
        mainWindow.setScene(windowScene);
        mainWindow.show();
       
        
    
        
    }
  
    public static void main(String args[]) throws SQLException{
        
         Application.launch(args);
        
        
        /*try(
        Connection conn= new DBConnection().connect();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery ("SELECT * FROM person");
                ){
           // rs.last();
            ManagerPerson.displayData(rs);
                    
             //System.out.println("Number of rows: " + rs.getRow());
            Application.launch(args);
             
        }catch (SQLException e){
            System.err.println(e);
        }
       */
        
    }
}
