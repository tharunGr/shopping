package com.shopping.dao;

import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction; 
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.databaseconnection.DatabaseConnection;
import com.shopping.exception.ShopException;
import org.hibernate.HibernateException;

/**
 * <p> 
 * Performing the various managing operations of products.
 * </p>
 *
 * @author Tharun
 * @date   20/06/2019
 */
public class ProductDAO {
    
    static final Logger logger = Logger.getLogger(ProductDAO.class);

   /** 
    * <p>
    * Creating the new product.
    * </p>
    *
    * @param product - It is the Object of product.  
    */
    public void createProduct(Product product) throws ShopException {
        Session session = null;   
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction();  
            session.save(product);
            transaction.commit();     
        } catch (HibernateException e) {
            logger.error("Exception at adding product : " + product.getName(), e); 
            throw new ShopException("Product creation is not successful");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Updating the existing product.
    * </p>
    *
    * @param product - It is the Object of product.
    */
    public void updateProduct(Product product) throws ShopException {
        Session session = null;         
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(product);
            transaction.commit();  
        } catch (HibernateException e) {
            logger.error("Exception at updating product id : " + product.getId(), e); 
            throw new ShopException("Product updation is not successful");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Deleting the existing products
    * </p>
    *
    * @param product - It is the Object of product. 
    */
    public void deleteProduct(Product product) throws ShopException {
         Session session = null;
         try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            Transaction transaction = session.beginTransaction(); 
            session.update(product);
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("Exception at deleting product id : " + product.getId(), e); 
            throw new ShopException("Product deletion is not successful");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Getting the details of a product.
    * </p>
    *
    * @param productid - It is the prooduct Id of the product. 
    * @return - It returns the product details. 
    */
    public Product getProductByProductId(int productId) throws ShopException {
        Session session = null;
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();     
            Product product = (Product) session.get(Product.class, productId);
            return product; 
        } catch (HibernateException e) {
            logger.error("Exception at getting product id : " + productId + " detail", e); 
            throw new ShopException("Exception occurred at getting product details by product id");
        } finally {
           session.close();
        }
    }

   /** 
    * <p>
    * Displaying the overall product.
    * </p>
    *
    * @return - It returns the product details. 
    */
    public List<Product> getProduct() throws ShopException {
        List<Product> productDetail = new ArrayList<Product>();
        Session session = null;        
        try{ 
            SessionFactory factory = DatabaseConnection.getInstance();
            session = factory.openSession();    
            productDetail = session.createQuery("from Product", Product.class).list();
            return productDetail;
        } catch (HibernateException e) {
           logger.error("Exception at getting overall products" , e); 
           throw new ShopException("Exception occurred at getting overall product details");
        } finally {
           session.close();
        }
    }
}

               

