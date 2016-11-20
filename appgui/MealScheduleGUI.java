/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.MealSchedule;
import appgui.tables.ManagerMealSchedule;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author lucy
 */
public class MealScheduleGUI {
    private static BorderPane borderPane;
    private static TableView<MealSchedule> mealTable;
    private static Button mainMenuButton;
    private static Label titleLabel;
    private static MealSchedule mealSchedule;
    
    private static Stage window;
    private static Scene scene;
    
    public static void display(int userId, String username)
    {
        
        //buttons and labels and the table
        titleLabel = new Label("Meal Schedule for " + username);
        mainMenuButton = new Button("Return to Main Menu");  
        
        //window initialization
        window = new Stage();
        window.setTitle("Meal Schedule");
        
        //mealTable columns
        TableColumn<MealSchedule, Integer> foodIdColumn = new TableColumn<>("Food ID");
        foodIdColumn.setMinWidth(100);
        foodIdColumn.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        
        
        
    }
    
    private static ObservableList<MealSchedule> populateMealScheduleList(int userId)
    {
        try 
        {
            ObservableList<MealSchedule> meals = ManagerMealSchedule.getMealScheduleList(userId);
            return meals;
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error, no rows affected");
            return null;
        }
    }
}
