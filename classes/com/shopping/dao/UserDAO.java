package com.shopping.dao;

import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.shopping.model.User;
import com.shopping.databaseconnection.DatabaseConnection;
import com.shopping.exception.ShopException;
import org.hibernate.HibernateException;

/**
 * <p> 
 * Performing the various managing operations of users like addition and deletion.
 * </p>
 *
 * @author Tharun
 * @date   19/06/2019
 */
public class UserDAO {
 	
    static final Logger logger = Logger.getLogger(UserDAO.class);  
 
   /** 
    * <p>
    * Creating the new users.
    * </p>
    *
    * @param user - It is the Object of user. 
    */
    public void createUser(User user) throws ShopException {
        Session session = null;
        try{ 
           SessionFactory factory = DatabaseConnection.getInstance();
           session = factory.openSession();    
           Transaction transaction = session.beginTransaction(); 
           session.save(user);
           transaction.commit(); 
           logger.info("User : " + user.getName() + " successfully created"); 
        } catch (HibernateException e) {
           logger.error("Exception at adding user : " + user.getName(), e); 
           throw new ShopException("Failed to create user");
        } finally {
           session.close();
        }
    }

   
   /** 
    * <p>
    * Displaying the overall user.
    * </p>
    *
    * @return - It returns the user details. 
    */
    public List<User> getUser() throws ShopException {
        List<User> userDetail = new ArrayList<User>();
        Session session = null;        
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            userDetail = session.createQuery("from User").list();
             logger.info("Successfully obtained overall user details");
        } catch (HibernateException e) {
           logger.error("Exception at getting overall users" , e); 
           throw new ShopException("Failed to get overall users");
        } finally {
           session.close();
        }
         return userDetail;
    }

   /** 
    * <p>
    * Getting the details of a user by their id.
    * </p>
    *
    * @param userid - User id of an user.
    * @return       - It returns the user details. 
    */
    public User getUserById(int userId) throws ShopException {
        Session session = null;
        User user = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            user = (User) session.get(User.class, userId);  
            logger.info("Successfully obtained user id : " + userId + " detail");
        } catch (HibernateException e) {
           logger.error("Exception at getting user id : " + userId + " detail", e); 
           throw new ShopException("Exception occured at getting user id : " + userId + " detail");
        } finally {
           session.close();
        }
        return user;
    }

   /** 
    * <p>
    * Getting the overall cart for a user.
    * </p>
    *
    * @param userid - User id to get their cart.
    * @return - It returns the cart details. 
    */
    public List<User> getUserByUserName(String userName) throws ShopException {
        Session session = null;
        List<User> userDetail = new ArrayList<User>();
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Query query = session.createQuery("from User where USERNAME = :userName");
            query.setParameter("userName", userName);
            userDetail = query.list();
            return userDetail;
        } catch (HibernateException e) {
            System.out.println(e); 
            throw new ShopException("Exception occurred at getting user detail by user Name");
        } finally {
           session.close();
        }
    }

    
   /** 
    * <p>
    * Updating the existing user.
    * </p>
    *
    * @param user - It is the Object of user.
    */
    public void updateUser(User user) throws ShopException {
       Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(user);
            transaction.commit();
            logger.info("User name : " + user.getName() + " successfully updated");
        } catch (HibernateException e) {
            logger.error("Exception at updating user id : " + user.getId(), e);  
            throw new ShopException("Failed to update User name : " + user.getName());
        } finally {
            session.close();
        }
    }

   /** 
    * <p>
    * Deleting the existing user
    * </p>
    *
    * @param user - user to get deleted. 
    */
    public void removeUser(User user) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(user);
            transaction.commit();
            logger.info("User id : " + user.getId() + " successfully deleted");
        } catch (HibernateException e) {
            logger.error("Exception at deleting user id : " + user.getId(), e); 
            throw new ShopException("Exception occured at deleting user details");
        } finally {
            session.close();
        }
    }
}

  

               

