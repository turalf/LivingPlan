/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vrf;

import com.dao.TransactionDao;
import com.domain.Transaction;
import java.util.logging.Logger;

/**
 *
 * @author Tural
 */
public class Log  {
    
    private final static Logger LOGGER = Logger.getLogger(TransactionDao.class.getName());
    public static Logger getLogger (){
        return LOGGER;
    }
    
}
