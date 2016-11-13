
package appgui;

import static appgui.popUpWindow.confirmation;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;


/**
 *
 * @author Carrie Dumit, diet  plann given based on selected restrictions and preferances
 */
public class dietPlan {
    ListView <String> listRestrictions;
    ListView <String> listDiet;
    
    
    public  void restrictions (String title ){
    
        String seafood = "Seafood";
        String redMeat = "Red Meat";
        String shellfish = "Shellfish";;
        String shrimp = "Shrimp";
        String legume = "Legume";
        String lactose = "Lactose";
        String gluten = "Gluten";
        String grain = "Grain";
        String whiteBread = "White Bread";
        String soy = "Soy";
        
       
        listRestrictions = new ListView();
        listRestrictions.getItems().addAll(seafood,redMeat,shellfish,shrimp,legume,lactose,
                gluten,grain,whiteBread, soy);
        listRestrictions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
        
      
        
        //confirm and send button
        Button done = new Button("Done");
        GridPane.setConstraints(done,2,2);
        //save al users selection when done
        done.setOnAction(e->{
         
            handleRestrictions();
            window.close();
            
          
            
        });
           VBox layout = new VBox (10);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(cancel, done, listRestrictions);
                   
 
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
    
    
    public void handleRestrictions(){
        String message = "Restrictions chosen: ";
        ObservableList<String> restrictions;
        restrictions= listRestrictions.getSelectionModel().getSelectedItems();
        for(String r: restrictions)
        {
            message += r+ "\n";
        }
        System.out.println(message);

    }
    public void handleChangesDiet(){
        String message = "Cahnges made: ";
        ObservableList<String> changes;
        changes= listDiet.getSelectionModel().getSelectedItems();
        for(String r: changes)
        {
            message += r+ "\n";
        }
        System.out.println(message);

    }
    
    
    public void modifyDiet(String title){
        //show what's on their diet 
   
        String seafood = "Seafood";
        String redMeat = "Red Meat";
        String shellfish = "Shellfish";;
        String shrimp = "Shrimp";
        String legume = "Legume";
        String lactose = "Lactose";
        String gluten = "Gluten";
        String grain = "Grain";
        String whiteBread = "White Bread";
        String soy = "Soy";
        
       
        listDiet = new ListView();
        listDiet.getItems().addAll(seafood,redMeat,shellfish,shrimp,legume,lactose,
                gluten,grain,whiteBread, soy);
        listDiet.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(500);
        
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
        
      
        
        //confirm and send button
        Button done = new Button("Done");
        GridPane.setConstraints(done,2,2);
        //save al users selection when done
        done.setOnAction(e->{
         
            handleChangesDiet();
            window.close();
            
          
            
        });
           VBox layout = new VBox (10);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(cancel, done,listDiet);
                   
 
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
 }
           
         