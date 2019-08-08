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

import com.shopping.model.Cart;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.service.UserService;
import com.shopping.utils.ProductServiceUtil;
import com.shopping.utils.CartServiceUtil;
import com.shopping.exception.ShopException;
import org.apache.log4j.Logger;

/**
 * <p> 
 * Managing the cart details by creating new products and update or 
 * delete the existing cart items. 
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class CartController extends HttpServlet {

    static final Logger logger = Logger.getLogger(CartController.class);
    private HttpSession session = null;

    /**
     * <p>
     * Creating the cart details
     * </p>
     *
     * @param request  - From client to create the new cart.
     * @param response - It displays the cart view page
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
        try {
            Cart cart = new Cart();
            cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            session = request.getSession(false);
            cart.setUser((User)session.getAttribute("user"));
            int productId = Integer.parseInt(request.getParameter("productId"));
            CartServiceUtil.getInstance().createCart(cart, productId); 
            response.sendRedirect("display-cart");
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
		        case "/create-cart":
                    create(request, response);
                    break;
                case "/display-cart":
                    display(request, response);
                    break;
                case "/delete-cart":
                    delete(request, response);
                    break;
                case "/show-update-cart-form":
                    showUpdateForm(request, response);
                    break;
                case "/update-cart":
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
     * Creating the cart
     * </p>
     *
     * @param request  - To create a cart.
     * @param response - Gets back to the cart create page.
     */
    private void create(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException, ShopException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        request.setAttribute("productId", productId); 
        RequestDispatcher dispatch = request.getRequestDispatcher("/createcart.jsp");  
        dispatch.forward(request, response);  
    }
    /**
     * <p>
     * Displaying the list of cart.
     * </p>
     *
     * @param request  - To display list of cart.
     * @param response - Gets back to the cart view page.
     */
    private void display(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException, ShopException {
        session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        int userId = CartServiceUtil.getInstance().getUserId(user.getId());
        List<Cart> cartDetail = CartServiceUtil.getInstance().getCartByUserId(userId); 
        request.setAttribute("view", cartDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/viewcart.jsp");    
        dispatch.forward(request, response);  
    }
   
    /**
     * <p>
     * Deletion of specific cart
     * </p>
     *
     * @param request  - To delete the specific cart
     * @param response - Gets back to the cart view page.
     */
    private void delete(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        logger.info("Details of cart id : " + cartId + "to delete");
        CartServiceUtil.getInstance().deleteCart(cartId);
        response.sendRedirect("display-cart");
    }

    
    /**
     * <p>
     * To get user details to update
     * </p>
     *
     * @param request  - To update the user details
     * @param response - Passes to update form page. .
     */
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int cartId = Integer.parseInt(request.getParameter("cartId"));
        Cart cart = CartServiceUtil.getInstance().getCartByCartId(cartId);
        request.setAttribute("update", cart);
        RequestDispatcher dispatch = request.getRequestDispatcher("/updatecart.jsp");  
        dispatch.forward(request, response); 
    }
 
    /**
     * <p>
     * Updation of user details
     * </p>
     *
     * @param request  - To update the user details
     * @param response - Gets back to the users view page.
     */
    private void update(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException, ShopException {
        Cart cart = new Cart();
        cart.setId(Integer.parseInt(request.getParameter("cartId")));
        cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        session = request.getSession(false);
        cart.setUser((User)session.getAttribute("user")); 
        int productId = Integer.parseInt(request.getParameter("productId")); 
        String date = request.getParameter("createdDate");
        logger.info("Details of cart id : " + cart.getId() + "to update");
        CartServiceUtil.getInstance().updateCart(cart, productId, date);
        response.sendRedirect("display-cart");
    }
}


            















