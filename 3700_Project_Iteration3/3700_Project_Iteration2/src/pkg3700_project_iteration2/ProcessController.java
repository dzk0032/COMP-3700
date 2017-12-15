/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project_iteration2;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 *
 * @author Daehyun
 */
public class ProcessController extends JFrame {

    private Process model;
    private ProcessViewer viewer;
    private Connection connection;
    String imageUrl = "";
    JFrame frame;
    
    public ProcessController(Process newModel, ProcessViewer newViewer) {
        model = newModel;
        viewer = newViewer;        
    }
    
    public void setFrame() {
        frame = viewer.getFrame();
    }
    
    public void setConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PRODUCT.db");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("SQLite not installed!");
        }
        catch (SQLException e ) {
            System.out.println("SQLite database not ready!");
        }
        
    }   
    
    //creates the login menu to show
    public void addLoginMenu() {
        viewer.addLoginMenu();        
    }
    
    // add action for logging in
    // If the password is invalid, sets password, userid, usertype of the model to
    //-1 and sets the loginsuccess to false
    // Else, sets the user's password, usertype, and userid to the appropriate
    //values and sets the login success to true
    // according to the data from the database
    public void login() {
        viewer.getLoginButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String pwd = viewer.getPasswordField().getText();
                    int pwdInt = -1;
                    
                    if (pwd != null) {
                        try {
                            pwdInt = Integer.parseInt(pwd);
                        }
                        catch(NumberFormatException n) {
                            JOptionPane.showMessageDialog(null, "Invalid password type! Must be integer!");
                        }                        
                    }                   
                    
                    String query = "SELECT * FROM USER WHERE Password = " + pwd;                    
                    try {
                       Statement statement = connection.createStatement();
                       ResultSet resultSet = statement.executeQuery(query);
                       
                       if (resultSet.next() == true) {
                           model.setPassword(pwdInt);
                           model.setUserID(resultSet.getInt(1));
                           model.setUserType(resultSet.getInt(2));
                           model.setLoginSuccess(true);                           
                           userMenu();
                       }
                       else {
                           JOptionPane.showMessageDialog(null, "Invalid Password");
                           model.setPassword(-1);
                           model.setUserID(-1);
                           model.setUserType(-1);
                           model.setLoginSuccess(false);
                       }
                    }
                    catch (SQLException x) {
                        System.out.println("SQLException occured!");
                    }                    
                }
            });
    }
    
    //checks if a user's picture exists
    public boolean picExists() {
        try {
            String query = "SELECT * FROM PICTURE WHERE UserID = " + model.getUserID();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("Database Error in accessing checkPic()");
        }
        return false;
    }   
    
    public void updatePhoto() {
        if (picExists() == false) {
            
        }
    }
    
    //show the user's home menu depending on what type of user it is
    public void userMenu() {
        
        loadPic();
        if (model.getUserType() == 1) {
            viewer.addManagerMenu();
            manageProducts();
            backToManMenu();
            addProduct();
            loadProduct();
            updateProduct();
            clearFields();
            createAccount();
            viewReport();
        }
        else {
            viewer.addCashierMenu();
            transactionMenu();
            backToCashierMenu();
            lookupItem();
            transaction();
            cashPaymentOptions();
            cardPaymentOptions();
            backToTrans();
            payWithCash();
            payWithCard();
            finish();
        }
        addChangePic();
        changePassword();
        logout();
        
    }
    
    // adds a picture for a user account
    public void addPic() {
        
        String sql = "INSERT INTO PICTURE VALUES (?, ?)";
        try {                
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            File file = new File(imageUrl);
            try {
                
                FileInputStream inputImg = new FileInputStream(file);
                stmt.setInt(1, model.getUserID());
                stmt.setBinaryStream(2, inputImg, (int)(file.length()));
                stmt.execute();
                
                stmt.close();
                System.out.println("ok");               
            }
            catch(FileNotFoundException f) {
                System.out.println("No such file");
            }
        }
        catch (SQLException e) {
            System.out.println("Database error");
        }
        
        if (model.getUserType() == 1) {
            viewer.modifyAcctPicMan(imageUrl);  
        }
        else {
            viewer.modifyAcctPicCashier(imageUrl);
        }
        
              
    }
    
    //loads a picture for a user account
    public void loadPic() {
        
        String query = "SELECT * FROM PICTURE WHERE UserID = " + model.getUserID();
        
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            
            if (resultSet.next()) {
                
                byte[] fileBytes = resultSet.getBytes(2);
                ImageIcon img = new ImageIcon(fileBytes);
                Image newImage = img.getImage();
                Image resized = newImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                img = new ImageIcon(resized);
                //Set img
                viewer.getPicLabel().setIcon(img);                
            }
            else {
                viewer.getPicLabel().setIcon(null);
            }
            resultSet.close();
            stmt.close();
        }
        catch (SQLException e) {
            
        }
        
    }
    
    //updates a user's picture 
    public void updatePic() {
        if (picExists()) {
            try {
                String query = "UPDATE PICTURE SET AcctPhoto = ? WHERE UserID = ?";
                        
                PreparedStatement statement = connection.prepareStatement(query);
                
                File file = new File(imageUrl);
                
                try {
                    FileInputStream inputImg = new FileInputStream(file);
                    statement.setBinaryStream(1, inputImg, (int)(file.length()));
                    statement.setInt(2, model.getUserID());
                    statement.execute();
                    statement.close();
                    
                }
                catch(FileNotFoundException f) {
                    System.out.println("No such file");
                }
                
            }
            catch (SQLException e) {
                System.out.println("Database error");
            }
            
            if(model.getUserType() == 1) {
                viewer.modifyAcctPicMan(imageUrl);
            }
            else {
                viewer.modifyAcctPicCashier(imageUrl);
            }
            
            
        }
    }
    
    //sends the picture data to the viewer for display
    public void addChangePic() {
        viewer.getChangeProfileButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    imageUrl = JOptionPane.showInputDialog(null, "Enter the directory for the image");
                                        
                    if (imageUrl != null) {
                        if (!picExists()) {
                            addPic();
                        }
                        else {
                            updatePic();
                        }
                    }
                    
                }
            });
    }
    
    //add action for clicking on managin products buttton
    public void manageProducts() {
        viewer.getManageButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.addProductManagementMenu();
                }
            });
    }
    
    //add action for clicking on back button for the manager
    public void backToManMenu() {
        viewer.getBackButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.addManagerMenu();
                }
            });
    }
    
    //check if product exists
    public boolean productExists(int id) {
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
    
    //add action for manager's add product button
    public void addProduct() {
        viewer.getAddPdtButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    model.setProductName(viewer.getProductName());
                    model.setProductSellerInfo(viewer.getCompany());
                    model.setProductId(viewer.getID());
                    model.setProductQuantity(viewer.getQuantity());
                    model.setProductExpDate(viewer.getExpDate());
                    model.setProductPrice(viewer.getPrice());
                    
                    try {
                        if (!productExists(viewer.getID())) {
                            String query = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?, ?, ?)"; 
                            PreparedStatement statement = connection.prepareStatement(query);
                            statement.setInt(1, model.getProductId());
                            statement.setString(2, model.getProductName());
                            statement.setInt(3, model.getProductQuantity());
                            statement.setDouble(4, model.getProductPrice());
                            statement.setInt(5, model.getProductExpDate());
                            statement.setString(6, model.getProductSellerInfo());
                            statement.execute();
                            statement.close();
                            JOptionPane.showMessageDialog(null, "Product added");
                        }                        
                    }
                    catch (SQLException s) {
                        System.out.println("Database error");
                    }                    
                }
        });
    }
    
    //add action for manager's load product button
    public void loadProduct() {
        viewer.getLoadPdtButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String idText = JOptionPane.showInputDialog(null, "Enter Product ID");
                    if (idText != null) {
                        int id = Integer.parseInt(idText);
                        
                        if (productExists(id)) {
                            String query = "SELECT * FROM PRODUCT WHERE ID = " + id;
                            
                            try {
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(query);
                                
                                model.setProductId(resultSet.getInt(1));
                                model.setProductName(resultSet.getString(2));
                                model.setProductQuantity(resultSet.getInt(3));
                                model.setProductPrice(resultSet.getDouble(4));
                                model.setProductExpDate(resultSet.getInt(5));
                                model.setProductSellerInfo(resultSet.getString(6));
                                
                                resultSet.close();
                                statement.close();
                            }
                            catch(SQLException s) {
                                System.out.println("Database error");
                            }
                            viewer.loadProduct(model.getProductName(), model.getProductId(),
                                    model.getProductExpDate(), model.getProductSellerInfo(),
                                    model.getProductPrice(), model.getProductQuantity());
                        }
                    }
                }
            });
    }
    
    //add action for manager's update product button
    public void updateProduct() {
        viewer.getUpdatePdtButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (productExists(viewer.getID())) {
                        String name = viewer.getProductName();
                        String company = viewer.getCompany();
                        int id = viewer.getID();
                        int exp = viewer.getExpDate();
                        double price = viewer.getPrice();
                        int quantity = viewer.getQuantity();
                        
                        try {
                            String query = "UPDATE PRODUCT SET Name = ?, "
                                    + "Price = ?, Quantity = ?, ExpirationDate = ?, "
                                    + "SellerInfo = ? WHERE ID = ?";
                            PreparedStatement statement = connection.prepareStatement(query);
                            statement.setString(1, name);
                            statement.setDouble(2, price);
                            statement.setInt(3, quantity);
                            statement.setInt(4, exp);
                            statement.setString(5, company);
                            statement.setInt(6, id);
                            statement.execute();
                            statement.close();
                        }
                        catch(SQLException s) {
                            System.out.println("Database error");
                        }
                        JOptionPane.showMessageDialog(null, "Product Updated");
                    }
                }
            });
    }
    
    //add action manager's clear button
    public void clearFields() {
        viewer.getClearButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.clear();
                }
            });
    }
    
    //check if password exists
    public boolean passwordExists(int num) {
        try {
            String query = "SELECT * FROM USER WHERE Password = " + num;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            if (resultSet.next()) {
                return true;
            }
        }
        catch(SQLException e) {
            System.out.println("Database error");
        }
        
        return false;
    }
    
    //add action for manager's create account button
    public void createAccount() {
        viewer.getCreateAccountButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {                    
                    
                    String in = JOptionPane.showInputDialog(null, "Enter the user type: "
                                + "(0 for cashier, 1 for manager)");
                    
                    if (in != null) {
                        
                        String query = "SELECT COUNT(*) AS total FROM USER";
                        String insert = "INSERT INTO USER VALUES (?, ?, ?)";
                        int totalUsers = -1;
                    
                        try {
                            Statement statement = connection.createStatement();
                            ResultSet resultSet = statement.executeQuery(query);
                            totalUsers = resultSet.getInt("total");
                            totalUsers++;                        
                            statement.close();
                            System.out.println(totalUsers);
                        
                        
                            PreparedStatement stat = connection.prepareStatement(insert);
                            stat.setInt(1, totalUsers);
                        
                            stat.setInt(2, Integer.parseInt(in));
                        
                            String password = "";
                            int pass = -1;
                        
                                            
                            for (int i = 0; i < 6; i++) {                                                      
                                int rand = (int)(Math.random() * 10);
                                String temp = Integer.toString(rand);
                                password += temp;                            
                            
                                if ((i == 5) && (passwordExists(pass))) {
                                    i = 0;
                                    password = "";
                                }
                            }
                        
                            pass = Integer.parseInt(password);
                        
                        
                            stat.setInt(3, pass);
                            stat.execute();
                            stat.close();
                        
                            if (Integer.parseInt(in) == 0) {
                                JOptionPane.showMessageDialog(null, "Cashier account"
                                    + " created with default password: " + password);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Manager account"
                                    + " created with default password: " + password);
                            }
                        }
                        catch(SQLException s) {
                            System.out.println("Database Error");
                            s.printStackTrace();
                        }                       
                    }
                    
                }
            });
    }
    
    //add action for changing password button
    public void changePassword() {
        viewer.getChangePasswordButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(model.getUserID());
                    String str = JOptionPane.showInputDialog(null, "Enter "
                                    + "new password (must consist of numbers only):");
                    if (str != null) {
                        try {
                            int changed = -1;
                            String query = "SELECT * FROM USER WHERE ID = " + model.getUserID();
                            PreparedStatement statement = connection.prepareStatement(query);
                            ResultSet resultSet = statement.executeQuery();
                        
                            if (resultSet.next()) {                            
                                int id = resultSet.getInt(1);
                                int type = resultSet.getInt(2);
                                String update = "UPDATE USER SET UserType = ?, Password = ? "
                                    + "WHERE ID = ?";
                                statement = connection.prepareStatement(update);
                                statement.setInt(1, type);                           
                            
                            
                                
                                changed = Integer.parseInt(str);
                            
                                while (passwordExists(changed)) {
                                    str = JOptionPane.showInputDialog(null, "password already exists."
                                        + "Enter another password");
                                    changed = Integer.parseInt(str);
                                }
                                statement.setInt(2, changed);                                
                                statement.setInt(3, id);                           
                            
                            }                  
                            statement.execute();
                            resultSet.close();
                            statement.close();                        
                        
                        }
                        catch(SQLException s) {
                            System.out.println("Database error");
                            s.printStackTrace();
                        }                        
                    }                    
                }
            });        
    }
    
    public void viewReport() {
        viewer.getReportButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int id;
                    double itemTotal = 0;
                    double total = 0;
                    String query = "SELECT * FROM PURCHASE";
                    String query2 = "SELECT * FROM PURCHASE WHERE ID = ";
                    String spaces = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    String report = "<p>Item ID" + spaces + "Name"
                            + spaces+ "Quantity sold" + spaces + "Item Total Price<br/><br/>";
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        
                        while (resultSet.next()) {
                            id = resultSet.getInt(2);
                            
                            Statement statement2 = connection.createStatement();
                            ResultSet rs = statement2.executeQuery(query2 + id);
                            
                            while (rs.next()) {
                                itemTotal += rs.getDouble(5) * rs.getInt(4);
                            }
                            
                            report += resultSet.getInt(2) + spaces + spaces + spaces + resultSet.getString(3) 
                                    + spaces + spaces + spaces + resultSet.getInt(4) + spaces +
                                    spaces + spaces + spaces + spaces + spaces + "$" + itemTotal + "<br/>";
                            total += itemTotal;
                            itemTotal = 0;
                            rs.close();
                            statement2.close();
                        }
                        resultSet.close();
                        statement.close();
                    }
                    catch(SQLException s) {
                        
                    }
                    report += "<br/>Total: " + spaces + "$" + total + "<p/>";
                    JOptionPane.showMessageDialog(null, "<html>" + report + "<html/>");
                }
            });
    }
    
    // add action for transaction button to show transaction menu
    public void transactionMenu() {
        viewer.getTransactionButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.addTransMenu();
                }
            });
    }
    
    public void backToCashierMenu() {
        viewer.getBack2Button().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.addCashierMenu();
                }
            });
    }
    
    public void lookupItem() {
        viewer.getLookupButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String idText = JOptionPane.showInputDialog(null, "Enter product ID: ");
                    int id = -1;
                    if (idText != null) {
                        id = Integer.parseInt(idText);                        
                        if (productExists(id)) {
                            try {
                                String q = "SELECT * FROM PRODUCT WHERE ID = " + id;
                                Statement statement = connection.createStatement();
                                ResultSet resultSet = statement.executeQuery(q);
                                
                                model.setProductId(resultSet.getInt(1));
                                model.setProductName(resultSet.getString(2));
                                model.setProductQuantity(resultSet.getInt(3));
                                model.setProductPrice(resultSet.getDouble(4));
                                model.setProductExpDate(resultSet.getInt(5));
                                model.setProductSellerInfo(resultSet.getString(6));                                
                                resultSet.close();
                                statement.close();
                            }
                            catch (SQLException s) {
                                System.out.println("Database Error");
                                s.printStackTrace();
                            }
                            
                            try {
                                int quantity = 0;
                                String query = "SELECT * FROM PURCHASE WHERE ID = " + id;
                                Statement check = connection.createStatement();
                                ResultSet resultSet = check.executeQuery(query);
                                
                                PreparedStatement statement = connection.prepareStatement(""
                                        + "INSERT INTO PURCHASE VALUES (?, ?, ?, ?, ?)");
                                
                                if (!resultSet.next()) {
                                    quantity = 1;
                                    statement.setInt(1, model.getUserID());
                                    statement.setInt(2, model.getProductId());
                                    statement.setString(3, model.getProductName());
                                    statement.setInt(4, quantity);
                                    statement.setDouble(5, model.getProductPrice());                                    
                                }
                                else {
                                    quantity = resultSet.getInt(4);
                                    quantity++;
                                    statement = connection.prepareStatement("UPDATE PURCHASE SET"
                                            + " Quantity = ? WHERE ID = " + id);
                                    statement.setInt(1, quantity);
                                }
                                PreparedStatement statement2 = connection.prepareStatement("UPDATE"
                                        + " PRODUCT SET Quantity = ? WHERE ID = " + id);
                                int quantityOriginal = model.getProductQuantity();
                                quantityOriginal--;
                                statement2.setInt(1, quantityOriginal);
                                
                                resultSet.close();
                                check.close();
                                statement.execute();
                                statement.close();
                                statement2.execute();
                                statement2.close();
                                
                            }
                            catch (SQLException s2) {
                                System.out.println("Database access error!");
                                s2.printStackTrace();
                            }
                        }
                    }
                }
            });
    }
    
    //log the user out
    public void logout () {
        viewer.getLogoutButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {                  
                    viewer.exit();                    
                }
            });
    }

    public void transaction() {
        viewer.getPayButton().addActionListener( 
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewer.addPaymentMenu();
                }
            
            });
    }
    
    public void backToTrans() {
        viewer.getBackToPaymentButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String str = "";
                    viewer.setPurchaseText(str);
                    viewer.addPaymentMenu();
                }
            });
    }
    
    public void cashPaymentOptions() {
        viewer.getCashButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {                      
                        String name = "";
                        String spaces = "&nbsp;&nbsp;&nbsp;";
                        double price = 0;
                        String priceText = "";
                        int quantity = 0;
                        String quantityText = "";
                        String totalText = "";
                        double total = 0;
                        String history = "<p>name" + spaces + "quantity" + spaces
                               + "price<br/><br/>";
                        String query = "SELECT * FROM PURCHASE WHERE UserID = " + model.getUserID();
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        
                        while (resultSet.next()) {
                            name = resultSet.getString(3);
                            quantity = resultSet.getInt(4);
                            quantityText = Integer.toString(quantity);
                            price = resultSet.getDouble(5);
                            price *= quantity;
                            total += price;
                            totalText = Double.toString(total);
                            priceText = Double.toString(price);
                            history += name + spaces + spaces + spaces + quantityText + spaces +
                               spaces + spaces + "$" + price + "<br/>"; 
                            
                        }
                        history += "<br/>Total:" + spaces + "$" + totalText;
                        history += "</p>";
                        viewer.setPurchaseText(history);
                
                    }
            
                    catch (SQLException s) {
                        System.out.println("Database error");
                        
                    }
                    
                viewer.addPurchaseHistoryCash();
                }
            });
    }
    
    public void cardPaymentOptions() {
        viewer.getCardButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {                      
                        String name = "";
                        String spaces = "&nbsp;&nbsp;&nbsp;";
                        double price = 0;
                        String priceText = "";
                        int quantity = 0;
                        String quantityText = "";
                        String totalText = "";
                        double total = 0;
                        String history = "<p>name" + spaces + "quantity" + spaces
                               + "price<br/><br/>";
                        String query = "SELECT * FROM PURCHASE WHERE UserID = " + model.getUserID();
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        
                        while (resultSet.next()) {
                            name = resultSet.getString(3);
                            quantity = resultSet.getInt(4);
                            quantityText = Integer.toString(quantity);
                            price = resultSet.getDouble(5);
                            price *= quantity;
                            total += price;
                            totalText = Double.toString(total);
                            priceText = Double.toString(price);
                            history += name + spaces + spaces + spaces + quantityText + spaces +
                               spaces + spaces + "$" + price + "<br/>"; 
                            
                        }
                        history += "<br/>Total:" + spaces + "$" + totalText;
                        history += "</p>";
                        viewer.setPurchaseText(history);
                
                    }
            
                    catch (SQLException s) {
                        System.out.println("Database error");
                        
                    }
                    
                    viewer.addPurchaseHistoryCard();
                }
            });
    }
    
    //returns the double for total amount for the  user's total purchase
    public double getTotal() {
        double total = 0;
        try {               
            double price = 0;                
            int quantity = 0;               
            total = 0;
            String query = "SELECT * FROM PURCHASE WHERE UserID = " + model.getUserID();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
                        
            while (resultSet.next()) {
                            
                quantity = resultSet.getInt(4);                            
                price = resultSet.getDouble(5);
                price *= quantity;
                total += price;                                                    
            }                        
        }
        catch (SQLException e) {
            System.out.println("Database erro");
            e.printStackTrace();
        }
        return total;
    }
    
    public void payWithCash() {
        viewer.getPayCashButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String str = JOptionPane.showInputDialog(null, "Enter cash amount: ");
                    double amount = 0;
                    double total = getTotal();
                    double change = 0;
                    String receipt = viewer.getPurchaseText();
                    if (str != null) {
                        try {
                            amount = Double.parseDouble(str);
                            
                            if (amount < getTotal()) {
                                while (amount < getTotal()) {
                                    str = JOptionPane.showInputDialog(null, "Not enough amount to complete"
                                        + " transaction. Please enter larger amount");
                                    amount = Double.parseDouble(str);
                                }
                            }
                            else {
                                change = amount - total;
                                receipt += "<p>Paid with Cash<br/><br/>Cash amount: $" +
                                        amount + "<br/>Change: $" + change;
                                viewer.addReceiptPage("<html>" + receipt + "<p/><html/>");
                            }
                        }
                        catch(NumberFormatException n) {
                            System.out.println("Not a number!");
                        }                        
                    }                    
                }
            });        
    }
    
    public void payWithCard() {
        viewer.getPayCardButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String receipt = viewer.getPurchaseText();
                    receipt += "<p><br/>Paid with Card<br/><p/>";
                    viewer.addReceiptPage("<html>" + receipt + "<html/>");
                    
                }
            });
    }
    
    //end program after viewing the receipt
    public void finish() {
        viewer.getOkButton().addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
    } 
    
}
