/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;


import appgui.beans.Fruit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lucy
 */
public class ManagerFruit {
    public static Fruit getFruit(int foodId) throws SQLException
    {
        String sql = "SELECT * FROM fruit WHERE foodId = ?";
        ResultSet rs = null;
        
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(foodId));
            rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                Fruit bean = new Fruit();
                bean.setId(rs.getInt("id"));
                bean.setFoodType(rs.getString("foodType"));
                bean.setNuts(rs.getInt("nuts"));
                bean.setCitrus(rs.getInt("citrus"));
                bean.setLegume(rs.getInt("legume"));
                              
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
