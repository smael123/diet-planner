/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.Food;
import appgui.beans.MealSchedule;
import appgui.tables.ManagerFood;
import appgui.tables.ManagerMealSchedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author lucy
 */
public class ApplicableFoodGUI {
   
    private static BorderPane borderPane;
    private static TableView<Food> foodTable;
    private static Button mainMenuButton;
    private static Label titleLabel;
    
    private static Stage window;
    private static Scene scene;
    
    public static void display(int userId, String username) throws SQLException
    {
        System.out.println("ID: " + userId + " Username: " + username);
        
        //buttons and labels and the table
        titleLabel = new Label("Applicable Foods for " + username);
        mainMenuButton = new Button("Return to Main Menu");
        
        
        //window initialization
        window = new Stage();
        window.setTitle("Applicable Foods");
        
        //mealTable columns
        TableColumn<Food, String> foodNameColumn = new TableColumn<>("Name");
        foodNameColumn.setMinWidth(100);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        
        TableColumn<Food, String> foodTypeColumn = new TableColumn<>("Type");
        foodTypeColumn.setMinWidth(100);
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        
        //initialize table
        foodTable = new TableView<>();
        foodTable.setItems(populateApplicableFoodList(userId));
        foodTable.getColumns().addAll(foodNameColumn, foodTypeColumn);
        
        borderPane = new BorderPane();
        borderPane.setTop(titleLabel);
        borderPane.setCenter(foodTable);
        borderPane.setBottom(mainMenuButton);
        
        scene = new Scene(borderPane, 500, 500);
        window.setScene(scene);
        window.showAndWait();
    }
    
    private static ObservableList<Food> populateApplicableFoodList(int userId)
    {
        try {
            ObservableList<Food> foodList = ManagerFood.getApplicableMuscleFoods(userId);
            
            return foodList;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
        
    }
}
