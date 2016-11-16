/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lucy
 */
public class DBConnection {
    private Connection DBConnection;
    String url;
    String username;
    String password;
    public static final String defaultURL = "jdbc:mysql://localhost:3306/diet_planner?zeroDateTimeBehavior=convertToNull";
    
    
    public DBConnection(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public DBConnection(String username, String password)
    {
        this.url = defaultURL;
        this.username = username;
        this.password = password;
    }
    public DBConnection()
    {
        url = defaultURL;
        username = "root";
        password = "";
    }
    
    public Connection connect() 
    {
        /*try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conection estabilshed.");
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Connection Failure" + cnfe);
        }*/
        
        try
        {
            DBConnection = DriverManager.getConnection(url, username, password);
            System.out.println("checking database connection after creatin new user");
        }
        catch (SQLException se)
        {
            System.out.println("Failed to connect to database." + se);
        }
        
        return DBConnection;
    }
}