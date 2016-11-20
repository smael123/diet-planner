/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.beans.Meat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucy
 */
public class ManagerMeat {
    public static Meat getMeat(int foodId) throws SQLException
    {
        String sql = "SELECT * FROM meat WHERE foodId = ?";
        ResultSet rs = null;
        
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(foodId));
            rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                Meat bean = new Meat();
                bean.setId(rs.getInt("id"));
                bean.setFoodType(rs.getString("foodType"));
                bean.setMeatType(rs.getString("meatType"));
                bean.setEgg(rs.getInt("egg"));
                bean.setSeafood(rs.getInt("seafood"));
                bean.setRedMeat(rs.getInt("redMeat"));
                bean.setShellfish(rs.getInt("shellfish"));
                bean.setShrimp(rs.getInt("shrimp"));
                              
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
