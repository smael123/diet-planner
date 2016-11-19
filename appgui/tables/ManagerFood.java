/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
import appgui.beans.Food;
import appgui.beans.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carrie Dumit
 */
public class ManagerFood {
    
    
    public static boolean addFood(Food bean) throws SQLException{
        String sql = "INSERT INTO food (foodName,foodType)" +
                "VALUES (?,?)";
        ResultSet keys = null;
        
        try(
                Connection conn = new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                ){
            stmt.setString(1, bean.getFoodName());
            stmt.setString(2,bean.getFoodType());
            
            int affected = stmt.executeUpdate();
            
            if(affected ==1){
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setId(newKey);
            }else{
                System.err.println("No rows affected");
                return false;
            }
        }catch (SQLException e){
                    System.err.println(e);
                    return false;
                    }finally {
                        if (keys!=null)keys.close();
        }
        return true;
    
        
    
    }
    
    
}
