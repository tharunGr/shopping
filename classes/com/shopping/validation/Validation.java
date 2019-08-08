package com.shopping.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;

import com.shopping.model.User;
import com.shopping.exception.ShopException;

/**
 * <p>
 * Validate the fields of user.
 * </p>
 *
 * @author - Tharun G.
 * @date   - 01/07/2019.
 */
public class Validation {
  
    /**
     * <p>
     * It checks for valid email
     * </p>
     *
     * @param input - User's input email address 
     * @return      - It returns user output
     */
    public static String getEmail(String input) throws ShopException {
        if (Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", input)) {
            return input; 
        } else {
            throw new ShopException("Enter the valid E-mail id");
        }
        
    }
  
    /**
     * <p>
     * It checks for valid mobile number
     * </p>
     *
     * @param input - User's input email address 
     * @return      - It returns user output
     */
    public static String getMobileNumber(String input) throws ShopException {
        if (Pattern.matches("[789]{1}[0-9]{9}", input)) {
            return input; 
        } else {
            throw new ShopException("Enter the valid mobile number : 10 digit numbers only");
        } 
    }
   
    /**
     * <p>
     * It checks for valid password
     * </p>
     *
     * @param input - User's input email address 
     * @return      - It returns user output
     */
    public static String getPassword(String input) throws ShopException {
        if (Pattern.matches("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@,.#$%!]).{8,40})", input)) {
            return input; 
        } else {
            throw new ShopException("Enter the valid pass word : \n" 
                                       + "Atleast one Uppercase Character\n" 
                                       + "Atleast one Lowercase Character\n" 
                                       + "Atleast one digit \n"
                                       + "Atleast one special character");
        }    
    } 
}
