/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project_iteration2;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Daehyun
 */
public class ProcessViewer extends JFrame{
    
       JFrame frame;
       String purchaseText = "";
        
      //declare Panels for use
      JPanel mainMenu = new JPanel(new GridLayout(2, 3));
      JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      JPanel loginBorder = new JPanel(new BorderLayout());
      JPanel loginMenu = new JPanel(new GridLayout(1, 1));
      JPanel loginLast = new JPanel(new GridLayout(1, 1));
      JPanel managerMenu = new JPanel(new BorderLayout());
      JPanel managerTitle = new JPanel(new GridLayout(2, 1));      
      JPanel managerFunction = new JPanel(new GridLayout(3, 1));
      JPanel managerPic = new JPanel(new GridLayout(1, 1));
      JPanel cashierMenu = new JPanel(new BorderLayout());
      JPanel cashierTitle = new JPanel(new GridLayout(2, 1));
      JPanel cashierFunction = new JPanel(new GridLayout(2, 2));
      JPanel cashierPic = new JPanel(new GridLayout(1, 1));
      JPanel productMan = new JPanel(new BorderLayout());      
      JPanel trans = new JPanel(new GridLayout(3, 1));
      JPanel backPanel = new JPanel(new GridLayout(1, 1));
      JPanel transMenu = new JPanel(new BorderLayout());
      JPanel pdtEntry = new JPanel(new GridLayout(6, 6));      
      JPanel pdtLast = new JPanel(new GridLayout(1, 1));      
      JPanel payment = new JPanel(new GridLayout(3, 1));
      JPanel cashMenu = new JPanel(new GridLayout(2, 1));
      JPanel cashMenuButtons = new JPanel(new GridLayout(1, 2));
      JPanel cashMenuBorder = new JPanel(new BorderLayout());     
      JPanel cardMenu = new JPanel(new GridLayout(2, 1));
      JPanel cardMenuButtons = new JPanel(new GridLayout(1, 2));
      JPanel cardMenuBorder = new JPanel(new BorderLayout());
      JPanel receiptBorder = new JPanel(new BorderLayout());
      
      //declare buttons for use
      JButton login = new JButton("Login");
      JButton logout = new JButton("Logout");
      JButton changePassword = new JButton("Change Password");
      JButton changeProfilePic = new JButton("Change/Add profile picture");
      JButton createAccount = new JButton("Create new User");
      JButton transaction = new JButton("Transaction");
      JButton manage = new JButton("Manage Products");        
      JButton trans2 = new JButton("Look up Item");           
      JButton back = new JButton("Back");
      JButton back2 = new JButton("Back");
      JButton back3 = new JButton("Back");
      JButton pay = new JButton("Finish and Pay");
      JButton addPdt = new JButton("Add product");
      JButton updatePdt = new JButton("Update product");
      JButton loadPdt = new JButton("Load product");
      JButton report = new JButton("View Business Report");
      JButton clear = new JButton("Clear");
      JButton cash = new JButton("Cash");
      JButton card = new JButton("Card");
      JButton finish1 = new JButton("Pay");
      JButton finish2 = new JButton("Pay");
      JButton ok = new JButton("OK");
      
      
      //declare textfield for product information
      JTextField name = new JTextField();      
      JTextField ID = new JTextField();      
      JTextField dateE = new JTextField();
      JTextField company = new JTextField();
      JTextField price = new JTextField();
      JTextField quantity = new JTextField();
      JTextField password = new JTextField("", 20);
      
      //declare labels 
      JLabel managerLabel = new JLabel("Account type: Manager");
      JLabel cashierLabel = new JLabel("Account type: Cashier");
      JLabel transLabel = new JLabel("Cashier Transaction Menu");
      JLabel paymentLabel = new JLabel("Cashier Payment Option Menu");
      JLabel picLabel = new JLabel();
      JLabel nameLabel = new JLabel("Name: ");
      JLabel companyLabel = new JLabel("Seller information: ");
      JLabel idLabel = new JLabel("ID Number: ");
      JLabel expLabel = new JLabel("Expiration Date (YYYYMMDD): ");
      JLabel priceLabel = new JLabel("Price: $");
      JLabel quantityLabel = new JLabel("Quantity: ");      
      JLabel passwordEntry = new JLabel("Password: ");
      JLabel welcome = new JLabel("Please log in");
      JLabel purchaseLabel = new JLabel("Purchase History");
      JLabel purchaseHistory = new JLabel("");
      JLabel receiptLabel = new JLabel("Receipt");
      JLabel receiptText = new JLabel("");
      
      
      public void setFrame(JFrame newFrame) {
          frame = newFrame;
      }     
      
      public JFrame getFrame() {
          return frame;
      }
      
      public JButton getLoginButton() {
          return login;
      }
      
      public JButton getLogoutButton() {
          return logout;
      }
      
      public JTextField getPasswordField() {
          return password;
      }
      
      public JButton getChangePasswordButton() {
          return changePassword;
      }
      
      public JButton getChangeProfilePicButton() {
          return changeProfilePic;
      }
      
      public JLabel getPicLabel() {
          return picLabel;
      }
      
      public JButton getChangeProfileButton() {
          return changeProfilePic;
      }
      
       public JButton getManageButton() {
         return manage;
     }
     
     public JButton getBackButton() {
         return back;
     }
     
     public JButton getBack2Button() {
         return back2;
     }
     
     public JButton getAddPdtButton() {
         return addPdt;
     }
     
     public JButton getLoadPdtButton() {
         return loadPdt;
     }
     
     public JButton getUpdatePdtButton() {
         return updatePdt;
     }
     
     public JButton getClearButton() {
         return clear;
     }
     
     public JButton getCreateAccountButton() {
         return createAccount;
     }
     
     public JButton getTransactionButton() {
         return transaction;
     }
     
     public JButton getLookupButton() {
         return trans2;
     }
     
     public JButton getPayButton() {
         return pay;
     }
     
     public JButton getCashButton() {
         return cash;
     }
     
     public JButton getCardButton() {
         return card;
     }
     
     public JButton getBackToPaymentButton() {
         return back3;
     }
     
     public JButton getPayCashButton() {
         return finish1;
     }
     
     public JButton getPayCardButton() {
         return finish2;
     }
     public JButton getOkButton() {
         return ok;
     }
     
     public JButton getReportButton() {
         return report;
     }
     
     public String getProductName() {
         return name.getText();
     }
     
     public String getCompany() {
         return company.getText();
     }
     
     public int getID() {
         String idText = ID.getText();
         int id = Integer.parseInt(idText);
         return id;
     }
     
     public int getExpDate() {
         String expText = dateE.getText();
         int exp = Integer.parseInt(expText);
         return exp;
     }
     
     public double getPrice() {
         String priceText = price.getText();
         double priceIn = Double.parseDouble(priceText);
         return priceIn;
     }
     
     public int getQuantity() {
         String quantityText = quantity.getText();
         int quantityIn = Integer.parseInt(quantityText);
         return quantityIn;
     }
      
      // add loginMenu to show
      public void addLoginMenu() {
        clearFrame();
        welcomePanel.add(welcome);
        loginMenu.add(passwordEntry);
        loginMenu.add(password);
        loginLast.add(login);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));        
        loginBorder.add(welcomePanel, BorderLayout.NORTH);
        loginBorder.add(loginMenu, BorderLayout.CENTER);
        loginBorder.add(loginLast, BorderLayout.SOUTH);
        frame.getContentPane().add(loginBorder);
        repaintFrame();
        frame.pack();       
      }
      
      //add managerMenu to show
      public void addManagerMenu() {
        clearFrame();       
        managerTitle.add(managerLabel);
        managerPic.add(picLabel);
        managerFunction.add(changeProfilePic);
        managerFunction.add(changePassword);
        managerFunction.add(createAccount);
        managerFunction.add(report);
        managerFunction.add(manage);
        managerFunction.add(logout);
        managerMenu.add(managerTitle, BorderLayout.NORTH);
        managerMenu.add(managerPic, BorderLayout.CENTER);
        managerMenu.add(managerFunction, BorderLayout.SOUTH);
        
        frame.getContentPane().add(managerMenu);
        repaintFrame();
        frame.pack();        
      }
      
      //adds cashier's menu to show
      public void addCashierMenu() {
          clearFrame();
          cashierTitle.add(cashierLabel);
          cashierPic.add(picLabel);
          cashierFunction.add(changeProfilePic);
          cashierFunction.add(changePassword);
          cashierFunction.add(transaction);
          cashierFunction.add(logout);
          cashierMenu.add(cashierTitle, BorderLayout.NORTH);
          cashierMenu.add(cashierPic, BorderLayout.CENTER);
          cashierMenu.add(cashierFunction, BorderLayout.SOUTH);
          
          frame.getContentPane().add(cashierMenu);
          repaintFrame();
          frame.pack();
      }
      
      //adds transaction menu to show
      public void addTransMenu() {
          clearFrame();
          trans.add(transLabel);
          trans.add(trans2);
          trans.add(pay);
          backPanel.add(back2);
          transMenu.add(trans, BorderLayout.CENTER);
          transMenu.add(backPanel, BorderLayout.SOUTH);
          frame.getContentPane().add(transMenu);
          repaintFrame();
          frame.pack();
      }
      
      //add payment menu to show
      public void addPaymentMenu() {
          clearFrame();
          payment.add(paymentLabel);
          payment.add(cash);
          payment.add(card);
          frame.getContentPane().add(payment);
          repaintFrame();
          frame.pack();
      }
      
      public void clearFrame() {
          frame.getContentPane().removeAll();
      }
      
      public void repaintFrame() {
          frame.validate();
          frame.repaint();
      }
      
      // modifies the profile picture for manager
      public void modifyAcctPicMan(String url) {         
          ImageIcon image = new ImageIcon(url);
          Image newImage = image.getImage();
          Image resized = newImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
          image = new ImageIcon(resized);
          picLabel.setIcon(image);      
          addManagerMenu();
      }
      
      //modifies the profile picture for cashier
      public void modifyAcctPicCashier(String url) {
          ImageIcon image = new ImageIcon(url);
          Image newImage = image.getImage();
          Image resized = newImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
          image = new ImageIcon(resized);
          picLabel.setIcon(image);   
          addCashierMenu();
      }
      
      
      
      //sets product management menu panel to show
     public void addProductManagementMenu() {
         clearFrame();
         pdtEntry.add(nameLabel);
         pdtEntry.add(name);
         pdtEntry.add(companyLabel);
         pdtEntry.add(company);
         pdtEntry.add(idLabel);
         pdtEntry.add(ID);
         pdtEntry.add(expLabel);
         pdtEntry.add(dateE);
         pdtEntry.add(priceLabel);
         pdtEntry.add(price);
         pdtEntry.add(quantityLabel);
         pdtEntry.add(quantity);
         pdtLast.add(addPdt);
         pdtLast.add(updatePdt);
         pdtLast.add(loadPdt);
         pdtLast.add(clear);
         pdtLast.add(back);
         productMan.add(pdtEntry, BorderLayout.CENTER);
         productMan.add(pdtLast, BorderLayout.SOUTH);
         frame.getContentPane().add(productMan);         
         repaintFrame();
         frame.pack();
     }
     
     public void addPurchaseHistoryCash() {
         clearFrame();         
         purchaseHistory.setText("");
         purchaseHistory.setText("<html>" + purchaseText + "</html>");
         cashMenu.add(purchaseLabel);
         cashMenu.add(purchaseHistory);
         cashMenuButtons.add(finish1);
         cashMenuButtons.add(back3);
         cashMenuBorder.add(cashMenu, BorderLayout.CENTER);
         cashMenuBorder.add(cashMenuButtons, BorderLayout.SOUTH);
         frame.getContentPane().add(cashMenuBorder);         
         repaintFrame();
         frame.pack();
     }
     
     public void addPurchaseHistoryCard() {
         clearFrame();
         purchaseHistory.setText("");
         purchaseHistory.setText("<html>" + purchaseText + "</html>");
         cardMenu.add(purchaseLabel);
         cardMenu.add(purchaseHistory);
         cardMenuButtons.add(finish2);
         cardMenuButtons.add(back3);
         cardMenuBorder.add(cardMenu, BorderLayout.CENTER);
         cardMenuBorder.add(cardMenuButtons, BorderLayout.SOUTH);
         frame.getContentPane().add(cardMenuBorder);
         repaintFrame();
         frame.pack();
     }
     
     public void addReceiptPage(String str) {
         clearFrame();
         receiptText.setText("");
         receiptText.setText(str);
         receiptBorder.add(receiptLabel, BorderLayout.NORTH);
         receiptBorder.add(receiptText, BorderLayout.CENTER);
         receiptBorder.add(ok, BorderLayout.SOUTH);
         frame.getContentPane().add(receiptBorder);
         repaintFrame();
         frame.pack();         
     }     
     
     //loads a product info to show
     public void loadProduct(String nameIn, int idIn, int datEIn, String companyIn,
             double priceIn, int quantityIn) {
         name.setText(nameIn);
         ID.setText(Integer.toString(idIn));
         dateE.setText(Integer.toString(datEIn));
         company.setText(companyIn);
         price.setText(Double.toString(priceIn));
         quantity.setText(Integer.toString(quantityIn)); 
     }
     
     public void clear() {
         name.setText("");
         ID.setText("");
         dateE.setText("");
         company.setText("");
         price.setText("");
         quantity.setText("");
     }
     
     public void setPurchaseText(String str) {
         purchaseText = str;
     }
     
     public String getPurchaseText() {
         return purchaseText;
     }
     
     public void exit() {
         System.exit(0);
     }
     
    
      
      
}
