/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.gui.sections;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tg.univlome.epl.boutique.client.gui.ProduitsScreen;

/**
 *
 * @author Caleb Lyc
 */
public class HeaderPanel extends JPanel {
    private JFrame frame;
    private final String title;
    private Class<JFrame> addScreenClass;
    
    public HeaderPanel(JFrame frame, String title, Class addScreenClass){
        this.frame = frame;
        this.title = title;
        this.addScreenClass = addScreenClass;
        
        this.setPreferredSize(new Dimension(800, 60));
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.add(new JLabel(this.title));
        
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(600,50));
        this.add(spacePanel);
        
        
        JButton addButton = new JButton("Ajouter");
        addButton.setPreferredSize(new Dimension(80, 25));
        addButton.setBackground(Color.GREEN);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.addActionListener((ActionEvent e) -> {
            try {
                this.frame.dispose();
                this.addScreenClass.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.add(addButton);
    }
}
