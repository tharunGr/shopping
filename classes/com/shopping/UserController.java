package com.shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.utils.RoleServiceUtil;
import com.shopping.model.User;
import com.shopping.model.Role;
import com.shopping.service.UserService;
import com.shopping.utils.DateUtil;
import com.shopping.exception.ShopException;
import org.apache.log4j.Logger;

/**
 * <p> 
 * Managing the user details by creating new users and update or delete 
 * the existing users. 
 * </p>
 *
 * @author Tharun
 * @date   19/06/2019
 */
public class UserController extends HttpServlet {

    private UserService userService = new UserService();
    static final Logger logger = Logger.getLogger(UserController.class); 
    
    /**
     * <p>
     * Creating the user details
     * </p>
     *
     * @param request  - From client to create the new users.
     * @param response - It displays the user view page
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
        try {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setUserName(request.getParameter("userName"));
            String password = userService.passwordValidation(request.getParameter("password"));
            user.setEmail(userService.EmailValidation(request.getParameter("email")));
            user.setMobileNumber(userService.MobileNumberValidation(request.getParameter("mobilenumber")));
            user.setAddress(request.getParameter("address"));
            String[] rolesId = request.getParameterValues("roleDetails");  
            logger.info("Details of user name : " + user.getName() + "to add");
            userService.createUser(user, password, rolesId);
            response.sendRedirect("display-user");
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
                case "/create-user":
                    create(request, response);
                    break;
                case "/display-user":
                    display(request, response);
                    break;
                case "/delete-user":
                    delete(request, response);
                    break;
                case "/show-update-user-form":
                    showUpdateForm(request, response);
                    break;
                case "/update-user":
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
     * Creating the users.
     * </p>
     *
     * @param request  - To display list of users.
     * @param response - Gets back to the users view page.
     */
    private void create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ShopException {
        List<Role> roleDetail = RoleServiceUtil.getInstance().getRole();
        request.setAttribute("viewroles", roleDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/createuser.jsp");  
        dispatch.forward(request, response);  
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
        List<User> userDetail = userService.getUser(); 
        request.setAttribute("view", userDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/viewuser.jsp");  
        dispatch.forward(request, response);  
    }

    /**
     * <p>
     * Deletion of specific users
     * </p>
     *
     * @param request  - To delete the specific user
     * @param response - Gets back to the users view page.
     */
    private void delete(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        logger.info("Details of user id : " + userId + "to delete");
        userService.deleteUser(userId);
        response.sendRedirect("display-user");
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
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userService.getUserById(userId);
        request.setAttribute("update", user);
        RequestDispatcher dispatch = request.getRequestDispatcher("/updateuser.jsp");  
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
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("userId")));
        user.setName(request.getParameter("name"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setMobileNumber(request.getParameter("mobilenumber"));
        user.setAddress(request.getParameter("address"));
        String date = request.getParameter("createdDate");
        user.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        logger.info("Details of user name : " + user.getName() + "to update");
        userService.updateUser(user, date);
        response.sendRedirect("display-user");
    }
}

















                    
