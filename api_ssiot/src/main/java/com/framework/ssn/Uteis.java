/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.framework.ssn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alisson Solitto
 */
public class Uteis {
    
    public static String getDateTime() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date(); 
	return dateFormat.format(date); 
    }    
    
    public static String getDateTimeUnderline() { 
	DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
	Date date = new Date(); 
	return dateFormat.format(date); 
    } 
}
