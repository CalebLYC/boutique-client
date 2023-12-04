/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.client.gui.widgets;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;
/**
 *
 * @author Caleb Lyc
 */
public class FormComponentBuilder {
    private GridBagConstraints gbc;
    private JPanel contentPanel;
    
    public FormComponentBuilder(GridBagConstraints gbc, JPanel contentPanel){
        this.gbc = gbc;
        this.contentPanel = contentPanel;
    }
    
    public JTextField addFormField(String label) {
        gbc.gridx = 0;
        gbc.gridy = contentPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 5, 10);

        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        contentPanel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);

        contentPanel.add(textField, gbc);
        return textField;
    }

    public JSpinner createDatePicker(String label) throws ParseException {
        gbc.gridx = 0;
        gbc.gridy = contentPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 0, 5, 10);

        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(dateModel);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, "yyyy-MM-dd");
        DateFormatter dateFormatter = (DateFormatter) dateEditor.getTextField().getFormatter();
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);
        dateFormatter.setCommitsOnValidEdit(true);

        spinner.setEditor(dateEditor);

        gbc.gridx = 0;
        contentPanel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 0, 5, 0);

        contentPanel.add(spinner, gbc);
        gbc.gridy++;

        return spinner;
    }

    public <T> JComboBox addFormField(String label, JComboBox comboBox, List<T> items) {
        
        for (T item : items) {
            comboBox.addItem(item);
        }
        JLabel fieldLabel = new JLabel(label + ":");
        fieldLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        contentPanel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        contentPanel.add(comboBox, gbc);
        gbc.gridy++;
        return comboBox;
    }
}
