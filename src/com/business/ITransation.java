/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.business;

import com.dao.TransactionDao;
import com.domain.Transaction;
import java.util.ArrayList;

/**
 *
 * @author Tural
 */
public interface ITransation {
    
    public ArrayList<Transaction> getTransaction();
    
    
}
