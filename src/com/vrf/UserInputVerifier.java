package com.vrf;


import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tural
 */
public class UserInputVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String numericRegex = "-?\\d+(\\.\\d+)?";
        String className = input.getClass().getName();
        switch (className) {
            case "javax.swing.JTextField":
                if (((JTextField) input).getText().matches(numericRegex)) {
                    return true;
                } else {
                    return false;

                }
            default: return false;

        }

    }
    

}
