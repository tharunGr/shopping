package com.shopping;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import com.shopping.model.Order;
import com.shopping.model.Cart;
import com.shopping.model.User;
import com.shopping.model.OrderDetail;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;
import com.shopping.exception.ShopException;

/**
 * <p> 
 * Showing the order details for a user. And user can cancel the order. 
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class OrderController extends HttpServlet {
 
    private OrderService orderService = new OrderService();
    static final Logger logger = Logger.getLogger(OrderController.class);
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
            Order order = new Order(); 
            session = request.getSession(false);
            order.setUser((User)session.getAttribute("user"));
            String[] cartIds = request.getParameterValues("orderDetails");
            orderService.createOrder(cartIds, order); 
            response.sendRedirect("display-order");
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
                case "/display-order":
                    display(request, response);
                    break;
                case "/delete-cart":
                    delete(request, response);
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
     * Deletion of specific cart
     * </p>
     *
     * @param request  - To delete the specific cart
     * @param response - Gets back to the cart view page.
     */
    private void delete(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        logger.info("Details of cart id : " + orderId + "to delete");
        orderService.deleteOrder(orderId);
        response.sendRedirect("display-order");
    }

    /**
     * <p>
     * Displaying the list of users.
     * </p>
     *
     * @param request  - To display list of users.
     * @param response - Gets back to the users view page.
     */
    private void display(HttpServletRequest request, HttpServletResponse response)
                         throws ServletException, IOException, ShopException {
        session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        int userId = orderService.getUserId(user.getId());
        List<Order> orderDetail = orderService.getOrderByUserId(userId); 
        request.setAttribute("view", orderDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/vieworder.jsp");  
        dispatch.forward(request, response);  
    }
}











