package com.shopping.utils;

import java.text.Format;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;

import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

/**
 * <p> 
 * It has common utility methods of usage for various modules in online shopping   
 * project.
 * </p>
 *
 * @author Tharun
 * @date   19/06/2019
 */
public class DateUtil {
 
    /**
     * <p> 
     * Getting current date
     * </p>
     *
     * @return date for creating and updating orders
     */
    public static LocalDateTime getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");  
        String date = now.format(format); 
        LocalDateTime dateTime = LocalDateTime.parse(date, format);    
        return dateTime;            
    }

    /**
     * <p> 
     * Converting String to date.
     * </p>
     *
     * @param  - Date to be converted.
     * @return - date for creating and updating orders
     */
    public static LocalDateTime getCorrectedDate(String date) {
        String correctDate = date.replace("T"," ");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss"); 
        LocalDateTime dateTime = LocalDateTime.parse(correctDate, format);
        return dateTime;            
    }
}














