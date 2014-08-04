/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Tural
 */
public class Transaction {
    private int id;
    private User paidByUser;
    private User ackByUser;
    private String descrition;
    private BigDecimal paidAmount;
    private int belongToNo;
    private BigDecimal actualAmount;
    private Calendar trDate;
    private int active  =1;
    private String ackBy;

    public String getAckBy() {
        return ackBy;
    }

    public void setAckBy(String ackBy) {
        this.ackBy = ackBy;
    }
    
    
    

    public void setId(int id) {
        this.id = id;
    }

    public void setPaidByUser(User paidByUser) {
        this.paidByUser = paidByUser;
    }

    public void setAckByUser(User ackByUser) {
        this.ackByUser = ackByUser;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public void setBelongToNo(int belongToNo) {
        this.belongToNo = belongToNo;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public void setTrDate(Calendar trDate) {
        this.trDate = trDate;
    }

    public void setActive(int active) {
        this.active = active;
    }

    
    
    public int getId() {
        return id;
    }

    public User getPaidByUser() {
        return paidByUser;
    }

    public User getAckByUser() {
        return ackByUser;
    }

    public String getDescrition() {
        return descrition;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public int getBelongToNo() {
        return belongToNo;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public Calendar getTrDate() {
        System.out.println(trDate.getTime());
        return trDate;
    }

    public int getActive() {
        return active;
    }
    
    
    
    
}
