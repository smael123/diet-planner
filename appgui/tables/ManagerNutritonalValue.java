/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;


import appgui.DBConnection;
import appgui.beans.NutritionalValue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carrie Dumit
 */
public class ManagerNutritonalValue {
    public static NutritionalValue getNutritionalValue(int foodId) throws SQLException
    {
        String sql = "SELECT * FROM nutritionalValue WHERE foodId = ?";
        ResultSet rs = null;
        
        try (Connection conn = null;
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(foodId));
            rs = stmt.executeQuery(sql);
            
            if (rs.next())
            {
                NutritionalValue bean = new NutritionalValue();
                //bean.setId(rs.getInt("id"));
                bean.setFoodId(rs.getInt("foodId"));
                bean.setCalorieCount(rs.getInt("calorieCount"));
                bean.setSugarCount(rs.getInt("sugarCount"));
                bean.setTransfatCount(rs.getInt("transfatCount"));
                bean.setSaturatedFatCount(rs.getInt("saturatedFatCount"));
                bean.setUnsaturatedFatCount(rs.getInt("unsaturatedFatCount"));
                bean.setCarbCount(rs.getInt("carbCount"));
                bean.setProteinCount(rs.getInt("proteinCount"));
                bean.setCholesterolCount(rs.getInt("cholesterolCount"));
                bean.setVitaminBDV(rs.getDouble("vitaminBDV"));
                bean.setVitaminCDV(rs.getDouble("vitaminCDV"));
                bean.setFolicAcidDV(rs.getDouble("folicAcidDV"));
                bean.setCalciumDV(rs.getDouble("calciumDV"));
                
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
    
    public boolean setNutritionalValue(NutritionalValue nutritionBean) throws SQLException
    {
        String sql = "INSERT into diet (calorieCount, sugarCount, saturatedFatCount, unsaturatedFatCount, transfatCount, carbCount, cholesterolCount, proteinCount, vitaminBDV, vitaminCDV, "
                    + "vitaminDDV, folicAcidDV, calciumDV) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) WHERE userId = ?";
            ResultSet keys = null;
            
            try(Connection conn = new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);)
            {
                stmt.setInt(1, nutritionBean.getCalorieCount());
                stmt.setInt(2, nutritionBean.getSugarCount());
                stmt.setInt(3, nutritionBean.getSaturatedFatCount());
                stmt.setInt(4, nutritionBean.getUnsaturatedFatCount());
                stmt.setInt(5, nutritionBean.getTransfatCount());
                stmt.setInt(6, nutritionBean.getCarbCount());
                stmt.setInt(7, nutritionBean.getCholesterolCount());
                stmt.setInt(8, nutritionBean.getProteinCount());
                stmt.setDouble(9, nutritionBean.getVitaminBDV());
                stmt.setDouble(10, nutritionBean.getVitaminCDV());
                stmt.setDouble(11, nutritionBean.getVitaminDDV());
                stmt.setDouble(12, nutritionBean.getFolicAcidDV());
                stmt.setDouble(13, nutritionBean.getCalciumDV());
                
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
}
