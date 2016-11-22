/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
import appgui.beans.Diet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Carrie Dumit
 */
public class ManagerDiet {
    
    public static Diet getDiet(int userId) throws SQLException
    {
        String sql = "SELECT * FROM diet WHERE username = ?";
        ResultSet rs = null;
        try (Connection conn =new DBConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql);)
        {
            stmt.setString(1, Integer.toString(userId));
            rs = stmt.executeQuery();
            if(rs.next())
            {
               Diet bean = new Diet();
               bean.setId(rs.getInt("id"));
               bean.setUserId(rs.getInt("userId"));
               bean.setDailyCalorieCount(rs.getInt("dailyCalorieCount"));
               bean.setDailyFatCount(rs.getInt("dailyFatCount"));
               bean.setDailyCarbCount(rs.getInt("dailyCarbCount"));
               bean.setDailyCholesterolCount(rs.getInt("dailyCholesterolCount"));
               bean.setEgg(rs.getInt("egg"));
               bean.setSeafood(rs.getInt("seafood"));
               bean.setRedMeat(rs.getInt("redMeat"));
               bean.setShellfish(rs.getInt("shellfish"));
               bean.setShrimp(rs.getInt("shrimp"));
               bean.setLegume(rs.getInt("legume"));
               bean.setLactose(rs.getInt("lactose"));
               bean.setGluten(rs.getInt("glutem"));
               bean.setWhiteBread(rs.getInt("whiteBread"));
               bean.setWheat(rs.getInt("wheat"));
               bean.setOats(rs.getInt("oats"));
               bean.setRice(rs.getInt("rice"));
               bean.setCorn(rs.getInt("corn"));

               return bean;   
            }
            else
            { 
              return null;
            }            
        }
        catch(SQLException e)
        {
            System.err.println(e);
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
    
    public static boolean setRestrictions(Diet dietBean) throws SQLException
        {
            String sql = "UPDATE diet SET egg=?, seafood =?, redMeat =?, shellfish =?, shrimp =?, "
                    + "legume =?, lactose =?, gluten =?, whiteBread =?, wheat =?, oats =?, rice =?, corn =?,nuts =?, citrus =? WHERE userId = ?";
            ResultSet keys = null;
            
            try(Connection conn = new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql);)
            {
                
                stmt.setInt(1, dietBean.getEgg());
                stmt.setInt(2, dietBean.getSeafood());
                stmt.setInt(3, dietBean.getRedMeat());
                stmt.setInt(4, dietBean.getShellfish());
                stmt.setInt(5, dietBean.getShrimp());
                stmt.setInt(6, dietBean.getLegume());
                stmt.setInt(7, dietBean.getLactose());
                stmt.setInt(8, dietBean.getGluten());
                stmt.setInt(9, dietBean.getWhiteBread());
                stmt.setInt(10, dietBean.getWheat());
                stmt.setInt(11, dietBean.getOats());
                stmt.setInt(12, dietBean.getRice());
                stmt.setInt(13, dietBean.getCorn());
                stmt.setInt(14, dietBean.getNuts());
                stmt.setInt(15, dietBean.getCitrus());
                stmt.setInt(15, dietBean.getCitrus());
                stmt.setInt(16, dietBean.getUserId());
                
                int affected = stmt.executeUpdate();
                
                if (affected == 1)
                {
                    System.out.println("Successful Query");
                }
                else
                {
                    System.err.println(affected + " rows affected.");
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
