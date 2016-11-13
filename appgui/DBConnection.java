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
    
    public DBConnection(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public DBConnection()
    {
        url = "jbdc:mysql://localhost:3306/javaaap";
        username = "root";
        password = "";
    }
    
    public Connection connect() 
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Conection estabilshed.");
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Connection Failure" + cnfe);
        }
        
        try
        {
            DBConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected");
        }
        catch (SQLException se)
        {
            System.out.println("Failed to connect to database." + se);
        }
        
        return DBConnection;
    }
}