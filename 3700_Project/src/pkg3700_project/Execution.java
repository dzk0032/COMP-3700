/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3700_project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author sky45611
 */
public class Execution extends JFrame{
   
   public static void main(String[] args) {
      Connector connector = new Connector();
      connector.connect();
      Adapter adapter = new Adapter(connector.getConnection());     
      
      //declare Frame
      JFrame frame = new JFrame();
      frame.setForeground(Color.WHITE);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(800, 800));
      frame.setTitle("Store Operations");
      
      //declare Panels for use
      JPanel mainMenu = new JPanel(new GridLayout(2, 3));     
      JPanel main = new JPanel(new GridLayout(1,1)); 
      JPanel trans = new JPanel(new GridLayout(2, 3));       
      JPanel pdtEntry = new JPanel(new GridLayout(6, 6));      
      JPanel pdtLast = new JPanel(new GridLayout(1, 1));
      JPanel payment = new JPanel(new GridLayout(2, 3));
      JPanel cashCardPayment = new JPanel(new GridLayout(2, 3));      
      JPanel previous = new JPanel(new GridLayout(1, 1));
      JPanel cashButtons = new JPanel(new GridLayout(1, 1));
      JPanel cardButtons = new JPanel(new GridLayout(1, 1));
      
      //declare buttons for use
      JButton transaction = new JButton("Transaction");
      JButton manage = new JButton("Manage Products");        
      JButton trans2 = new JButton("Look up Item");           
      JButton back1 = new JButton("Back to Main Menu");    
      JButton back2 = new JButton("Back");
      JButton back3 = new JButton("Back");
      JButton pay = new JButton("Finish and Pay");
      JButton addPdt = new JButton("Add product");
      JButton updatePdt = new JButton("Update product");
      JButton loadPdt = new JButton("Load product");
      JButton clear = new JButton("Clear");
      JButton cash = new JButton("Cash");
      JButton card = new JButton("Card");
      JButton finish = new JButton("Pay");
      JButton finish2 = new JButton("Pay");
      
      //declare textfield for product information
      JTextField name = new JTextField();      
      JTextField ID = new JTextField();      
      JTextField dateE = new JTextField();
      JTextField company = new JTextField();
      JTextField price = new JTextField();
      JTextField quantity = new JTextField();
      
      //declare labels 
      JLabel nameLabel = new JLabel("Name: ");
      JLabel companyLabel = new JLabel("Seller information: ");
      JLabel idLabel = new JLabel("ID Number: ");
      JLabel expLabel = new JLabel("Expiration Date (YYYYMMDD): ");
      JLabel priceLabel = new JLabel("Price: $");
      JLabel quantityLabel = new JLabel("Quantity: ");
      JLabel paymentHistory = new JLabel("");
      
      
      
      frame.setLayout(new BorderLayout());        
      mainMenu.add(transaction);
      mainMenu.add(manage);
      frame.add(mainMenu, BorderLayout.CENTER);
      frame.setLocation(new Point(600, 150));        
      frame.setVisible(true);
      
      //add action for Transaction button
      transaction.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.getContentPane().removeAll();              
               trans.add(trans2);     
               trans.add(pay);   	
               main.add(back1);
               frame.getContentPane().add(trans, BorderLayout.CENTER);
               frame.getContentPane().add(main, BorderLayout.SOUTH);                		
               frame.validate();
               frame.repaint();
            }
         });
    
    //add action for Manage Products button
      manage.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.getContentPane().removeAll();                
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
                pdtLast.add(back1);                
                frame.getContentPane().add(pdtEntry, BorderLayout.CENTER);                
                frame.getContentPane().add(pdtLast, BorderLayout.SOUTH);                
                frame.validate();
                frame.repaint();
            }
         });
    
     //add action for back button
      back1.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               frame.getContentPane().removeAll();      
               name.setText("");
               ID.setText("");
               dateE.setText("");
               company.setText("");
               price.setText("");
               quantity.setText("");
               frame.getContentPane().add(mainMenu, BorderLayout.CENTER);      		
               frame.validate();
               frame.repaint();
            }
         });
    
    //add action for look up product button
      trans2.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {                               
               
               String idText = JOptionPane.showInputDialog(null, "Enter Product ID: ");               
               if (idText != null) {
                   int id = Integer.parseInt(idText);
                  if(adapter.exists(id) == false) {
                     JOptionPane.showMessageDialog(null, "Invalid Product ID!");
                  }                  
                  else {
                     Product current = new Product();
                     current = adapter.load(id);
                     if (current.getQuantity() != 0) {
                         adapter.purchase(current);
                     }
                     else {
                         JOptionPane.showMessageDialog(null, "No more in stock!");
                     }
                     
                  }           
               }
            }            
         });
      
      
      //add action for Add product button
      addPdt.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String companyText = company.getText();
                String idText = ID.getText();
                int id = Integer.parseInt(idText);
                String expText = dateE.getText();
                int exp = Integer.parseInt(expText);
                String priceText = price.getText();
                double priceIn = Double.parseDouble(priceText);
                String quantityText = quantity.getText();
                int quantityIn = Integer.parseInt(quantityText);
                
                Product product = new Product(nameText, companyText, id, quantityIn,
                    exp, priceIn);                
                boolean status = adapter.add(product);
                
                
                if (status == true) {
                    JOptionPane.showMessageDialog(null, "Product Created");
                }
                if (status == false) {
                    JOptionPane.showMessageDialog(null, "Only one product per id may be created");
                }               
            }
        });    
      
      //add action for Update product
      updatePdt.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String companyText = company.getText();
                String idText = ID.getText();
                int id = Integer.parseInt(idText);
                String expText = dateE.getText();
                int exp = Integer.parseInt(expText);
                String priceText = price.getText();
                double priceIn = Double.parseDouble(priceText);
                String quantityText = quantity.getText();
                int quantityIn = Integer.parseInt(quantityText);
                
                Product product = new Product(nameText, companyText, id, quantityIn,
                    exp, priceIn);                
                boolean status = adapter.update(product);
                
                
                if (status == true) {
                    JOptionPane.showMessageDialog(null, "Product Updated");
                }
                if (status == false) {
                    JOptionPane.showMessageDialog(null, "Product does not exist");
                }               
            }
        });
      
      //add action for load product button
      loadPdt.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idText = JOptionPane.showInputDialog(null, "Enter Product ID: ");
                if (idText != null) {
                    int id = Integer.parseInt(idText);
                    if (adapter.exists(id) == false) {
                        JOptionPane.showMessageDialog(null, "Invalid Product ID!");                        
                    }
                    else {
                        Product current = new Product();
                        current = adapter.load(id);
                        name.setText(current.getName());
                        ID.setText(Integer.toString(current.getId()));
                        dateE.setText(Integer.toString(current.getExpDate()));
                        company.setText(current.getSellerInfo());
                        price.setText(Double.toString(current.getPrice()));
                        quantity.setText(Integer.toString(current.getQuantity()));                        
                    }
                }
            }
        });
      
      //add action for "Clear" button
      clear.addActionListener(
              new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      name.setText("");
                      ID.setText("");
                      dateE.setText("");
                       company.setText("");
                       price.setText("");
                      quantity.setText("");
                  }
              });
      
      //add action for "Finish and Pay" button
      pay.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                payment.add(cash);
                payment.add(card);
                previous.add(back2);
                frame.getContentPane().add(payment, BorderLayout.CENTER);
                frame.getContentPane().add(previous, BorderLayout.SOUTH);
                frame.validate();
                frame.repaint();
            }
        });
      
      //add action for "back" button inside "Finish and Pay"
      back2.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();                               
                frame.getContentPane().add(trans, BorderLayout.CENTER);
                frame.getContentPane().add(main, BorderLayout.SOUTH);
                frame.validate();
                frame.repaint();
            }
        });
      
      //add action for "back" button inside paying with cash
      back3.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                cashCardPayment.removeAll();
                paymentHistory.setText(""); 
                frame.getContentPane().add(payment, BorderLayout.CENTER);
                frame.getContentPane().add(previous, BorderLayout.SOUTH);
                frame.validate();
                frame.repaint();
            }
        });
      
      //add action for "Cash" button
      cash.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {                
                frame.getContentPane().removeAll();                
                paymentHistory.setText(adapter.purchaseList());
                cashCardPayment.add(paymentHistory);
                cashButtons.add(finish);
                cashButtons.add(back3);
                frame.getContentPane().add(cashButtons, BorderLayout.SOUTH);
                frame.getContentPane().add(cashCardPayment, BorderLayout.NORTH);                
                frame.validate();
                frame.repaint();
            }
        });
      
      //add action for "Pay" button for Cash
      finish.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double change = 0;
                String receipt = "";
                String amountText = JOptionPane.showInputDialog(null, "Enter cash amount: ");
                
                if (amountText != null) {
                    double amount = Double.parseDouble(amountText);
                    change = adapter.getChange(amount);
                    
                    if (change < 0) {
                        JOptionPane.showMessageDialog(null, "Not enough money to complete transaction!");                        
                    }
                    else {
                        receipt = "RECEIPT\n\n";
                        receipt += adapter.purchaseList();
                        receipt += "\nCash: $" + amountText;
                        receipt += "\nChange: $" + Double.toString(change);
                        JOptionPane.showMessageDialog(null, receipt);
                        adapter.removePurchaseList();                        
                    }
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(mainMenu, BorderLayout.CENTER);
                    frame.validate();
                    frame.repaint();
                }
                
            }
        });
      
      //add action for "Card" button
      card.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                paymentHistory.setText(adapter.purchaseList());
                cashCardPayment.add(paymentHistory);
                cardButtons.add(finish2);
                cardButtons.add(back3);
                frame.getContentPane().add(cardButtons, BorderLayout.SOUTH);
                frame.getContentPane().add(cashCardPayment, BorderLayout.NORTH);                
                frame.validate();
                frame.repaint();
            }
        });
      
      finish2.addActionListener(
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double balance = 0;
                String receipt = "RECEIPT\n\n";
                receipt += adapter.purchaseList();
                receipt += "\nPaid: $" + Double.toString(Math.abs(adapter.getChange(balance)));                
                receipt += "\n\nPaid With Card";
                JOptionPane.showMessageDialog(null, receipt);
                adapter.removePurchaseList();
                
                frame.getContentPane().removeAll();
                frame.getContentPane().add(mainMenu, BorderLayout.CENTER);
                frame.validate();
                frame.repaint();
            }
        });
      
     
      
         
   }
}
