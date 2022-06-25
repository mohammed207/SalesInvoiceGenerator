/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTable;
import Model.InvoiceLines;
import Model.InvoiceLinesTable;
import View.Header;
import View.Jframe;
import View.Lines;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Mohammed Abdelaziz
 */
public class ActionHandler  implements ActionListener {
       private Jframe frame;
      private Header headerdialog;
     private Lines linedialog;

    
    public ActionHandler(Jframe frame){
        this.frame = frame; }

    public ActionHandler() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void valueChanged(ListSelectionEvent lse) {
        int selectionInvoices=frame.getHeaderTable().getSelectedRow();
        System.out.println("Invoice is selected"+ selectionInvoices);
        if (selectionInvoices != -1) {
        InvoiceHeader selInvoices = frame.getInvsarray().get(selectionInvoices);
        ArrayList<InvoiceLines>linesOfInv = (ArrayList<InvoiceLines>) selInvoices.getLinesOfInv();
        InvoiceLinesTable linemodel= new InvoiceLinesTable(linesOfInv);
        frame.setLineInvoiceTable(linesOfInv);
        frame.getLineTable().setModel(linemodel);
        frame.getInvoicenumberlabel().setText(""+selInvoices.getNumberOfInvoice());
        frame.getCustomernamefield().setText(selInvoices.getCustomerName());
        frame.getInvoicedatefield().setText(SIG_Application_Frame.dateFormat.format(selInvoices.getInvoiceDate()));
        frame.getInvoicetotallabel().setText(""+selInvoices.getInvoiceTotal());}
         
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        System.out.println("Action Handling Called!");
        
        switch(e.getActionCommand()){
             
               case"Load File":
       {
           try {
               loadfile();
           } catch (IOException ex) {
               Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
               break;
               case "Save File":
                  savefile(); 
               break;
               case "Create New Invoice":
                   createnewinvoice();
               break;
               case "Delete Invoice":
                   deleteinvoice();
               break;
               case "Save":
                   save();
               break;
               
               case "Cancel":
                   cancel();
               break;
               case"lineOK":
                makeThelineOKDialog();
                break;
                case"LineCancel":
                closeThelineCancelDialog();
                break;
               
               case"InvoiceCancel":
                   closeTheInvCancelDialog();
                   break;
                   
               case"OK":
                   makeTheInvoiceOKDialog();
                   break;
                   
                   
               
       }
     
    }

    private void savefile()    {
        String headerFile = "";
        String lineFile = "";
        for (InvoiceHeader Header : frame.getInvoiceTable()) {
            headerFile+=Header.getCSVfile;
            headerFile+="\n";
            for (InvoiceLines Line : frame.getLinesTable()) {
                lineFile+=Line.getCSVfile();
                lineFile+="\n";}

        }
        JOptionPane.showMessageDialog(frame, "Select Header File", "attension", JOptionPane.WARNING_MESSAGE);
        JFileChooser choosefile = new JFileChooser();
        int saveData =choosefile.showSaveDialog(frame);
        if (saveData == JFileChooser.APPROVE_OPTION) {
            File headersfile = choosefile.getSelectedFile();
            try{            
                FileWriter filewrite = new FileWriter(headersfile);
                filewrite.write(headerFile);
                filewrite.flush();
                filewrite.close();
            JOptionPane.showMessageDialog(frame, "Select Line File", "attension", JOptionPane.WARNING_MESSAGE);
            saveData=choosefile.showSaveDialog(frame);
            if (saveData == JFileChooser.APPROVE_OPTION) {
            File linesfile = choosefile.getSelectedFile();
            FileWriter linefilewrite = new FileWriter(linesfile);
            linefilewrite.write(lineFile);
            linefilewrite.flush();
            linefilewrite.close();

            }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "error: " + ex.getMessage(), "error", JOptionPane.ERROR_MESSAGE);}
            

        }

        
        

    }
    
         
   
    private void createnewinvoice() {
        headerdialog=new Header(frame);
        headerdialog.setVisible(true);
    }

    private void deleteinvoice() {
        int linetableselection = Jframe.getInvoiceHeaderTable().getSelectedRow();
        if (linetableselection != -1) {
            frame.getInvoiceTable().remove(linetableselection);
            frame.getInvoiceHeadertablemodel().fireTableDataChanged();

frame.getLinesTable().setModel(new InvoiceLinesTable(null));           
            frame.getInvoicenumberlabel().setText("");
            frame.getCustomernamefield().setText("");
            frame.getInvoicedatefield().setText("");
            frame.getInvoicetotallabel().setText("");
        
        }
    }

    private void save() {
         linedialog=new Lines(frame);
         linedialog.setVisible(true);
        
    }

    private void cancel() {
        int line=frame.getLineTable().getSelectedRow();
        int removeINNtotal = frame.getLineTable().getSelectedRow();

        if(line!= -1){
            frame.getLineInvoiceTable().remove(line);
            var lineTable =(InvoiceLinesTable)frame.getLineTable().getModel();
            lineTable.fireTableDataChanged();
            Jframe.getInvoiceTotal().setText(""+frame.getInvsarray().get(removeINNtotal).getInvoiceTotal());
            frame.getHeadertablemodel().fireTableDataChanged();
            frame.getHeaderTable().setRowSelectionInterval(removeINNtotal, removeINNtotal);

        }
       
    }

    private void loadfile () throws IOException{
           JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fileChooser.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
                for (String headerLine : headerLines) {
                    String[] arr = headerLine.split(",");
                    String str1 = arr[0];
                    String str2 = arr[1];
                    String str3 = arr[2];
                    int code = Integer.parseInt(str1);
                    Date invoiceDate = Jframe.dateFormat.parse(str2);
                    InvoiceHeader header = new InvoiceHeader(code, str3, invoiceDate);
                    invoiceHeaders.add(header);  }
                
                frame.setInvoiceTable(invoiceHeaders);

                result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fileChooser.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    ArrayList<InvoiceLines> Invoice = new ArrayList<>();
                    for (String lineLine : lineLines) {
                        String[] arr = lineLine.split(",");
                        String str1 = arr[0];    
                        String str2 = arr[1];       
                        String str3 = arr[2];          
                        String str4 = arr[3];           
                        int invCode = Integer.parseInt(str1);
                        double ItemPrice = Double.parseDouble(str3);
                        int NumberOfItems = Integer.parseInt(str4);
                        InvoiceHeader inv =frame.getInvoiceobject(invCode);
                        InvoiceLines line = new InvoiceLines(ItemPrice, str2, NumberOfItems, inv);
                        inv.getLinesOfInv().add(line);}
                   
                
                }
            
                InvoiceHeaderTable headertablemodel = new InvoiceHeaderTable(invoiceHeaders);
                frame.setHeadertablemodel(headertablemodel);
                frame.getHeaderTable().setModel(headertablemodel);
                System.out.println("checkk");
            }            
         } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeTheInvCancelDialog() {
        headerdialog.setVisible(false);
        headerdialog.dispose();
        headerdialog=null;
    }

    private void makeTheInvoiceOKDialog() {
         headerdialog.setVisible(false);
         String customername=headerdialog.getCustomerName().getText();
         String invoicedate=headerdialog.getInvDateText().getText();
         Date date=new Date();
        try {
            date = SIG_Application_Frame.dateFormat.parse(invoicedate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot get the current date.", "Invalid date", JOptionPane.ERROR_MESSAGE);
        }
        int num = 0;
        for (Header_SIG_app inv : frame.getInvsarray()) {
            if (inv.getNumberOfInvoice() > num) {
                num = inv.getNumberOfInvoice();}
        }
        num++;
        InvoiceHeader in = new InvoiceHeader(num, customername, date);
        frame.getInvsarray().add(in);
        frame.getHeadertablemodel().fireTableDataChanged();
        headerdialog.dispose();
        headerdialog=null;
    }

    private void makeThelineOKDialog() {
        linedialog.setVisible(false);
        String numbercount=linedialog.getCountField().getText();
        String price=linedialog.getPriceField().getText();
        String name = linedialog.getNameField().getText();

        int countnum = 1;
        double pricenum = 1;
        
        try {
            pricenum = Double.parseDouble(price);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot show the price", "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }

         try {
            countnum = Integer.parseInt(numbercount);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Cannot show the number", "Invalid Data", JOptionPane.ERROR_MESSAGE);
        }
        int invoiceH = frame.getHeaderTable().getSelectedRow();
        if (invoiceH != -1) {
            InvoiceHeader header=frame.getInvoiceTable().get(invoiceH);
            InvoiceLines Lineframe=new InvoiceLines(pricenum, name,countnum,header);
            frame.getLinesTable().add(Lineframe);
            InvoiceLinesTableModel lineTable =(InvoiceLinesTableModel)frame.getLineTable().getModel();
            lineTable.fireTableDataChanged();
            frame.getHeadertablemodel().fireTableDataChanged();

        }
        
        frame.getHeaderTable().setRowSelectionInterval(invoiceH, invoiceH);

        linedialog.dispose();
        linedialog=null;
        
    }

    private void closeThelineCancelDialog() {
        linedialog.setVisible(false);
        linedialog.dispose();
        linedialog=null;
    }

        
        
        
        
        
        
        
        }
        
        
        
        
        
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
}
