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
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private int active; 
    private double individualExpense;

    public User(){
    }
    public User(int uid){
        this.id = uid;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public double getIndividualExpense() {
        return individualExpense;
    }

    public void setIndividualExpense(double individualExpense) {
        this.individualExpense = individualExpense;
    }

    
    
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public int getActive() {
        return active;
    }
    
    
}
