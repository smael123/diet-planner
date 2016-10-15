package appgui;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
/**
 *
 * @author Carrie Dumit
 */
public class popUpWindow {
    static boolean confirmation= false;
    
    public static boolean userReg(String message, String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);
        Button cancel= new Button("Cancel");
        //confirm and send
        Button ok = new Button("OK");
        //save to database?
        
        ok.setOnAction(e->{
            confirmation= true;
            window.close();
        });
        cancel.setOnAction(e->window.close());
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label1,cancel,ok);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return confirmation;
        
    }
    
    public static boolean login(String message, String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);
        Button cancel= new Button("Cancel");
        Button ok = new Button("OK");
        //save to database
        ok.setOnAction(e->{
            confirmation = true;
            window.close();
            
        });
        cancel.setOnAction(e->window.close());
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label1,cancel,ok);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return confirmation;
        
    }
    
    
    public static void admin(String message, String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label1 = new Label();
        label1.setText(message);
        Button cancel= new Button("Cancel");
        Button ok = new Button("OK");
        //save to database
        ok.setOnAction(e->window.close());
        cancel.setOnAction(e->window.close());
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label1,cancel);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        
        
    }
    
    
    
    
    
    
}
