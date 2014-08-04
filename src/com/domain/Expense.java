/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domain;

/**
 *
 * @author Tural
 */
public class Expense {
    
    private User user;
    private Category expenseCategory;

    public User getUser() {
        return user;
    }

    public Category getExpenseCategory() {
        return expenseCategory;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExpenseCategory(Category expenseCategory) {
        this.expenseCategory = expenseCategory;
    }
    
    
    
}
