package com.shopping.utils;

import com.shopping.service.CartService;

public class CartServiceUtil {

    private static CartService cartService;
    private static CartServiceUtil cartServiceUtil;

    private CartServiceUtil() {
        cartService = new CartService();
    }

    public static CartService getInstance() {
        if (cartServiceUtil == null) {
            cartServiceUtil = new CartServiceUtil();
        }
        return cartService; 
    }	
}
