/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.gui.widgets;

import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Caleb Lyc
 */
public abstract class SuperFormComponent extends JFrame {
    protected static JPanel contentPanel;
    protected static final GridBagConstraints gbc = new GridBagConstraints();
    
    public SuperFormComponent(){
        
    }
    
    protected boolean validateFields() {
        Component[] components = contentPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField textField) {
                if (textField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }
    
    protected void clearFields() {
        Component[] components = contentPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField textField) {
                textField.setText("");
            }
        }
    }
    
    protected abstract void addFields();
    protected abstract void displayDialog(); 
}
