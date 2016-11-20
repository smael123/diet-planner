/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.beans.Grains;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucy
 */
public class ManagerGrains {
    public static Grains getGrains(int foodId) throws SQLException
    {
        String sql = "SELECT * FROM grains WHERE foodId = ?";
        ResultSet rs = null;
        
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(foodId));
            rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                Grains bean = new Grains();
                bean.setId(rs.getInt("id"));
                bean.setFoodType(rs.getString("foodType"));
                bean.setGluten(rs.getInt("gluten"));
                bean.setTypeOfGrain(rs.getString("typeOfGrain"));
                bean.setWhiteBread(rs.getInt("whiteBread"));
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
