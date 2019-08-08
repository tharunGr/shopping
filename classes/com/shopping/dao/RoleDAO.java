package com.shopping.dao;

import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
 
import com.shopping.model.Role;
import com.shopping.databaseconnection.DatabaseConnection;
import com.shopping.exception.ShopException;
import org.hibernate.HibernateException;

/**
 * <p> 
 * Performing the various managing operations of role.
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class RoleDAO {

    static final Logger logger = Logger.getLogger(RoleDAO.class);

   /** 
    * <p>
    * Creating the new role.
    * </p>
    *
    * @param role - It is the Object of role.  
    */
    public void addRole(Role role) throws ShopException {
        Session session = null;
        try { 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.save(role);
            transaction.commit();  
            logger.info("Role : " + role.getName() + " successfully created");
        } catch (HibernateException e) {
           logger.error("Exception at adding role : " + role.getName(), e); 
           throw new ShopException("Role creation is not successful");
        } finally {
            session.close();
        }
    }
/*
   /** 
    * <p>
    * Deleting the existing role
    * </p>
    *
    * @param roleid - It is the role id to get deleted. 
    */
    public void removeRole(int roleId) throws ShopException {
        Session session = null;
        try { 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            Role role = (Role) session.get(Role.class, roleId);
            role.setStatus(Boolean.FALSE);
            session.update(role);
            transaction.commit();
            logger.info("Role id : " + roleId + " successfully deleted");
        } catch (HibernateException e) {
            logger.error("Exception at deleting role id : " + roleId, e); 
            throw new ShopException("Failed to remove role id : " + roleId);
        } finally {
            session.close();
        }
    }
 
   /** 
    * <p>
    * Updating the existing role.
    * </p>
    *
    * @param role - It is the Object of role.
    */
    public void updateRole(Role role) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(role);
            transaction.commit();
            logger.info("Role name : " + role.getName() + " successfully updated");    
        } catch (HibernateException e) {
            logger.error("Exception at updating role id : " + role.getId(), e); 
            throw new ShopException("Failed to update Role name : " + role.getName());
        } finally {
            session.close();
        }
    }

   /** 
    * <p>
    * Displaying the overall role.
    * </p>
    *
    * @return - It returns the role details. 
    */
    public List<Role> getRole() throws ShopException {
        List<Role> roleDetail = new ArrayList<Role>();
        Session session = null;      
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            roleDetail = session.createQuery("from Role", Role.class).list();
            logger.info("Successfully obtained overall role details");
        } catch (HibernateException e) {
            logger.error("Exception at getting overall roles" , e); 
            throw new ShopException("Failed to get overall roles");
        } finally {
            session.close();
        }
        return roleDetail;
    }

   /** 
    * <p>
    * Getting the details of a role.
    * </p>
    *
    * @param roleId - Getting role details by role id.
    * @return       - It returns the role details. 
    */
    public Role getRoleById(int roleId) throws ShopException {
        Session session = null;
        Role role = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();     
            role = (Role) session.get(Role.class, roleId);
            logger.info("Successfully obtained role id : " + roleId + " detail");
        } catch (HibernateException e) {
            logger.error("Exception at getting role id : " + roleId + " detail", e); 
            throw new ShopException("Exception occured at getting role id : " + roleId + " detail");
        } finally {
            session.close();
        }
        return role;
    }
}        



               

