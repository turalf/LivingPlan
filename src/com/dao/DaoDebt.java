/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.domain.DebtDmn;
import com.domain.Transaction;
import com.ui.DebtUI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tural
 */
public class DaoDebt {

    public static void main(String... args) {
        new DaoDebt().getFinalDebt();
    }

    public ArrayList<DebtDmn> getInitialDebt() {
        PreparedStatement ps = null;

        ResultSet rs = null;

        ArrayList<DebtDmn> debtList = new ArrayList<DebtDmn>();

        String sql = "select ownerId,ownerUser,debtAmount,debterId, u.USER_NAME as debterUsername\n"
                + "from\n"
                + "(select t.PAID_BY_USER_ID as ownerId, u.user_name as ownerUser, sum(t.ACTUAL_AMOUNT) as debtAmount, t.ACK_BY_USER_ID as debterId\n"
                + "from transaction t, user u\n"
                + "where t.PAID_BY_USER_ID <> t.ACK_BY_USER_ID and u.ID = t.PAID_BY_USER_ID\n"
                + "group by t.PAID_BY_USER_ID, t.ACK_BY_USER_ID) d , user u\n"
                + "\n"
                + "where u.ID = d.debterId";
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                DebtDmn debt = new DebtDmn();
                debt.setDebtOwnerId(rs.getInt("ownerId"));
                debt.setDebtOwnerUserName(rs.getString("ownerUser"));
                debt.setDebt_amount(rs.getDouble("debtAmount"));
                debt.setDebterId(rs.getInt("debterId"));
                debt.setDebterUserName(rs.getString("debterUsername"));
                debtList.add(debt);
            }
            return debtList;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("exception occured");
            return null;

        }
    }

    public ArrayList<DebtDmn> getFinalDebt() {
        ArrayList<DebtDmn> dl = getInitialDebt();
        ArrayList<DebtDmn> dlf = getInitialDebt();
        

        for (DebtDmn d : dl) {
            for (DebtDmn d1 : dl) {
                if ((d.getDebtOwnerId() == d1.getDebterId())&&(d.getDebterId()==d1.getDebtOwnerId())) {
                    
                    
                    dlf.remove(d1);
                    for (DebtDmn d2: dlf){
                        if (d2.equals(d)){
                            d2.setDebt_amount(d2.getDebt_amount()-d1.getDebt_amount());
                        }
                    }
                    

                }
            }

        }
       
            
        
        return dlf;
    }
}
