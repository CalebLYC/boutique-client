/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tg.univlome.epl.boutique.client.gui.sections.HeaderPanel;
import tg.univlome.epl.boutique.client.gui.sections.ProduitsTable;
import tg.univlome.epl.boutique.client.utils.implementations.ProduitClient;

/**
 *
 * @author Caleb Lyc
 */
public class ProduitsScreen extends JFrame {
    private static JPanel headerPanel;
    private static JPanel mainPanel;
    
    public ProduitsScreen(){
        this.setTitle("Produits");
        this.setSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
        
        try{
            headerPanel = new HeaderPanel(this, "Produits", AddProduitPage.class);
            mainPanel = new ProduitsTable(this);
        }catch(Exception e){
            
        }
       
        this.getContentPane().add(headerPanel, BorderLayout.NORTH);
        this.getContentPane().add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
