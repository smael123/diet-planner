/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
import appgui.beans.Food;
import appgui.beans.FriendlySchedule;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lucy
 */
public class ManagerFriendlySchedule {
    
    public static ObservableList<FriendlySchedule> getFriendlySchedule(int userId) throws SQLException
    {
        String sql = "CALL getFriendlyMealSchedule(?)";
        ResultSet rs = null;
        ObservableList<FriendlySchedule> friendlyList = FXCollections.observableArrayList(); //friendly neighborhood spiderman!

        try (Connection conn = new DBConnection().connect();
             CallableStatement stmt = conn.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);)
             
        {
            int numberOfRows = 0;
            
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            
            while (rs.next())
            {
                numberOfRows++;
                FriendlySchedule friendlyBean = new FriendlySchedule();
                friendlyBean.setFoodName(rs.getString("foodName"));
                friendlyBean.setFoodType(rs.getString("foodType"));
                switch (rs.getInt("dayTime"))
                {
                    case 0:
                        friendlyBean.setDayTime("Breakfast");
                        break;
                    case 1:
                        friendlyBean.setDayTime("Lunch");
                        break;
                    default:
                        friendlyBean.setDayTime("Dinner");
                        break;
                }
                
                switch (rs.getInt("day"))
                {
                    case 0:
                        friendlyBean.setDay("Sunday");
                        break;
                    case 1:
                        friendlyBean.setDay("Monday");
                        break;
                    case 2:
                        friendlyBean.setDay("Tuesday");
                        break;
                    case 3:
                        friendlyBean.setDay("Wednesday");
                        break;
                    case 4:
                        friendlyBean.setDay("Thursday");
                        break;
                    case 5:
                        friendlyBean.setDay("Friday");
                        break;
                    case 6:
                        friendlyBean.setDay("Saturday");
                        break;
                }
                
                friendlyBean.setCalorie(rs.getInt("calorieCount"));
                friendlyBean.setTotalCalorie(rs.getInt("totalCalorie"));
                friendlyBean.setTotalFat(rs.getInt("totalFat"));
                friendlyBean.setCarb(rs.getInt("carbCount"));
                friendlyBean.setProtein(rs.getInt("proteinCount"));
                friendlyBean.setVitaminC(rs.getDouble("vitaminCDV"));
                friendlyBean.setCalcium(rs.getDouble("calciumDV"));
                
                friendlyList.add(friendlyBean);
            }
            
            
            if (numberOfRows == 0)
            {
                friendlyList.add(new FriendlySchedule());
            }
        }
        catch (SQLException ex) 
        {
            System.err.println(ex.getMessage());
            friendlyList.add(new FriendlySchedule());
            return friendlyList;        
        }
        finally
        {
            if (rs != null)
            {   
                 rs.close();   
            }
        }
        
        return friendlyList;
    }
}
