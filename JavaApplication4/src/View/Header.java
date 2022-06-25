/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Mohammed Abdelaziz
 */
public class Header extends JDialog{
    
    JTextField CustomerName;
     private JTextField InvoiceDateText;
     private JLabel CustomerNameText;
     private JLabel InvoiceDate;
     private JButton SaveButton;
    private JButton CancelButton;
    public Header(Jframe frame) {
        CustomerNameText = new JLabel("Customer Name:");
        CustomerName = new JTextField(20);
        InvoiceDate = new JLabel("Invoice Date:");
        InvoiceDateText = new JTextField(20);
        CancelButton = new JButton("Cancel");
         SaveButton = new JButton("Save");
        
        
        SaveButton.setActionCommand("OK");
        CancelButton.setActionCommand("InvoiceCancel");
        
         SaveButton.addActionListener(frame.getActionListener());
        CancelButton.addActionListener(frame.getActionListener());
        setLayout(new GridLayout(3, 2));
        
        add(InvoiceDate);
        add(InvoiceDateText);
        add(CustomerNameText);
        add(CustomerName);
        add( SaveButton );
        add(CancelButton);
        
        pack();
        
    }

    public JTextField getCustomerName() {
        return CustomerName;
    }

    public JTextField getInvDateText() {
        return InvoiceDateText;
    }

    public JTextField getInvoiceDateText() {
        return InvoiceDateText;
    }

    public void setInvoiceDateText(JTextField InvoiceDateText) {
        this.InvoiceDateText = InvoiceDateText;
    }

    public JLabel getCustomerNameText() {
        return CustomerNameText;
    }

    public void setCustomerNameText(JLabel CustomerNameText) {
        this.CustomerNameText = CustomerNameText;
    }

    public JLabel getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(JLabel InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public JButton getSaveButton() {
        return SaveButton;
    }

    public void setSaveButton(JButton SaveButton) {
        this.SaveButton = SaveButton;
    }

    public JButton getCancelButton() {
        return CancelButton;
    }

    public void setCancelButton(JButton CancelButton) {
        this.CancelButton = CancelButton;
    }
    

    
    
    
    
    
}
