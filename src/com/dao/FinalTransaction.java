/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import business.ITransation;
import com.domain.Transaction;
import com.domain.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Tural
 */
public class FinalTransaction implements ITransation {

    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String TIME_FORMAT = "YYYY-MM-dd hh:mm:ss";

    @Override
    public ArrayList<Transaction> getTransaction() {
        PreparedStatement ps = null;

        ResultSet rs = null;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        String sql = "select r.PAID_BY_USER_ID, r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount, DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE\n"
                + "from  raw11 r, user u\n"
                + "where u.ID = r.ACK_BY_USER_ID\n"
                + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE order by r.TRANSACTION_DATE DESC";

        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                User user = new User(rs.getInt("PAID_BY_USER_ID"));
                user.setUserName(rs.getString("PAID_BY_username"));
                t.setPaidByUser(user);

                t.setAckBy(rs.getString("BELONGS_TO"));
                t.setDescrition(rs.getString("DESCRIPTION"));
                t.setPaidAmount(rs.getBigDecimal("ACTUAL_AMOUNT"));
                Calendar c = Calendar.getInstance();
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                c.setTime(df.parse(rs.getString("TRANSACTION_DATE")));
                t.setTrDate(c);
                ft.add(t);
            }

            return ft;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean addTrToDB(User paidByUser, ArrayList<User> ackByUsers, String desc, Double amount) {

        PreparedStatement ps = null;

        ResultSet rs = null;

        boolean result = true;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        int noOfTransactions = ackByUsers.size();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
        for (User u : ackByUsers) {
            String sql = "";
            sql += " insert into transaction(id,paid_by_user_id,ack_by_user_id, description,actual_amount,transaction_date,active)\n ";
            sql += "values(DEFAULT," + paidByUser.getId() + "," + u.getId() + ",'" + desc + "'," + amount / noOfTransactions + ",'" + sf.format(c.getTime()) + "','1'" + ") ";

            try {
                Connection conn = new DbConnection().getConnection();
                ps = conn.prepareCall(sql);
                ps.execute();
                result = result && true;

            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }
    
    
     public boolean addTrToDB(User paidByUser, ArrayList<User> ackByUsers, String desc, Double amount, String strDate)throws ParseException{

        PreparedStatement ps = null;

        ResultSet rs = null;

        boolean result = true;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        int noOfTransactions = ackByUsers.size();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
        strDate += " " + c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
        java.util.Date date = sf.parse(strDate);
        c.setTime(date);
        for (User u : ackByUsers) {
            String sql = "";
            sql += " insert into transaction(id,paid_by_user_id,ack_by_user_id, description,actual_amount,transaction_date,active)\n ";
            sql += "values(DEFAULT," + paidByUser.getId() + "," + u.getId() + ",'" + desc + "'," + amount / noOfTransactions + ",'" + sf.format(c.getTime()) + "','1'" + ") ";

            try {
                Connection conn = new DbConnection().getConnection();
                ps = conn.prepareCall(sql);
                ps.execute();
                result = result && true;

            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }

        return result;
    }
    

    public int getUserIdByUserName(String userName) {
        PreparedStatement ps = null;

        ResultSet rs = null;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        String sql = "select id from user where user_name = '" + userName + "'";
        int id = -1;
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt("ID");

            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("noUser in db");
            return id;
        }

    }

    public ArrayList<User> getUser() {
        ArrayList<User> ul = new ArrayList<User>();
        PreparedStatement ps = null;

        ResultSet rs = null;

        String sql = "select t.ACK_BY_USER_ID as userId, u.USER_NAME ,u.FIRST_NAME , u.LAST_NAME , sum(t.ACTUAL_AMOUNT)  as individualExpenses\n"
                + "from transaction t, user u\n"
                + "where u.ID = t.ACK_BY_USER_ID\n"
                + "group by t.ACK_BY_USER_ID;";
       
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString("userId"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setUserName(rs.getString("user_name"));
                u.setIndividualExpense(rs.getDouble("individualExpenses"));
                ul.add(u);
            }
            return ul;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public ArrayList<User> getIndividualExpensesByDate(String fromDate, String toDate) {
        ArrayList<User> ul = new ArrayList<User>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select t.ACK_BY_USER_ID as userId, u.USER_NAME ,u.FIRST_NAME , u.LAST_NAME , sum(t.ACTUAL_AMOUNT)  as individualExpenses\n"
                + "from transaction t, user u\n"
                + "where u.ID = t.ACK_BY_USER_ID and (t.transaction_date <= '"+toDate+" 23:59:59' and t.transaction_date >= '"+fromDate+"')\n"
                + "group by t.ACK_BY_USER_ID;";
       
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUserName(rs.getString("userId"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setUserName(rs.getString("user_name"));
                u.setIndividualExpense(rs.getDouble("individualExpenses"));
                ul.add(u);
            }
            return ul;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
