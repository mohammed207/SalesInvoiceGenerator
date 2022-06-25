package View;


import View.Jframe;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Mohammed Abdelaziz
 */
public class Lines extends JDialog {
    
     JTextField NameField;
      JTextField CountField;
      JTextField PriceField;
      private JLabel NameLabel;
      private JLabel CountLabel;
     private JLabel PriceLabel;
     private JButton CancelButton;
    private JButton SaveButton;
   public Lines(Jframe frame) {
        NameField = new JTextField(20);
        NameLabel = new JLabel("Item Name");
        
        CountField = new JTextField(20);
        CountLabel = new JLabel("Item Count");
        
        PriceField = new JTextField(20);
        PriceLabel = new JLabel("Item Price");
        
        SaveButton= new JButton("Save");
        CancelButton = new JButton("Cancel");
        
        SaveButton.setActionCommand("lineSave");
        CancelButton.setActionCommand("LineCancel");
        
        SaveButton.addActionListener(frame.getActionListener());
        CancelButton.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(4, 2));
        
        add(NameLabel);
        add(NameField);
        add(CountLabel);
        add(CountField);
        add(PriceLabel);
        add(PriceField);
        add(SaveButton);
        add(CancelButton);
        
        pack();
    }

    public JTextField getNameField() {
        return NameField;
    }

    public JTextField getCountField() {
        return CountField;
    }

    public JTextField getPriceField() {
        return PriceField;
    }

    public JLabel getNameLabel() {
        return NameLabel;
    }

    public void setNameLabel(JLabel NameLabel) {
        this.NameLabel = NameLabel;
    }

    public JLabel getCountLabel() {
        return CountLabel;
    }

    public void setCountLabel(JLabel CountLabel) {
        this.CountLabel = CountLabel;
    }

    public JLabel getPriceLabel() {
        return PriceLabel;
    }

    public void setPriceLabel(JLabel PriceLabel) {
        this.PriceLabel = PriceLabel;
    }

    public JButton getCancelButton() {
        return CancelButton;
    }

    public void setCancelButton(JButton CancelButton) {
        this.CancelButton = CancelButton;
    }

    public JButton getSaveButton() {
        return SaveButton;
    }

    public void setSaveButton(JButton SaveButton) {
        this.SaveButton = SaveButton;
    }
    
    
    
}
