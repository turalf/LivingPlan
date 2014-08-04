/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import com.dao.FinalTransaction;
import com.domain.Transaction;
import java.util.ArrayList;

/**
 *
 * @author Tural
 */
public interface ITransation {
    
    public ArrayList<Transaction> getTransaction();
    
    
}
