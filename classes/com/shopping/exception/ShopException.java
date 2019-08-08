package com.shopping.exception;

/**
 * <p>
 * User defined shop exception 
 * </p>
 *
 * @author - Tharun G.
 * @date   - 31/07/2019.
 */
public class ShopException extends Exception{
   String message1;

   public ShopException(String message2) {
	message1 = message2;
   }
   public String toString(){ 
	return (message1) ;
   }
}
