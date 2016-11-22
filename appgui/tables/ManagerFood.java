/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
import appgui.beans.Food;
import appgui.beans.MealSchedule;
import appgui.beans.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
    public static ObservableList<Food> getNoPreferenceFoods(int userId) throws SQLException
    {
        String sql = "{call getApplicableFood(?)}";
        return callProcedure(userId, sql);
    }
    
    public static ObservableList<Food> getAllFoods(int userId) throws SQLException
    {
        //String sql = "{call getApplicableFood(?)}";
        String sql = "SELECT foodName, foodType FROM food";
        ResultSet rs = null;
        ObservableList<Food> foodList = FXCollections.observableArrayList();

        try (Connection conn = new DBConnection().connect();
             //CallableStatement stmt = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);)
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            int numberOfRows = 0;
            
            //stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                numberOfRows++;
                Food foodBean = new Food();
                foodBean.setFoodName(rs.getString("foodName"));
                foodBean.setFoodType(rs.getString("foodType"));
                
                foodList.add(foodBean);
            }   
            
            if (numberOfRows == 0)
            {
                foodList.add(new Food());
            }
        }
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
            foodList.add(new Food());
            return foodList;        
        }
        finally
        {
            if (rs != null)
            {   
                 rs.close();   
            }
        }
        
        return foodList;
    }
    
    public static ObservableList<Food> getApplicableMuscleFoods(int userId) throws SQLException
    {
        String sql = "{call getApplicableMuscleFood(?)}";
        return callProcedure(userId, sql);     
    }
    
    public static ObservableList<Food> getApplicableThinFoods(int userId) throws SQLException
    {
        String sql = "{call getApplicableThinFood(?)}";
        return callProcedure(userId, sql);     
    }

    public static ObservableList<Food> callProcedure(int userId, String sql) throws SQLException
    {
        ResultSet rs = null;
        ObservableList<Food> foodList = FXCollections.observableArrayList();

        try (Connection conn = new DBConnection().connect();
             CallableStatement stmt = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);)
             
        {
            int numberOfRows = 0;
            
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                numberOfRows++;
                Food foodBean = new Food();
                foodBean.setId(rs.getInt("id"));
                System.out.println(foodBean.getId());
                foodBean.setFoodName(rs.getString("foodName"));
                foodBean.setFoodType(rs.getString("foodType"));
                
                foodList.add(foodBean);
            }   
            
            if (numberOfRows == 0)
            {
                foodList.add(new Food());
            }
        }
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
            foodList.add(new Food());
            return foodList;        
        }
        finally
        {
            if (rs != null)
            {   
                 rs.close();   
            }
        }
        
        return foodList;
    }
}
