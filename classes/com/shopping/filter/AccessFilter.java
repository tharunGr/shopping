package com.shopping.filter;

import java.util.Arrays;
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

import com.shopping.exception.ShopException;
import com.shopping.service.UserService;
import com.shopping.model.User;
import com.shopping.model.Role;

/**
 * <p>
 * Filter the url based on role of the user.
 * </p>
 *
 * @author Tharun
 * @date   28/07/2019
 */
public class AccessFilter implements Filter {
    
    private List<String> excludedUrls;

    /**
     * <p>
     * Initialization of filter
     * </p>
     *
     * @param filterConfig  - configures the filter.
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludePattern = filterConfig.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePattern.split(","));
    }

    /**
     * <p>
     * Authorization the url based on roles of user.
     * </p>
     *
     * @param request  - Request to check login.
     * @param response - response to shop menu
     * @param chain    - passes to destination.
     */
    public void doFilter(ServletRequest request, ServletResponse response,  
                   FilterChain chain) throws IOException, ServletException { 
        try {   
            HttpServletRequest httprequest = (HttpServletRequest) request;
            HttpServletResponse httpresponse = (HttpServletResponse) response;
            String path = ((HttpServletRequest) request).getServletPath();
            HttpSession session = httprequest.getSession();
            if (!path.equals("/login.jsp")) {           
                String userRole = (String)session.getAttribute("userRole");
                if (userRole.equals("user") && excludedUrls.contains(path)) {
                    throw new ShopException("Access Denied");
                }
                chain.doFilter(request, response); 
            } else {
                if (session.getAttribute("user") != null) {
                    throw new ShopException("Request to Logout");
                }
                chain.doFilter(request, response);
            }        
        } catch (ShopException e) {
            request.setAttribute("error", e);
            RequestDispatcher dispatch = request.getRequestDispatcher("errorpage.jsp");  
            dispatch.forward(request, response);  
        } 
    }
}



















