
package appgui;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
//import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Carrie Dumit, diet  plann given based on selected restrictions and preferances
 */
public class dietPlan {
   
    
    
    public static void restrictions (String title ){
        
         //checkboxes for restrictions and preferences
        CheckBox egg = new CheckBox("Egg");
        CheckBox seafood = new CheckBox("Seafood");
        CheckBox redMeat = new CheckBox("Red Meat");
        CheckBox shellfish = new CheckBox("Shellfish");
        CheckBox shrimp = new CheckBox("Shrimp");
        CheckBox legume = new CheckBox("Legume");
        CheckBox lactose = new CheckBox("Lactose");
        CheckBox gluten = new CheckBox("Gluten");
        CheckBox grain = new CheckBox("Grain");
        CheckBox whiteBread = new CheckBox("White Bread");
        CheckBox soy = new CheckBox("Soy");
        
        
        
        
        //display menu with checkboxes to select restrictions
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        VBox box = new VBox(20);
        box.getChildren().addAll(egg,seafood,redMeat,shellfish,shrimp,legume,
                lactose,gluten,grain,grain,whiteBread,soy);
        box.setAlignment(Pos.CENTER);
        
        
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
        
        
        
        
        
    }
    
}
