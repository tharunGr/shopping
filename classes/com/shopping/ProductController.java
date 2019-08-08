package com.shopping;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.utils.ProductServiceUtil;
import com.shopping.exception.ShopException;
import org.apache.log4j.Logger;

/**
 * <p> 
 * Managing the product details here by creating new products and update or 
 * delete the existing products.
 * </p>
 *
 * @author Tharun
 * @date   20/06/2019
 */
public class ProductController extends HttpServlet {

    static final Logger logger = Logger.getLogger(ProductController.class); 
    private HttpSession session = null;

   /**
     * <p>
     * Creating the product details
     * </p>
     *
     * @param request  - From client to create the new products.
     * @param response - It displays the product view page
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
        try {
            Product product = new Product();
            product.setName(request.getParameter("name"));
            product.setDescription(request.getParameter("description"));
            product.setPrice(Integer.parseInt(request.getParameter("price")));
            session = request.getSession(false);
            product.setUser((User)session.getAttribute("user")); 
            logger.info("Details of product name : " + product.getName() + "to add");
            ProductServiceUtil.getInstance().createProduct(product); 
            response.sendRedirect("display-product");
        } catch (ShopException e) {
            request.setAttribute("error", e);
            RequestDispatcher dispatch = request.getRequestDispatcher("errorpage.jsp");  
            dispatch.forward(request, response);
        } 
    }

    /**
     * <p>
     * Access the CRUD operations based on URL.
     * </p>
     *
     * @param request  - From client to access specific operation.
     * @param response - It passes to another operation.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            switch (action) {
                case "/display-product":
                    display(request, response);
                    break;
                case "/delete-product":
                    delete(request, response);
                    break;
                case "/show-update-product-form":
                    showUpdateForm(request, response);
                    break;
                case "/update-product":
                    update(request, response);
                    break;
            }
        } catch (ShopException e) { 
            request.setAttribute("error", e);
            RequestDispatcher dispatch = request.getRequestDispatcher("errorpage.jsp");  
            dispatch.forward(request, response);
        } 
    }

    /**
     * <p>
     * Displaying the list of products.
     * </p>
     *
     * @param request  - To display list of products.
     * @param response - Gets back to the products view page.
     */
    private void display(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ShopException {
        List<Product> productDetail = ProductServiceUtil.getInstance().getProduct(); 
        request.setAttribute("view", productDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/viewproduct.jsp");  
        dispatch.forward(request, response);  
    }

    /**
     * <p>
     * Deletion of specific users
     * </p>
     *
     * @param request  - To delete the specific product
     * @param response - Gets back to the products view page.
     */
    private void delete(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        logger.info("Details of product id : " + productId + "to delete");
        ProductServiceUtil.getInstance().deleteProduct(productId);
        response.sendRedirect("display-product");
    }

    
    /**
     * <p>
     * To get user details to update
     * </p>
     *
     * @param request  - To update the product details
     * @param response - Passes to update form page. .
     */
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = ProductServiceUtil.getInstance().getProductByProductId(productId);
        request.setAttribute("update", product);
        RequestDispatcher dispatch = request.getRequestDispatcher("/updateproduct.jsp");  
        dispatch.forward(request, response); 
    }
 
    /**
     * <p>
     * Updation of user details
     * </p>
     *
     * @param request  - To update the product details
     * @param response - Gets back to the products view page.
     */
    private void update(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, ShopException {
        Product product = new Product();
        product.setId(Integer.parseInt(request.getParameter("productId")));
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("password"));
        product.setPrice(Integer.parseInt(request.getParameter("price")));
        session = request.getSession(false);
        product.setUser((User)session.getAttribute("user")); 
        product.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        String date = request.getParameter("createdDate");
        logger.info("Details of product name : " + product.getName() + "to update");
        ProductServiceUtil.getInstance().updateProduct(product, date);
        response.sendRedirect("display-product");
    }
}
   














