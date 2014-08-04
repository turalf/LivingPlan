/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui;

import com.dao.FinalTransaction;
import com.domain.Transaction;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tural
 */
public class TransactionTable extends AbstractTableModel{
    
    
    private String [] columnNames = {"Paid by","Actual amount","Description","Date","Acquired by"};
     private final String DATE_FORMAT = "EEE, dd MMM yyyy";
    ArrayList <Transaction> tList = new FinalTransaction().getTransaction();
    
    Transaction t =  null;
    
    public String getColumnName(int index){
        return columnNames[index];
    }
    @Override
    public int getRowCount() {
       return tList.size();
    }

    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Transaction ft = tList.get(rowIndex);
        
        switch (columnIndex){
            case 0: return ft.getPaidByUser().getUserName();
                  
                    
            case 1: return ft.getPaidAmount();
                   
        
                   
            case 2: return ft.getDescrition();
            case 3: return new SimpleDateFormat(DATE_FORMAT).format(ft.getTrDate().getTime());
            case 4: return ft.getAckBy();
                    
        }
        return ft;
        
    }
    
}
