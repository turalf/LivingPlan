/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.domain;

import java.util.Objects;

/**
 *
 * @author Tural
 */
public class DebtDmn {
    
    private int debtOwnerId;
    private String debtOwnerUserName;
    private int debterId;
    private String debterUserName;
    private Double debt_amount;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.debtOwnerId;
        hash = 19 * hash + this.debterId;
        hash = 19 * hash + Objects.hashCode(this.debt_amount);
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
        final DebtDmn other = (DebtDmn) obj;
        if (this.debtOwnerId != other.debtOwnerId) {
            return false;
        }
        if (this.debterId != other.debterId) {
            return false;
        }
        if (!Objects.equals(this.debt_amount, other.debt_amount)) {
            return false;
        }
        return true;
    }

    public void setDebtOwnerId(int debtOwnerId) {
        this.debtOwnerId = debtOwnerId;
    }

    public void setDebtOwnerUserName(String debtOwnerUserName) {
        this.debtOwnerUserName = debtOwnerUserName;
    }

    public void setDebterId(int debterId) {
        this.debterId = debterId;
    }

    public void setDebterUserName(String debterUserName) {
        this.debterUserName = debterUserName;
    }

    public void setDebt_amount(Double debt_amount) {
        this.debt_amount = debt_amount;
    }

    
    
    
    public int getDebtOwnerId() {
        return debtOwnerId;
    }

    public String getDebtOwnerUserName() {
        return debtOwnerUserName;
    }

    public int getDebterId() {
        return debterId;
    }

    public String getDebterUserName() {
        return debterUserName;
    }

    public Double getDebt_amount() {
        return debt_amount;
    }
    
}
