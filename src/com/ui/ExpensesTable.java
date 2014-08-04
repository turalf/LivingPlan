/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui;

import com.dao.FinalTransaction;
import com.domain.Transaction;
import com.domain.User;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tural
 */
public class ExpensesTable extends AbstractTableModel {
    
    String [] columns = {"User name","First name","Last name","Individual expense"};
    ArrayList <User> ul = new FinalTransaction().getUser();

    public void setUserListByDate(String fromDate, String toDate){
        this.ul = new FinalTransaction().getIndividualExpensesByDate(fromDate, toDate);
    }
     public String getColumnName(int index){
        return columns[index];
    }
    
    @Override
    public int getRowCount() {
        return ul.size();
    }

    @Override
    public int getColumnCount() {
       return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        User user = ul.get(rowIndex);
        System.out.println("");
        
        switch (columnIndex){
            case 0: return user.getUserName();
                    
            case 1: return user.getFirstName();
                   
        
                   
            case 2: return user.getLastName();
            case 3: return user.getIndividualExpense();
                    
        }
        return user;
        
    }
    
    
    
    
}
