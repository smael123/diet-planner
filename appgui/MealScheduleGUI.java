/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import appgui.beans.FriendlySchedule;
import appgui.beans.MealSchedule;
import appgui.tables.ManagerFriendlySchedule;
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
    
    private static TableView<FriendlySchedule> mealTable;
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
        TableColumn<FriendlySchedule, String> foodNameColumn = new TableColumn<>("Name");
        foodNameColumn.setMinWidth(100);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        
        TableColumn<FriendlySchedule, String> foodTypeColumn = new TableColumn<>("Type");
        foodTypeColumn.setMinWidth(100);
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        
        TableColumn<FriendlySchedule, String> dayColumn = new TableColumn<>("Day");
        dayColumn.setMinWidth(100);
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        
        TableColumn<FriendlySchedule, String> dayTimeColumn = new TableColumn<>("Day Time");
        dayTimeColumn.setMinWidth(100);
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dayTime"));
        
        TableColumn<FriendlySchedule, Integer> calorieColumn = new TableColumn<>("Calorie");
        calorieColumn.setMinWidth(100);
        calorieColumn.setCellValueFactory(new PropertyValueFactory<>("calorie"));
        
        TableColumn<FriendlySchedule, Integer> totalCalorieColumn = new TableColumn<>("Total Calorie");
        totalCalorieColumn.setMinWidth(100);
        totalCalorieColumn.setCellValueFactory(new PropertyValueFactory<>("totalCalorie"));
        
        TableColumn<FriendlySchedule, Integer> totalFatColumn = new TableColumn<>("Total Fat");
        totalFatColumn.setMinWidth(100);
        totalFatColumn.setCellValueFactory(new PropertyValueFactory<>("totalFat"));
        
        TableColumn<FriendlySchedule, Integer> carbColumn = new TableColumn<>("Carb");
        carbColumn.setMinWidth(100);
        carbColumn.setCellValueFactory(new PropertyValueFactory<>("carb"));
        
        TableColumn<FriendlySchedule, Integer> proteinColumn = new TableColumn<>("Protein");
        proteinColumn.setMinWidth(100);
        proteinColumn.setCellValueFactory(new PropertyValueFactory<>("protein"));
        
        TableColumn<FriendlySchedule, Double> vitaminCColumn = new TableColumn<>("Vitamin C DV");
        vitaminCColumn.setMinWidth(100);
        vitaminCColumn.setCellValueFactory(new PropertyValueFactory<>("vitaminC"));
        
        TableColumn<FriendlySchedule, Double> calciumColumn = new TableColumn<>("Calcium DV");
        calciumColumn.setMinWidth(100);
        calciumColumn.setCellValueFactory(new PropertyValueFactory<>("calcium"));
        
        //initialize table
        mealTable = new TableView<>();
        try 
        {
            mealTable.setItems(ManagerFriendlySchedule.getFriendlySchedule(userId));
        } 
        catch (SQLException ex) 
        {
            System.out.println("Error, no rows affected");
        }
        
        //set columns
        mealTable.getColumns().addAll(foodNameColumn);
        mealTable.getColumns().addAll(foodTypeColumn);
        mealTable.getColumns().addAll(dayColumn);
        mealTable.getColumns().addAll(dayTimeColumn);
        mealTable.getColumns().addAll(calorieColumn);
        mealTable.getColumns().addAll(totalCalorieColumn);
        mealTable.getColumns().addAll(totalFatColumn);
        mealTable.getColumns().addAll(carbColumn);
        mealTable.getColumns().addAll(proteinColumn);
        mealTable.getColumns().addAll(vitaminCColumn);
        mealTable.getColumns().addAll(calciumColumn);
        
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
    
//    private static ObservableList<MealSchedule> populateMealScheduleList(int userId)
//    {
//        try 
//        {
//            ObservableList<MealSchedule> meals = ManagerMealSchedule.getMealScheduleList(userId);
//            return meals;
//        } 
//        catch (SQLException ex) 
//        {
//            System.out.println("Error, no rows affected");
//            return null;
//        }
//    }
}
