package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.shopping.dao.ProductDAO;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.exception.ShopException;
import com.shopping.utils.DateUtil;

/**
 * <p> 
 * Performing cart module bussiness logics like adding, comparing and perform 
 * some other operations.
 * </p>
 *
 * @author Tharun
 * @date   20/06/2019
 */
public class ProductService {

    private ProductDAO productDAO = new ProductDAO();

    /** 
     * <p>
     * Creating the new products .
     * </p>
     *
     * @param product       - It is the Object of products. 
     */
    public void createProduct(Product product) throws ShopException {
        product.setStatus(Boolean.TRUE);
        product.setCreatedDate(DateUtil.getDate());
        product.setUpdatedDate(DateUtil.getDate());
        productDAO.createProduct(product);
    }

    /** 
     * <p>
     * Deleting the product from overall product details
     * </p>
     *
     * @param product       - It is the Object of products.  
     */
    public void deleteProduct(int productId) throws ShopException {
        Product product = getProductByProductId(productId);
        product.setStatus(Boolean.FALSE);
        productDAO.deleteProduct(product);
    }

    /** 
     * <p>
     * Updating the specific product details of the products.
     * </p>
     *
     * @param product  - It is the Object of products.
     * @param date     - Product created Date
     */
    public void updateProduct(Product product, String date) throws ShopException {
        product.setCreatedDate(DateUtil.getCorrectedDate(date));
        product.setUpdatedDate(DateUtil.getDate());
        productDAO.updateProduct(product);
    }
   
    /** 
     * <p>
     * Displaying the overall product details.
     * </p>
     *
     * @return       - It returns the overall product details. 
     */
    public List<Product> getProduct() throws ShopException {
        return productDAO.getProduct();
    }

    /**
     * <p>
     * check product id does exist..
     * </p>
     *
     * @param productId - product Id to check.
     * @returns          - if equals returns true or else returns false.
     */
    public Product getProductByProductId(int productId) throws ShopException {
        Product product = productDAO.getProductByProductId(productId);
        if (product == null) {
            throw new ShopException("Invalid product id");
        }
        return product;
    }
}






















