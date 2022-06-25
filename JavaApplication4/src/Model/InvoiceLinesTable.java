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
public class InvoiceLinesTable extends AbstractTableModel{
    private ArrayList<InvoiceLines> linesTable;
    private String[] columns={"No.","Item Name","Item Price","Count","Item Total"};
    public InvoiceLinesTable(ArrayList<InvoiceLines>linesTable){
        this.linesTable= linesTable;
    }
    
    

    @Override
    public int getRowCount() {

return  linesTable== null ?0: linesTable.size();


    }

    @Override
    public int getColumnCount() {

        return columns.length;


    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

InvoiceLines INF = linesTable.get(rowIndex);
         if(linesTable==null)   {return"";}
         else{
        switch (columnIndex) {
            case 0: return INF.getItemName();
            case 1: return INF.getItemPrice();
            case 2: return INF.getNumberOfItems();
            case 3: return INF.getmultofLineTotal();
            default: return "";
        }
         }
        
        
        
        
        



    }

    public void setLinesTable(ArrayList<InvoiceLines> linesTable) {
        this.linesTable = linesTable;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }
    
}
