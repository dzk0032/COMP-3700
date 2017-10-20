/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sky45611
 */


public class Adapter {
   private Connection connection;
   private ArrayList<String> names = new ArrayList<String>();
   private ArrayList<Double> prices = new ArrayList<Double>();
   private ArrayList<Integer> quantity = new ArrayList<Integer>();
   
   public Adapter(Connection connection) {
      this.connection = connection;
   } 
   
   public boolean exists(int id) {
       try {
           String query = "SELECT * FROM PRODUCT WHERE ID = " + id;
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           
           if (resultSet.next() == true) {
               return true;
           }           
       }
       catch (SQLException e) {
           System.out.println("Does not exist");
       }
       return false;
   }
   
   public Product load(int id) {
      try {
         String query = "SELECT * FROM PRODUCT WHERE ID = " + id;
         //DEBUG
         //System.out.println(query);
         //
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);
         if (resultSet.next()) {
            Product product = new Product();                            
            product.setId(resultSet.getInt(1));    
            product.setName(resultSet.getString(2));
            product.changeQuantity(resultSet.getInt(3));
            product.setPrice(resultSet.getDouble(4));
            product.setExpDate(resultSet.getInt(5));
            product.setSellerInfo(resultSet.getString(6));
                            
            resultSet.close();
            statement.close();
            return product;
         }
      
      } catch (SQLException e) {
         System.out.println("Database access error!");
         e.printStackTrace();
      }
      return null;
   }
   
   public boolean add(Product product) {
       boolean status = false; 
       try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?");
            statement.setInt(1, product.getId());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next() == false) {
                statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES (?, ?, ?, ?, ?, ?)");
                statement.setInt(1, product.getId());
                statement.setString(2, product.getName());
                statement.setInt(3, product.getQuantity());
                statement.setDouble(4, product.getPrice());
                statement.setInt(5, product.getExpDate());
                statement.setString(6, product.getSellerInfo());
                status = true;                
            }
        
            statement.execute();
            resultSet.close();
            statement.close();
            
        }   
                
        catch (SQLException e) {
            System.out.println("Database access error!");
        }          
       return status;
   }
   
   public boolean update(Product product) {
       boolean status = false;
       
       try {
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?");
            statement.setInt(1, product.getId());
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next() == true) {
                statement = connection.prepareStatement("UPDATE PRODUCT SET Name = ?, "
                        + "Price = ?, Quantity = ?, ExpirationDate = ?, SellerInfo = ? WHERE ID = ?");
                statement.setString(1, product.getName());
                statement.setDouble(2, product.getPrice());
                statement.setInt(3, product.getQuantity());
                statement.setInt(4, product.getExpDate());
                statement.setString(5, product.getSellerInfo());
                statement.setInt(6, product.getId());
                status = true;
            }
            statement.execute();
            resultSet.close();
            statement.close();
       }
       
       catch (SQLException e) {
           System.out.println("Database access error!");
       }
       
       return status;
   }
   
   public void purchase(Product product) {       
       int id = product.getId();
       String name = product.getName();
       int quantity;       
       int quantityOriginal = product.getQuantity();
       double price = product.getPrice();
       
       try {
           String query = "SELECT * FROM PURCHASE WHERE ID = " + id;
           Statement check = connection.createStatement();
           ResultSet resultSet = check.executeQuery(query);
           
           PreparedStatement statement = connection.prepareStatement("INSERT INTO "
                    + "PURCHASE VALUES (?, ?, ?, ?)");
           
           if (resultSet.next() == false) {
               quantity = 1;
               statement.setInt(1, id);
               statement.setString(2, name);
               statement.setInt(3, quantity);
               statement.setDouble(4, price);
            }
           else {
               quantity = resultSet.getInt(3);
               quantity++;
               statement = connection.prepareStatement("UPDATE PURCHASE SET Quantity = ? "
                    + "WHERE ID =" + id);
               statement.setInt(1, quantity);               
           }
           
           PreparedStatement statement2 = connection.prepareStatement("UPDATE PRODUCT SET Quantity = ? "
                   + "WHERE ID = " + id);
           quantityOriginal--;
           statement2.setInt(1, quantityOriginal);
           
           resultSet.close();
           check.close();
           statement.execute();
           statement.close();
           statement2.execute();
           statement2.close();
       }
       
       catch (SQLException e) {
           System.out.println("Database access error!");
       }      
   }
   
   public String purchaseList() {
              
       String receipt = "<html>";
       double total = 0;       
       
       try {
           String query = "SELECT * FROM PURCHASE";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()) {
               names.add(resultSet.getString(2));
               quantity.add(resultSet.getInt(3));
               prices.add(resultSet.getDouble(4));
               total += resultSet.getDouble(4) * resultSet.getInt(3);                             
           }
           
           //since the three arraylists' sizes are the same
           for (int i = 0; i < names.size(); i++) {
               receipt += names.get(i) + "<br>Quantity: " + quantity.get(i) + "<br>$: " + Double.toString(prices.get(i) * quantity.get(i)) + " <br> <br>";
           }
           names.clear();
           quantity.clear();
           prices.clear();
           
           receipt += "Total: $" + Double.toString(total) + "<html/>";           
           
           resultSet.close();
           statement.close();
           
       }
       catch (SQLException e) {
           System.out.println("Error accessing database");
       }
       return receipt;
   }
   
   public double getChange(double amt) {
       double change = 0;
       double total = 0;
       
       try {
           String query = "SELECT * FROM PURCHASE";
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query);
           
           while (resultSet.next()) {
               total += resultSet.getDouble(4) * resultSet.getInt(3);
           }
           
           change = amt - total;
           resultSet.close();
           statement.close();
       }
       
       catch (SQLException e) {
           System.out.println("Error accessing database");
       }      
       
       return change;
   }
   
   public void removePurchaseList() {
       try {
           String query = "DELETE FROM PURCHASE";
           Statement statement = connection.createStatement();
           statement.executeQuery(query);
           
           statement.close();
       }
       catch(SQLException e) {
           //No item to pay for
       }      
   }
   
}
