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
public class Category {
    private int categoryId;
    private String categoryName;

    public Category(){
        
    }
    
    public Category(int catId){
        this.categoryId = catId;
    }
    
    public Category(int catId, String catName){
        this.categoryId = catId;
        this.categoryName = catName;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    
    
    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.categoryId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return true;
    }
    
    
    
}
