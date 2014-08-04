/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Tural
 */
public class DbConnection {
    private Connection connect = null;
    private Statement stm = null;
    private PreparedStatement ps = null;
    private Statement s = null;
    private ResultSet rs = null;
    
    //testing
    public static void main(String [] args){
        new DbConnection().getConnection();
        new FinalTransaction().getTransaction();
    }
    
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://192.168.0.4:3306/living_plan?user=tural&password=123456");
            System.out.println("connected to Db successfully");
            return connect;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean closeConnection(){
        
        try{
            this.connect.close();
            System.out.println("Connection closed");
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
     
    }
    
    
    
   
    
}
