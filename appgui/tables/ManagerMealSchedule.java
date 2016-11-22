/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
import appgui.beans.MealSchedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Carrie Dumit
 */
public class ManagerMealSchedule {
    
    public static boolean setMealSchedule(MealSchedule mealBean) throws SQLException
    {
        String sql = "INSERT into mealschedule (userId, foodId, quantity, day, dayTime) " +
        "VALUES (?,?,?,?,?)";

        ResultSet keys = null;
        
        try(Connection conn = new DBConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setInt(1, mealBean.getUserId());
            stmt.setInt(2, mealBean.getFoodId());
            stmt.setInt(3, mealBean.getQuantity());
            stmt.setInt(4, mealBean.getDay());
            stmt.setInt(5, mealBean.getDayTime());
            
            int affected = stmt.executeUpdate();
            
            if (affected == 1)
                {
                    keys = stmt.getGeneratedKeys();
                    keys.next();
//                    int newKey = keys.getInt(1);
//                    nutritionBean.setId(newKey);
                }
                else
                {
                    System.err.println("No rows affected");
                    return false;
                }
        } 
            catch (SQLException sqle) 
            {
                System.err.println(sqle);
                return false;
            }
            finally
            {
                if (keys!=null)keys.close();
            }
            
            return true;
    }
    
    public static ObservableList<MealSchedule> getMealScheduleList(int userId) throws SQLException
    {
        String sql = "SELECT * FROM mealSchedule WHERE userId";
        ResultSet rs = null;
        ObservableList<MealSchedule> meals = FXCollections.observableArrayList();

        try (Connection conn = new DBConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            rs = stmt.executeQuery(sql);
            
            while (rs.next())
            {
                MealSchedule mealBean = new MealSchedule();
                mealBean.setId(rs.getInt("id"));
                mealBean.setUserId(rs.getInt("userId"));
                mealBean.setFoodId(rs.getInt("foodId"));
                mealBean.setQuantity(rs.getInt("quantity"));
                mealBean.setDay(rs.getInt("day"));
                mealBean.setDayTime(rs.getInt("dayTime"));
                
                meals.add(mealBean);
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
        
        return meals;
    }
}
