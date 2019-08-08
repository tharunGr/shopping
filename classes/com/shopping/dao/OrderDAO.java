package com.shopping.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.SessionFactory;
import org.apache.log4j.Logger;

import com.shopping.model.Order;
import com.shopping.model.OrderDetail;
import com.shopping.exception.ShopException;
import com.shopping.databaseconnection.DatabaseConnection;
import org.hibernate.HibernateException;

/**
 * <p> 
 * Performing the various managing operations of products in order and send it   
 * back to order services
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class OrderDAO {

    static final Logger logger = Logger.getLogger(OrderDAO.class);

   /** 
    * <p>
    * Creating the new order.
    * </p>
    *
    * @param order     - It is the Object of order.
    */
    public void createOrder(Order order) throws ShopException {
        Session session = null;
        Transaction transaction = null;     
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            transaction = session.beginTransaction();  
            session.save(order);
            transaction.commit();   
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("Exception at adding order" + order.getId(), e); 
            throw new ShopException("order creation is not successful");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Getting the overall order for a user.
    * </p>
    *
    * @param userid - User id to get their order.
    * @return       - It returns the order details. 
    */
    public List<Order> getOrder() throws ShopException {
        Session session = null; 
        List<Order> orders = new ArrayList<Order>();
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();     
            orders = session.createQuery("from Order", Order.class).list();
            return orders;
        } catch (HibernateException e) {
            logger.error("Exception at getting overall order" , e); 
            throw new ShopException("Exception occurred at getting order details by user id");
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
    public List<Order> getOrderByUserId(int userId) throws ShopException {
        Session session = null;
        List<Order> orderDetail = new ArrayList<Order>();
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Query query = session.createQuery("from Order where USER_ID = :userId");
            query.setParameter("userId", userId);
            orderDetail = query.list();
            return orderDetail;
        } catch (HibernateException e) {
            logger.error("Exception at getting user id : " + userId + " order detail", e); 
            throw new ShopException("Exception occurred at getting order details by user id");
        } finally {
           session.close();
        }
    }
   
   /** 
    * <p>
    * Getting the order for a user.
    * </p>
    *
    * @param orderid - Order id to get their order.
    * @return        - It returns the order details. 
    */
    public Order getOrderByOrderId(int orderId) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();     
            Order order = (Order) session.get(Order.class, orderId);
            return order;
        } catch (HibernateException e) {
            logger.error("Exception at getting order id : " + orderId + " detail", e); 
            throw new ShopException("Exception occurred at getting order details by order id");
        } finally {
           session.close();
        }
    }
 
    /** 
    * <p>
    * Deleting the existing order
    * </p>
    *
    * @param order     - It is the Object of order. 
    */
    public void removeOrder(Order order) throws ShopException {
        Session session = null;     
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction();  
            session.update(order);
            transaction.commit();  
        } catch (HibernateException e) {
            logger.error("Exception at deleting order id : " + order.getId(), e); 
            throw new ShopException("order deletion is not successful");
        } finally {
           session.close();
        }
    }
}















