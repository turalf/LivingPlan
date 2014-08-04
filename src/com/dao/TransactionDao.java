/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.business.ITransation;
import com.domain.Category;
import com.domain.Expense;
import com.domain.Transaction;
import com.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.vrf.Log;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tural
 */
public class TransactionDao implements ITransation {

    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String TIME_FORMAT = "YYYY-MM-dd hh:mm:ss";

    @Override
    public ArrayList<Transaction> getTransaction() {
        PreparedStatement ps = null;

        ResultSet rs = null;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        String sql = "select r.PAID_BY_USER_ID,\n"
                + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId \n"
                + "from  raw1 r, user u\n"
                + "where u.ID = r.ACK_BY_USER_ID\n"
                + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName order by r.TRANSACTION_DATE DESC";
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                User user = new User(rs.getInt("PAID_BY_USER_ID"));
                user.setUserName(rs.getString("PAID_BY_username"));
                t.setPaidByUser(user);
              //  t.setCategory(new Category(rs.getInt("categoryId"), rs.getString("categoryName")));
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
    

     public ArrayList<Transaction> getTransaction(String paidByUser, String acqByuser,String category, String description) {
        PreparedStatement ps = null;
        
        ResultSet rs = null;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        String sql = "select * from (select r.PAID_BY_USER_ID,\n"
                + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId \n"
                + "from  raw1 r, user u\n"
                + "where u.ID = r.ACK_BY_USER_ID and r.Paid_by_username like '%"+paidByUser+"%' and "
                + " r.categoryName like '%"+category+"%'   and r.description like '%"+description+"%' \n"
                + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName order by r.TRANSACTION_DATE DESC) tran"
                + " where tran.Belongs_to like '%"+acqByuser+"%'"
                + "";
        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                User user = new User(rs.getInt("PAID_BY_USER_ID"));
                user.setUserName(rs.getString("PAID_BY_username"));
                t.setPaidByUser(user);
//                t.setCategory(new Category(rs.getInt("categoryId"), rs.getString("categoryName")));
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
    
    
    public ArrayList<Transaction> getTransaction(String searchKey, String place) {
        PreparedStatement ps = null;

        ResultSet rs = null;
        String sql = "";
        if (place.equalsIgnoreCase("paidByUser")) {
            sql = "select r.PAID_BY_USER_ID,\n"
                    + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                    + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                    + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId \n"
                    + "from  raw1 r, user u\n"
                    + "where u.ID = r.ACK_BY_USER_ID and r.Paid_by_username like '%" + searchKey + "%'\n"
                    + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName order by r.TRANSACTION_DATE DESC";
        }
        if (place.equalsIgnoreCase("ackbyuser")) {
            sql = "select * from (\n"
                    + "select r.PAID_BY_USER_ID,\n"
                    + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                    + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                    + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId\n"
                    + "from  raw1 r, user u\n"
                    + "where u.ID = r.ACK_BY_USER_ID \n"
                    + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName\n"
                    + " order by r.TRANSACTION_DATE DESC) tran\n"
                    + "where tran.Belongs_to like '%"+searchKey+"%'";
        }
        
        if (place.equalsIgnoreCase("category")){
             sql = "select r.PAID_BY_USER_ID,\n"
                + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId \n"
                + "from  raw1 r, user u\n"
                + "where u.ID = r.ACK_BY_USER_ID and r.categoryName like '%"+searchKey+"%' \n"
                + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName order by r.TRANSACTION_DATE DESC";
     
        }
        if (place.equalsIgnoreCase("description")){
             sql = "select r.PAID_BY_USER_ID,\n"
                + "       r.Paid_by_username,group_concat( distinct r.DESCRIPTION) description, \n"
                + "       group_concat(r.ACK_BY_USER_ID) belong_to_id , group_concat( u.USER_NAME) as Belongs_to, sum(r.ACTUAL_AMOUNT) actual_amount,\n"
                + "       DATE_FORMAT(r.TRANSACTION_DATE,'%Y-%m-%d') as TRANSACTION_DATE , r.categoryName, r.categoryId \n"
                + "from  raw1 r, user u\n"
                + "where u.ID = r.ACK_BY_USER_ID and r.description like '%"+searchKey+"%' \n"
                + "group by r.PAID_BY_USER_ID, r.Paid_by_username,  r.TRANSACTION_DATE, r.categoryName order by r.TRANSACTION_DATE DESC";
     
        }
        
        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        try {
            Connection conn = new DbConnection().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                User user = new User(rs.getInt("PAID_BY_USER_ID"));
                user.setUserName(rs.getString("PAID_BY_username"));
                t.setPaidByUser(user);
//                t.setCategory(new Category(rs.getInt("categoryId"), rs.getString("categoryName")));
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
            //not a good wayd
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
        if (result) {
            try {
                Logger l = Log.getLogger();
                File f = new File("../log/LogFile.txt");
                FileWriter fw = new FileWriter(f);
                l.log(Level.OFF, sf.format(new Date()) + "----in that dat---" + paidByUser.getUserName() + "is added new item with " + amount);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    public boolean addTrToDB(User paidByUser, ArrayList<User> ackByUsers, String desc, Double amount, String trDate, int catId) {

        PreparedStatement ps = null;

        ResultSet rs = null;

        boolean result = true;

        ArrayList<Transaction> ft = new ArrayList<Transaction>();

        int noOfTransactions = ackByUsers.size();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat(TIME_FORMAT);
        try {

            String[] dateDetails = trDate.split("-");
            c.set(Integer.parseInt(dateDetails[0]), Integer.parseInt(dateDetails[1]) - 1, Integer.parseInt(dateDetails[2]));

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User u : ackByUsers) {
            String sql = "";
            sql += " insert into transaction(id,paid_by_user_id,ack_by_user_id, description,actual_amount,transaction_date,active,categoryId)\n ";
            sql += "values(DEFAULT," + paidByUser.getId() + "," + u.getId() + ",'" + desc + "'," + amount / noOfTransactions + ",'" + sf.format(c.getTime()) + "','1'," + catId + ") ";

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

    public ArrayList<Expense> getExpenses() {
        ArrayList<Expense> el = new ArrayList<Expense>();
        PreparedStatement ps = null;

        ResultSet rs = null;

        String sql = "select t.ACK_BY_USER_ID as userId, u.USER_NAME ,u.FIRST_NAME , u.LAST_NAME , sum(t.ACTUAL_AMOUNT)  as individualExpenses, t.categoryId, c.CategoryName\n"
                + "from transaction t, user u, category c\n"
                + "where u.ID = t.ACK_BY_USER_ID  and c.CategoryId = t.categoryId\n"
                + "group by t.ACK_BY_USER_ID, CategoryId;";

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

                Category c = new Category(rs.getInt("categoryId"), rs.getString("categoryName"));
                Expense e = new Expense();
                e.setExpenseCategory(c);
                e.setUser(u);
                el.add(e);
            }
            return el;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Expense> getIndividualExpensesByDate(String fromDate, String toDate) {
        ArrayList<Expense> el = new ArrayList<Expense>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select t.ACK_BY_USER_ID as userId, u.USER_NAME ,u.FIRST_NAME , u.LAST_NAME , sum(t.ACTUAL_AMOUNT)  as individualExpenses, t.categoryId, c.CategoryName\n"
                + "from transaction t, user u, category c\n"
                + "where u.ID = t.ACK_BY_USER_ID  and c.CategoryId = t.categoryId and (t.transaction_date <= '" + toDate + " 23:59:59' and t.transaction_date >= '" + fromDate + "')\n"
                + "group by t.ACK_BY_USER_ID , t.CategoryId";

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

                Category c = new Category(rs.getInt("categoryId"), rs.getString("categoryName"));
                Expense e = new Expense();
                e.setExpenseCategory(c);
                e.setUser(u);
                el.add(e);
            }
            return el;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
