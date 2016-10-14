/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Carrie Dumit
 */
public class AppGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Diet Planner");
        Button btn = new Button("Start your new diet!");
        btn.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            System.out.println("Hello");
        }
        
    });
                
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Scene scene1 = new Scene(root, 500, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();
        
        
        
        
        
       
    }

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        AppGUI.launch(args);
    }
    
} 
