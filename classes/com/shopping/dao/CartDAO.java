package com.shopping.dao;

import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.shopping.model.Cart;
import com.shopping.exception.ShopException;
import com.shopping.databaseconnection.DatabaseConnection;
import org.hibernate.HibernateException;

/**
 * <p> 
 * Performing the various managing operations of products in cart.
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class CartDAO {

    static final Logger logger = Logger.getLogger(CartDAO.class);

   /** 
    * <p>
    * Creating the new cart.
    * </p>
    *
    * @param cart - It is the Object of cart. 
    */
    public void createCart(Cart cart) throws ShopException {
        Session session = null;       
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction();     
            session.save(cart);
            transaction.commit();   
        } catch (HibernateException e) {
            logger.error("Exception at adding cart " + cart.getId(), e); 
            throw new ShopException("Exception occurred at creation of cart");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Getting the overall cart for a user.
    * </p>
    *
    * @param userid - User id to get their cart.
    * @return - It returns the cart details. 
    */
    public List<Cart> getCart() throws ShopException {
        Session session = null;
        List<Cart> cartDetail = new ArrayList<Cart>();
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            cartDetail = session.createQuery("from Cart", Cart.class).list();
            return cartDetail;
        } catch (HibernateException e) {
            logger.error("Exception at getting overall carts" , e); 
            throw new ShopException("Exception occurred at getting cart details by user id");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Getting the details of a cart.
    * </p>
    *
    * @param cartId - cart id to get cart details.
    * @return       - It returns the cart details. 
    */
    public Cart getCartByCartId(int cartId) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();     
            Cart cart = (Cart) session.get(Cart.class, cartId);
            return cart;
        } catch (HibernateException e) {
            logger.error("Exception at getting cart id : " + cartId + " cart detail", e); 
            throw new ShopException("Exception occurred at getting cart details by cart id");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Updating the existing role.
    * </p>
    *
    * @param cart - It is the Object of cart.
    */
    public void updateCart(Cart cart) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(cart);
            transaction.commit();     
        } catch (HibernateException e) {
            logger.error("Exception at updating cart id : " + cart.getId(), e);  
            throw new ShopException("Exception occurred at cart updation");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Deleting the cart
    * </p>
    *
    * @param cartid - cart id to get deleted. 
    */
    public void deleteCart(int cartId) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            Cart cart = (Cart) session.get(Cart.class, cartId);
            session.delete(cart);
            transaction.commit();    
        } catch (HibernateException e) {
             logger.error("Exception at deleting cart id : " + cartId, e); 
            throw new ShopException("Exception occurred at cart deletion");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Getting the overall cart for a user.
    * </p>
    *
    * @param userid - User id to get their cart.
    * @return - It returns the cart details. 
    */
    public List<Cart> getCartByUserId(int userId) throws ShopException {
        Session session = null;
        List<Cart> cartDetail = new ArrayList<Cart>();
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Query query = session.createQuery("from Cart where USER_ID = :userId");
            query.setParameter("userId", userId);
            cartDetail = query.list();
            return cartDetail;
        } catch (HibernateException e) {
            logger.error("Exception at getting user id : " + userId + " product detail", e);  
            throw new ShopException("Exception occurred at getting cart details by user id");
        } finally {
           session.close();
        }
    }
}

