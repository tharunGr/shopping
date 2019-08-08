package com.shopping.filter;

import java.util.ArrayList;
import java.util.List; 
import java.util.HashSet;
import java.util.Set;
import java.io.PrintWriter;  
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.service.UserService;
import com.shopping.model.User;
import com.shopping.model.Role;
import com.shopping.exception.ShopException;
import com.shopping.encryption.PasswordEncrypt;

/**
 * <p>
 * Filter the url based on user name and user password.
 * </p>
 *
 * @author Tharun
 * @date   28/07/2019
 */
public class LoginFilter implements Filter {
    
    /**
     * <p>
     * Authenticating the user login
     * </p>
     *
     * @param request  - Request to check login.
     * @param response - response to shop menu
     * @param chain    - passes to destination.
     */
    public void doFilter(ServletRequest request, ServletResponse response,  
                   FilterChain chain) throws IOException, ServletException {
            doPostFilter(request, response, chain);
    }

    /**
     * <p>
     * Authenticating the user login
     * </p>
     *
     * @param request  - Request to check login.
     * @param response - response to shop menu
     * @param chain    - passes to destination.
     */
    public void doPostFilter(ServletRequest request, ServletResponse response,  
                   FilterChain chain) throws IOException, ServletException {
        HttpSession session = null;
        try {   
            HttpServletRequest httprequest = (HttpServletRequest) request;
		    HttpServletResponse httpresponse = (HttpServletResponse) response;
            boolean isInvalidUser = false;
            boolean isInvalidAdminRole = false; 
            UserService userService = new UserService();
            User user = userService.getUserByUserName(request.getParameter("userName"));
            String password = PasswordEncrypt.getEncryptedPassword(request.getParameter("password"));
            if ((user != null) && 
                (user.getPassword().equals(password))) { 
                session = httprequest.getSession();  
                session.setAttribute("user", user);  
                isInvalidUser = true;
                Set<Role> userRole = user.getUserRoles();
                for (Role role : userRole) {
                    if (role.getName().equals("admin_role")) { 
                        session.setAttribute("roles","admin");     
                        session.setAttribute("userRole", "admin"); 
                        chain.doFilter(request, response);
                        isInvalidAdminRole = true;
                    }
                }                                 
            } 
            if ((isInvalidUser) && (!isInvalidAdminRole)) {
                session.setAttribute("userRole", "user");
                session.setAttribute("roles","user");    
                chain.doFilter(request, response); 
            }
            if (!isInvalidUser) {
                throw new ShopException("Invalid user name and password please try again!");   
            }
        } catch (ShopException e) {
            request.setAttribute("error", e);
            RequestDispatcher dispatch = request.getRequestDispatcher("errorpage.jsp");  
            dispatch.forward(request, response);  
        }  
    }
}   



















