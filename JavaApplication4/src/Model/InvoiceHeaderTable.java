/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mohammed Abdelaziz
 */
public class InvoiceHeaderTable  extends AbstractTableModel {
    
    private ArrayList<InvoiceHeader> InvoiceTable;
    private String[] columns = {"No.","Date","Customer","Total"};
    private Object Date;
    public InvoiceHeaderTable(ArrayList<InvoiceHeader> InvoiceTable) 
    {
        this.InvoiceTable = InvoiceTable;
    }
    

    @Override
    public int getRowCount() {
        
        return InvoiceTable.size();
        
        
    }

    @Override
    public int getColumnCount() {
        
        return columns.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
         InvoiceHeader inv = InvoiceTable.get(rowIndex);
        switch (columnIndex) {
            case 0: return inv.getNumberOfInvoice();
            case 1: return Date;
            case 2: return inv.getCustomerName();
            case 3: return inv.getInvoiceTotal();
        }
        return "";
        
        
        
        
        
    }

    public ArrayList<InvoiceHeader> getInvoiceTable() {
        return InvoiceTable;
    }

    public void setInvoiceTable(ArrayList<InvoiceHeader> InvoiceTable) {
        this.InvoiceTable = InvoiceTable;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
    
}
