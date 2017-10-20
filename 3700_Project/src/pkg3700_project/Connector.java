/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project;
import java.sql.*;
import pkg3700_project.Adapter;

public class Connector {
   private Connection connection;
   public void connect() {
      try {
         Class.forName("org.sqlite.JDBC");
         connection = DriverManager.getConnection("jdbc:sqlite:PRODUCT.db");
      }
      catch (ClassNotFoundException ex) {
         System.out.println("SQLite not installed!");
      }
      
      catch (SQLException e) {
         System.out.println("SQLite database not ready!");
      }
    
   }
 
   public Connection getConnection() {
      return connection;
   }
}
   