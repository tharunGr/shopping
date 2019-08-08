package com.shopping.utils;

import com.shopping.service.ProductService;

public class ProductServiceUtil {

    private static ProductService productService;
    private static ProductServiceUtil productServiceUtil;

    private ProductServiceUtil() {
        productService = new ProductService();
    }

    public static ProductService getInstance() {
        if (productServiceUtil == null) {
            productServiceUtil = new ProductServiceUtil();
        }
        return productService; 
    }	
}
