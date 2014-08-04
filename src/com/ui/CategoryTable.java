/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ui;

import com.domain.Category;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tural
 */
public class CategoryTable  extends AbstractTableModel{
     private String [] columns = {"CategoryId","CategoryName"};
    ArrayList<Category> cl = new com.dao.CategoryDao().getCategories();

     @Override
    public String getColumnName(int index){
        return columns[index];
    }
    @Override
    public int getRowCount() {
        return cl.size();
    }

    @Override
    public int getColumnCount() {
        return Category.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex ){
             case 0: return cl.get(rowIndex).getCategoryId();
                     
             case 1: return cl.get(rowIndex).getCategoryName();
                     
        }
           return  null;
    }
     
     @Override
     public boolean isCellEditable(int row, int col){
         return col != 0;
     }
     
     public Category getValue(int row){
         return cl.get(row);
     }
     public void setValueAt(int row, int column){
         
     }
    
}
