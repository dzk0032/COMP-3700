/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project_iteration2;
import java.sql.*;

/**
 *
 * @author Daehyun
 */
public class Process {
   private int password;
   private int userID;
   private int userType;
   private String productName;
   private String sellerInfo;
   private int productId;   
   private int quantity;   
   private int expDate;
   private double price;  
   private boolean loginSuccess = false;
   
   
   public Process() {      
   }       
   
   public int getPassword() {
       return password;
   }
   
   public void setPassword(int pwd) {
       password = pwd;
   }
   
   public int getUserID() {
       return userID;
   }
   
   public void setUserID(int id) {
       userID = id;
   }
   
   public int getUserType() {
       return userType;
   }
   
   public void setUserType(int type) {
       userType = type;
   }
   
   public String getProductName() {
      return productName;
   }

   public void setProductName(String newName) {
      productName = newName;
   }

   public String getProductSellerInfo() {
      return sellerInfo;
   }

   public void setProductSellerInfo(String seller) {
      sellerInfo = seller;
   }

   public int getProductId() {
      return productId;
   }

   public void setProductId(int IdEntry) {
      productId = IdEntry;
   }   

   public void setProductQuantity(int newQuant) {
       quantity = newQuant;
   }

   public int getProductQuantity() {
      return quantity;
   }

   public void changeProductQuantity(int num) {
      quantity = num;
   }  

   public int getProductExpDate() {
      return expDate;
   }

   public void setProductExpDate(int date) {
      expDate = date;
   }

   public double getProductPrice() {
      return price;
   }

   public void setProductPrice(double num) {
      price = num;
   }
   
   public boolean getLoginSuccess() {
       return loginSuccess;
   }
   
   public void setLoginSuccess(boolean bool) {
       loginSuccess = bool;
   }
}
