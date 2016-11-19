/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui.tables;

import appgui.DBConnection;
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
public class ManagerPerson {
    
    
    public static void displayData(ResultSet rs) throws SQLException{
        while(rs.next()){
         StringBuffer buffer = new StringBuffer();
         
         buffer.append("Person " + rs.getInt("id") + ": ");
         System.out.println(buffer.toString());
         
         
         
         
        }
        
    }
    public static Person getPerson(String username) throws SQLException{
        String sql = "SELECT * FROM person WHERE username = ?";
        ResultSet rs = null;
        try (
                Connection conn =new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
               Person bean = new Person();
               bean.setId(rs.getInt("id"));
               bean.setUsername(rs.getString("username"));
               bean.setPword(rs.getString("pword"));
               bean.setAge(rs.getInt("age"));
               bean.setGender(rs.getString("gender"));
               bean.setHeight(rs.getInt("height"));
               bean.setWeight(rs.getInt("weight"));
               bean.setBMI(rs.getDouble("BMI"));
               bean.setWeight(rs.getInt("weight"));
               bean.setAthletic(rs.getBoolean("athletic"));
               
               return bean;
                
            }else{
              
              return null;
            }          
           
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }finally{
            if (rs != null){
                rs.close();
            }
        }
        
    }
        
        
    public static boolean insertPerson(Person bean) throws SQLException{
        String sql = "INSERT into person (username,pword)" +
                "VALUES (?,?)";
        ResultSet keys = null;
        
        try(
                Connection conn = new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                
                ){
            stmt.setString(1, bean.getUsername());
            stmt.setString(2,bean.getPword());
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
    
    
    public static boolean deletePerson(String username) throws Exception{
        String sql = "DELETE FROM person WHERE username = ?";
        try(
                Connection conn = new DBConnection().connect();
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                ){
            stmt.setString(1, username);
            int affected = stmt.executeUpdate();
            
            if(affected==1){
                return true;
            }else{
                System.out.println("No user was found");
                return false;
                
            }
          
        }catch (SQLException e){
            System.err.println(e);
            return false;
        }
        }
    
}
        
    /*public static boolean changePWord(Person bean){
        String sql = "UPDATE person SET" + "username = ?, pword = ?" +
                "WHERE username = ?";
        
        try(
                Connection conn = Connecton().connect());
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
                    stmt.
                }
                
                
    
        
    
        
        */
        
        
    
    


