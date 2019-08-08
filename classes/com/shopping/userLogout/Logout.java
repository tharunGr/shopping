package com.shopping.userLogout;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * <p> 
 * Showing the order details for a user. And user can cancel the order. 
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class Logout extends HttpServlet {

    static final Logger logger = Logger.getLogger(Logout.class);
    private HttpSession session = null;
    
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
        HttpSession session = request.getSession(false);  
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");  
        dispatcher.forward(request, response); 
    }
}











