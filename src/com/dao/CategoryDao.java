/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.domain.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tural
 */
public class CategoryDao {

    public ArrayList<Category> getCategories() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Category> categoryList = null;
        String sql = "select c.CategoryId,c.CategoryName\n"
                + "from Category c\n"
                + "where c.IsActive = 1";

        try {
            Connection c = new DbConnection().getConnection();
            ps = c.prepareCall(sql);
            rs = ps.executeQuery();
            categoryList = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("CategoryId"));
                category.setCategoryName(rs.getString("CategoryName"));
                categoryList.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public Category getCategoryByCategoryName(String catName) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Category> categoryList = null;
        int catId = -1;
        Category cat = null;
        String categoryName = "";
        String sql = "select c.CategoryId, c.CategoryName \n"
                + "from Category c\n"
                + "where c.IsActive = 1 and c.CategoryName = '" + catName + "'";
        try {
            Connection c = new DbConnection().getConnection();
            ps = c.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                catId = rs.getInt("categoryId");
                catName = rs.getString("categoryName");
                cat = new Category();
                cat.setCategoryId(catId);
                cat.setCategoryName(categoryName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cat;
    }

    public boolean updateCategoryName(Category c, String newCategoryName) {
       
        PreparedStatement ps = null;
        String sql = "update category\n"
                + "set CategoryName=? \n"
                + "where CategoryId=?";
        try{
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCategoryId());
            ps.setString(2, newCategoryName);
            ps.executeQuery();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
     public boolean deleteCategoryName(Category c) {
       
        PreparedStatement ps = null;
        String sql = "update category\n"
                + "set IsActive=0 \n"
                + "where CategoryId=?";
        try{
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCategoryId());
            ps.executeQuery();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    
    
}
