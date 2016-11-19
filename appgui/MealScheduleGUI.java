/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.MealSchedule;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 *
 * @author lucy
 */
public class MealScheduleGUI {
    private static BorderPane borderPane;
    private static TableView mealTable;
    private static Button mainMenuButton;
    private static Label titleLabel;
    private static MealSchedule mealSchedule;
    
    Stage window;
    Scene scene;
    
    public static void display(String username, MealSchedule mealBean)
    {
        titleLabel = new Label("Meal Schedule for " + username);
        mainMenuButton = new Button("Return to Main Menu");
        mealTable = new TableView();
        mealSchedule = mealBean;
        
        
    }
}
