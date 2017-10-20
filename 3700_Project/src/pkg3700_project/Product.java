/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project;

/**
 *
 * @author sky45611
 */
public class Product {
   private String name;
   private String sellerInfo;
   private int id;
   private int barcodeID;
   private int quantity;
   private int orderQuantity;
   private int expDate;
   private double price;



   public Product(String nameIn, String sellerInfoIn,  int IdIN,  int quantityIN, 
   			 int expDateIN, double priceIN) {
      name = nameIn;
      sellerInfo = sellerInfoIn;
      id = IdIN;      
      quantity = quantityIN;      
      expDate = expDateIN;
      price = priceIN;
   }
   
   public Product() {
       
   }

   public String getName() {
      return name;
   }

   public void setName(String newName) {
      name = newName;
   }

   public String getSellerInfo() {
      return sellerInfo;
   }

   public void setSellerInfo(String seller) {
      sellerInfo = seller;
   }

   public int getId() {
      return id;
   }

   public void setId(int IdEntry) {
      id = IdEntry;
   }   

   public int getQuantity() {
      return quantity;
   }

   public void changeQuantity(int num) {
      quantity = num;
   }  

   public int getExpDate() {
      return expDate;
   }

   public void setExpDate(int date) {
      expDate = date;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double num) {
      price = num;
   }

}

