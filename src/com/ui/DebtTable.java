/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui;

import com.dao.DaoDebt;
import com.domain.DebtDmn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tural
 */
public class DebtTable extends AbstractTableModel {
    private String [] columns = {"Debt owner","Debter","Amount"};
    DaoDebt dd = new DaoDebt();
    ArrayList<DebtDmn> fd = dd.getFinalDebt();
    
    
    public String getColumnName(int index){
        return columns[index];
    }

    @Override
    public int getRowCount() {
        return fd.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DebtDmn dd = fd.get(rowIndex);
        switch (columnIndex){
            case 0: return dd.getDebtOwnerUserName();
                    
            case 1: return dd.getDebterUserName();
                   
        
                   
            case 2: return dd.getDebt_amount();
           
                    
        }
        return dd;
    }
    
}
