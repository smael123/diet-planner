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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

/**
 *
 * @author lucy
 */
public class MealScheduleGUI {
    private static BorderPane borderPane;
    private static HBox buttonBox;
    
    private static TableView<MealSchedule> mealTable;
    private static Button mainMenuButton;
    private static Label titleLabel;
    
    private static Stage window;
    private static Scene scene;
    
    public static void display(int userId, String username)
    {
        
        //buttons and labels and the table
        titleLabel = new Label("Meal Schedule for " + username);
        titleLabel.setPadding(new Insets(10,10,10,10));
        mainMenuButton = new Button("Return to Main Menu");  
        mainMenuButton.setPadding(new Insets(10,10,10,10));

        
        //window initialization
        window = new Stage();
        window.setTitle("Meal Schedule");
        
        //mealTable columns
        TableColumn<MealSchedule, Integer> foodIdColumn = new TableColumn<>("Food ID");
        foodIdColumn.setMinWidth(100);
        foodIdColumn.setCellValueFactory(new PropertyValueFactory<>("foodId"));
        
        //initialize table
        mealTable = new TableView<>();
        try 
        {
            mealTable.setItems(ManagerMealSchedule.getMealScheduleList(userId));
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error, no rows affected");
        }
        
        //set columns
        mealTable.getColumns().addAll(foodIdColumn);
        
        buttonBox = new HBox();
        buttonBox.getChildren().addAll(mainMenuButton);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10,10,10,10));
        buttonBox.setAlignment(Pos.CENTER);
        
        borderPane = new BorderPane();
        borderPane.setTop(titleLabel);
        borderPane.setCenter(mealTable);
        borderPane.setBottom(buttonBox);
        
        scene = new Scene(borderPane, 500, 500);
        window.setScene(scene);
        window.showAndWait();
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
