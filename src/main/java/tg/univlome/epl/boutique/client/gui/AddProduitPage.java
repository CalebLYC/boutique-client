/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import tg.univlome.epl.boutique.api.entites.Categorie;
import tg.univlome.epl.boutique.api.entites.Produit;
import tg.univlome.epl.boutique.client.gui.widgets.SuperFormComponent;
import tg.univlome.epl.boutique.client.gui.widgets.FormComponentBuilder;
import tg.univlome.epl.boutique.client.utils.implementations.CategorieClient;
import tg.univlome.epl.boutique.client.utils.implementations.ProduitClient;

/**
 *
 * @author Caleb Lyc
 */
public class AddProduitPage extends SuperFormComponent {

    private static final ProduitClient produitClient = ProduitClient.getInstance();
    private static final CategorieClient categorieClient = CategorieClient.getInstance();
    private static JButton addButton;
    private static JButton cancelButton;
    private static JTextField libelleField;
    private static JTextField puField;
    private static JSpinner dateField;
    private static JComboBox categorieField;

    public AddProduitPage(){
        this.setTitle("Ajouter un produit");
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        this.addFields();

        addButton = new JButton("Ajouter");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton = new JButton("Annuler");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        this.getContentPane().add(contentPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            //new ProduitsScreen(); //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            if (validateFields()) {
                displayDialog();
                clearFields();
            }
            this.dispose();
        });
        cancelButton.addActionListener((ActionEvent e) -> {
            //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            try{
                this.dispose();
                new ProduitsScreen();
            }catch(Exception ex){
                
            }
        });

        this.setVisible(true);
    }

    @Override
    protected final void addFields() {
        FormComponentBuilder fcb = new FormComponentBuilder(gbc, contentPanel);
        libelleField = fcb.addFormField("Libelle");
        puField = fcb.addFormField("Prix unitaire");
        try {
            dateField = fcb.createDatePicker("Date de péremption");
        } catch (ParseException ex) {
            Logger.getLogger(AddProduitPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Categorie> categories = categorieClient.lister();
        categorieField = fcb.addFormField("Catégorie", new JComboBox(), categories);
    }

    @Override
    protected void displayDialog() {
        try {
            String libelle = libelleField.getText();
            double pu = Float.parseFloat(puField.getText());
            Date dateFromSpinner = (Date) dateField.getValue();
            Instant instant = dateFromSpinner.toInstant();
            LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            Categorie categorie = (Categorie) categorieField.getSelectedItem();
            Produit p = new Produit(libelle, pu, date, categorie);
            produitClient.ajouter(p);
            //JOptionPane.showMessageDialog(this, sb.toString(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            try{
                this.dispose();
                new ProduitsScreen();
            }catch(Exception ex){
                
            }
            dispose();
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
