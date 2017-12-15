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

/**
 *
 * @author Daehyun
 */
public class Execution {
    
    public static void main(String[] args) {
        
        
        Process pmodel = new Process();        
        ProcessViewer pviewer = new ProcessViewer();
        ProcessController pcontrol = new ProcessController(pmodel, pviewer);
        pcontrol.setConnection();        
        
        JFrame frame = new JFrame();
        frame.setForeground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 800));
        frame.setTitle("Store Operations");
        frame.setLayout(new BorderLayout());       
        frame.setLocation(new Point(600, 150));        
        frame.setVisible(true);
        pviewer.setFrame(frame);
        pcontrol.setFrame();
        pviewer.addLoginMenu();       
        pcontrol.login();
        
       
        
        
        
        
    }
    
}
