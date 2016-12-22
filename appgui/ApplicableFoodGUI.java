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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author lucy
 */
public class ApplicableFoodGUI {
   
    private static BorderPane borderPane;
    private static HBox hBox;
    
    private static TableView<Food> foodTable;
    private static Button mainMenuButton;
    private static Button pickFoodsButton;
    private static Label titleLabel;
    
    private static Stage window;
    private static Scene scene;
    
    //maybe make this a set because they might pick the same thing twice
    private static ArrayList<Food> pickedFoods;
    
    public static ArrayList<Food> display(int userId, String username, int preferenceType, String title)
    {
        System.out.println("ID: " + userId + " Username: " + username);
        
        //buttons and labels and the table
        titleLabel = new Label("Applicable " + title + " Foods for " + username);
        titleLabel.setPadding(new Insets(10,10,10,10));
        //titleLabel.setAlignment(Pos.CENTER);
        mainMenuButton = new Button("Return to Main Menu");
        mainMenuButton.setPadding(new Insets(10,10,10,10));
        //mainMenuButton.setAlignment(Pos.CENTER);
        
        pickFoodsButton = new Button("Pick Foods");
        pickFoodsButton.setPadding(new Insets(10,10,10,10));
        //pickFoodsButton.setAlignment(Pos.CENTER);
        
        //pickedFoods initialization
        pickedFoods = new ArrayList<>();
        
        
        //window initialization
        window = new Stage();
        window.setTitle(title);
        
        //foodTable columns
        TableColumn<Food, String> foodNameColumn = new TableColumn<>("Name");
        foodNameColumn.setMinWidth(100);
        foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        
        TableColumn<Food, String> foodTypeColumn = new TableColumn<>("Type");
        foodTypeColumn.setMinWidth(100);
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        
        //initialize table
        foodTable = new TableView<>();
        foodTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        try 
        {
            switch (preferenceType)
            {
                case 0:
                    foodTable.setItems(ManagerFood.getNoPreferenceFoods(userId));
                    break;
                case 1:
                    foodTable.setItems(ManagerFood.getApplicableMuscleFoods(userId));
                    break;
                case 2:
                    foodTable.setItems(ManagerFood.getApplicableThinFoods(userId));
                    break;
                default:
                    System.out.println("Preference type: " + preferenceType + " is not valid.");
                    break;
 
            }
                    
            
        } 
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
        }
        foodTable.getColumns().addAll(foodNameColumn, foodTypeColumn);
        
        //event listeners
        
        pickFoodsButton.setOnAction(e->{
            
            for (Food x : foodTable.getSelectionModel().getSelectedItems())
            {
                pickedFoods.add(x);
            }
        });
        
        hBox = new HBox();
        hBox.getChildren().addAll(mainMenuButton, pickFoodsButton);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setAlignment(Pos.CENTER);
        
        borderPane = new BorderPane();
        borderPane.setTop(titleLabel);
        borderPane.setCenter(foodTable);
        borderPane.setBottom(hBox);
        
        scene = new Scene(borderPane, 500, 500);
        window.setScene(scene);
        window.showAndWait();
        
        return pickedFoods;
    }
}
