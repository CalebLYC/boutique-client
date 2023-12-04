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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import tg.univlome.epl.boutique.api.entites.Produit;
import tg.univlome.epl.boutique.client.gui.AddProduitPage;
import tg.univlome.epl.boutique.client.gui.EditProduitScreen;
import tg.univlome.epl.boutique.client.gui.ProduitsScreen;
import tg.univlome.epl.boutique.client.utils.implementations.ProduitClient;

/**
 *
 * @author Caleb Lyc
 */
public class ProduitsTable extends JPanel {
    private JFrame frame;
    private static final ProduitClient produitClient = ProduitClient.getInstance();
    private static List<Produit> produitsList = new ArrayList<>();
     
    
    public ProduitsTable(ProduitsScreen frame){
        this.frame = frame;
        
        this.setPreferredSize(new Dimension(800, 500));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        String[] columnNames = {"Numéro", "Libelle", "Prix unitaire", "Date de péremption", "Categorie"};
        try{
            produitsList = produitClient.lister();
        }catch(Exception e){
            System.out.println("Erreur");
        }
        Object[][] data = new Object[produitsList.size()][5];
        for(int i = 0; i < produitsList.size(); i++){
            Produit produit = produitsList.get(i);
            data[i][0] = (int)produit.getId();
            data[i][1] = produit.getLibelle();
            data[i][2] = produit.getPrixUnitaire();
            data[i][3] = produit.getDatePeremtion();
            data[i][4] = produit.getCaterogie()!=null ? produit.getCaterogie().getLibelle() : "Catégorie";
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        JTable produitsTable = new JTable(model);
        produitsTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(produitsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(800,450));
        
        this.add(scrollPane);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(800, 50));
        buttonsPanel.setLayout(new FlowLayout());
        JButton addButton2 = new JButton("Ajouter");
        addButton2.setBackground(Color.GREEN);
        addButton2.setForeground(Color.WHITE);
        addButton2.setFont(new Font("Arial", Font.BOLD, 14));
        addButton2.addActionListener((ActionEvent e) -> {
            this.frame.dispose();
            new AddProduitPage();
        });
        JButton editButton = new JButton("Modifier");
        editButton.setPreferredSize(new Dimension(80, 25));
        editButton.setBackground(Color.BLUE);
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.addActionListener((ActionEvent e) -> {
            int selectedRow = produitsTable.getSelectedRow();
        if (selectedRow >= 0) {
            Object selectedValue = produitsTable.getValueAt(selectedRow, 0);
            if (selectedValue instanceof Integer) {
                Integer id = (Integer) selectedValue;
                new EditProduitScreen(produitClient.recuperer(id));
                this.frame.dispose();
            } else {
                JOptionPane.showMessageDialog(ProduitsTable.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(ProduitsTable.this, "Veuillez sélectionner un produit.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
        });
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setPreferredSize(new Dimension(80, 25));
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.addActionListener((ActionEvent e) -> {
            int selectedRow = produitsTable.getSelectedRow();
        if (selectedRow >= 0) {
            Object selectedValue = produitsTable.getValueAt(selectedRow, 0);
            if (selectedValue instanceof Integer) {
                Integer id = (Integer) selectedValue;
                produitClient.supprimer(id);
                new ProduitsScreen();
                this.frame.dispose();
                JOptionPane.showMessageDialog(ProduitsTable.this, "Produit supprimée avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(ProduitsTable.this, "Valeur ID non valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(ProduitsTable.this, "Veuillez sélectionner un produit.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
        });
        buttonsPanel.add(addButton2);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        this.add(buttonsPanel);
    }
}
