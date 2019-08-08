package com.shopping.service;

import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CartDAO;
import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.service.UserService;
import com.shopping.utils.ProductServiceUtil;
import com.shopping.exception.ShopException;
import com.shopping.utils.DateUtil;

/**
 * <p> 
 * Performing cart module bussiness logics like adding, comparing and perform 
 * some other operations.
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class CartService {

    private CartDAO cartDAO = new CartDAO();

    /** 
     * <p>
     * Creating the new products in cart.
     * </p>
     *
     * @param cart       - It is the Object of cart. 
     */
    public void createCart(Cart cart, int productId) throws ShopException {
        cart.setProduct(ProductServiceUtil.getInstance().getProductByProductId(productId));
        cart.setCreatedDate(DateUtil.getDate());
        cart.setUpdatedDate(DateUtil.getDate());
        cartDAO.createCart(cart);
    }

    /** 
     * <p>
     * Deleting the cart 
     * </p>
     *
     * @param cartId - It has the cartId to get deleted. 
     */
    public void deleteCart(int cartId) throws ShopException {
        cartDAO.deleteCart(cartId);
    }
  
    /** 
     * <p>
     * Updating the specific product quantity in cart.
     * </p>
     * 
     * @param cart      - It is the Object of cart.
     * @param productId - It is the id of the product in cart.
     * @param date      - cart created date. 
     */
    public void updateCart(Cart cart, int productId, String date) throws ShopException {
       cart.setProduct(ProductServiceUtil.getInstance().getProductByProductId(productId));
       cart.setCreatedDate(DateUtil.getCorrectedDate(date));
       cart.setUpdatedDate(DateUtil.getDate());
       cartDAO.updateCart(cart);
    }
 
    /** 
     * <p>
     * getting the cart by user id.
     * </p>
     *
     * @param userid - User id to get display their cart.
     * @return       - It returns the cart details of a user. 
     */
    public List<Cart> getCart() throws ShopException {
        return cartDAO.getCart();
    }

    /**
     * <p>
     * check cart id does exist..
     * </p>
     *
     * @param cartId - cart Id to check.
     * @return       - if equals returns true or else returns false.
     */
    public Cart getCartByCartId(int cartId) throws ShopException {
        Cart cart = cartDAO.getCartByCartId(cartId);
        if (cart == null) {
            throw new ShopException("Invalid cart id");
        }
        return cart;
    }

    /** 
     * <p>
     * getting the cart by user id.
     * </p>
     *
     * @param userid - User id to get display their cart.
     * @return       - It returns the cart details of a user. 
     */
    public List<Cart> getCartByUserId(int userId) throws ShopException {
        return cartDAO.getCartByUserId(userId);
    }

    /**
     * <p>
     * Getting user id
     * </p>
     *
     * @param userId - It has the user id.
     */
    public int getUserId(int userId) throws ShopException {
        UserService userService = new UserService();
        return userService.getUserById(userId).getId();  
    }
}


