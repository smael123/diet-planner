
package appgui;

import appgui.beans.Diet;

import static appgui.popUpWindow.confirmation;
import appgui.tables.ManagerDiet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ListView listDiet = new ListView();
    ArrayList<Integer> restrictionsChosen = new ArrayList<Integer>(Collections.nCopies(15, 0));
    
    public  void restrictions (int userId){
        
     
        Menu menuRestrictions = new Menu("Restrictions");
        
        CheckMenuItem egg = new CheckMenuItem("Eggs");
        CheckMenuItem seafood = new CheckMenuItem("Seafood");
        CheckMenuItem redMeat = new CheckMenuItem("Red Meat");
        CheckMenuItem shellfish = new CheckMenuItem("Shellfish");
        CheckMenuItem shrimp = new CheckMenuItem("Shrimp");
        CheckMenuItem legume = new CheckMenuItem("Legume");
        CheckMenuItem lactose = new CheckMenuItem("Lacotse");
        CheckMenuItem gluten = new CheckMenuItem("Gluten");
        CheckMenuItem whiteBread = new CheckMenuItem("White Bread");
        CheckMenuItem wheat = new CheckMenuItem("Wheat");
        CheckMenuItem oats = new CheckMenuItem("Oats");
        CheckMenuItem rice = new CheckMenuItem("Rice");
        CheckMenuItem corn = new CheckMenuItem("Corn");
        CheckMenuItem nuts = new CheckMenuItem("Nuts");
        CheckMenuItem citrus = new CheckMenuItem("Citrus");
        
         egg.setOnAction(e->{
            if(egg.isSelected()){
              restrictionsChosen.set(0,1); 
              System.out.println("Eggs chosen ");
            }     
        });
   
        seafood.setOnAction(e->{
            if(seafood.isSelected()){
              restrictionsChosen.set(1,1); 
              System.out.println("seafood chosen ");
            }     
        });
        redMeat.setOnAction(e->{
            if(redMeat.isSelected()){
              restrictionsChosen.set(1,1); 
              System.out.println("Red meat chosen ");
              }
               
        });
        shellfish.setOnAction(e->{
            if(shellfish.isSelected()){
              restrictionsChosen.set(3,1); 
              System.out.println("Shellfish chosen " );
              }
               
        });
        shrimp.setOnAction(e->{
            if(shrimp.isSelected()){
              restrictionsChosen.set(4,1); 
              System.out.println("Shrimp ");
              }
               
        });
        legume.setOnAction(e->{
            if(legume.isSelected()){
              restrictionsChosen.set(5,1); 
              System.out.println("Legume chosen ");
              }   
        });
        lactose.setOnAction(e->{
            if(lactose.isSelected()){
              restrictionsChosen.set(6,1); 
              System.out.println("Lactose chosen " );
              }   
        });
        gluten.setOnAction(e->{
            if(gluten.isSelected()){
              restrictionsChosen.set(7,1); 
              System.out.println("Gluten chosen " );
              }   
        });
        
        whiteBread.setOnAction(e->{
            if(whiteBread.isSelected()){
              restrictionsChosen.set(8,1); 
              System.out.println("White bread chosen ");
              }   
        });
        wheat.setOnAction(e->{
            if(wheat.isSelected()){
              restrictionsChosen.set(9,1); 
              System.out.println("Wheat chosen ");
              }   
        });
        oats.setOnAction(e->{
            if(oats.isSelected()){
              restrictionsChosen.set(10,1); 
              System.out.println("Oats chosen ");
              }   
        });
        rice.setOnAction(e->{
            if(rice.isSelected()){
              restrictionsChosen.set(11,1); 
              System.out.println("Rice chosen ");
              }   
        });
        corn.setOnAction(e->{
            if(corn.isSelected()){
              restrictionsChosen.set(12,1); 
              System.out.println("Corn chosen ");
              }   
        });
        nuts.setOnAction(e->{
            if(nuts.isSelected()){
              restrictionsChosen.set(13,1); 
              System.out.println("Nuts ");
              }   
        });
        citrus.setOnAction(e->{
            if(citrus.isSelected()){
              restrictionsChosen.set(9,1); 
              System.out.println("Citrus chosen ");
              }   
        });
        
        
        
         menuRestrictions.getItems().addAll(egg,seafood,redMeat,shellfish,shrimp,legume,lactose, gluten,whiteBread,wheat,oats,rice,corn,
                 nuts,citrus);
         MenuBar restrictionsMenu = new MenuBar();
         restrictionsMenu.getMenus().addAll(menuRestrictions);
                
        
        
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(500);
        Label intructions = new Label("Choose items one by one and then hit done");
        Button cancel= new Button("Cancel");
        GridPane.setConstraints(cancel,2,2);
        cancel.setOnAction(e->window.close());
         
        //confirm and send button
        Button done = new Button("Done");
        GridPane.setConstraints(done,2,2);
        //save al users selection when done
        done.setOnAction(e->{
         for (int i =0 ; i < restrictionsChosen.size(); i++){
            System.out.println(restrictionsChosen.get(i));
        }
            try {
                handleRestrictions(userId);
            } catch (SQLException ex) {
                Logger.getLogger(dietPlan.class.getName()).log(Level.SEVERE, null, ex);
            }
            window.close();
           
        });
           VBox layout = new VBox (10);
           layout.setPadding(new Insets (20,20,20,20));
           layout.getChildren().addAll(cancel, done,intructions,restrictionsMenu);
                   
 
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
       
        
    }
   
    public void handleRestrictions(int userId) throws SQLException{
        
        Diet bean = new Diet();
        bean.setEgg(restrictionsChosen.get(0));
        bean.setSeafood(restrictionsChosen.get(1));
        bean.setRedMeat(restrictionsChosen.get(2));
        bean.setShellfish(restrictionsChosen.get(3));
        bean.setShrimp(restrictionsChosen.get(4));
        bean.setLegume(restrictionsChosen.get(5));
        bean.setLactose(restrictionsChosen.get(6));
        bean.setGluten(restrictionsChosen.get(7));
        bean.setWhiteBread(restrictionsChosen.get(8));
        bean.setWheat(restrictionsChosen.get(9));
        bean.setOats(restrictionsChosen.get(10));
        bean.setRice(restrictionsChosen.get(11));
        bean.setCorn(restrictionsChosen.get(12));
        bean.setNuts(restrictionsChosen.get(13));
        bean.setCitrus(restrictionsChosen.get(14));
        bean.setUserId(userId);
        
        //ManagerDiet managerObj = new ManagerDiet();
       boolean affected= ManagerDiet.setRestrictions(bean);
       System.out.println("Rows affected " + affected);
        

    }
    /*public void handleChangesDiet(){
        String message = "Cahnges made: ";
        ObservableList<String> restrictions;
        restrictions= listDiet.getSelectionModel().getSelectedItems();
        for(String r: restrictions)
        {
            message += r+ "\n";
        }
        System.out.println(message);

    }
    */
    
    /*public void modifyDiet(String title){
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
*/
 }
           
         