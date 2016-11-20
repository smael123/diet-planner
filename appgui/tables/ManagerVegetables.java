/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.beans.Vegetables;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucy
 */
public class ManagerVegetables {
    public static Vegetables getVegetables(int foodId) throws SQLException
    {
        String sql = "SELECT * FROM vegetables WHERE foodId = ?";
        ResultSet rs = null;
        
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(foodId));
            rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                Vegetables bean = new Vegetables();
                bean.setId(rs.getInt("id"));
                bean.setFoodType(rs.getString("foodType"));
                bean.setTypeOfVegetable(rs.getString("typeOfVegetable"));
                bean.setLegume(rs.getInt("legume"));
                bean.setComplexCarb(rs.getInt("complexCarb"));
                              
                return bean;
            }
            else
            {
                System.err.println("No rows affected");
                return null;
            }
        }
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
            return null;        
        }
        finally
        {
            if (rs != null)
            {   
                 rs.close();   
            }
        }                
    }
}
