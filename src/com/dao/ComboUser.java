/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.domain.Transaction;
import com.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Tural
 */
public class ComboUser {

    public ArrayList<User> getUsers() {

        PreparedStatement ps = null;

        ResultSet rs = null;

        ArrayList<User> ul = new ArrayList<User>();

        String sql = "select u.ID, u.FIRST_NAME , u.LAST_NAME, u.USER_NAME\n"
                + "from user u \n"
                + "where u.ACTIVE = '1'";
        try {
            Connection c = new DbConnection().getConnection();
            ps = c.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setId(Integer.parseInt(rs.getString("ID")));
                u.setFirstName(rs.getString("FIRST_NAME"));
                u.setLastName(rs.getString("LAST_NAME"));
                u.setUserName(rs.getString("USER_NAME"));
                ul.add(u);
            }
            return ul;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

    }

}
