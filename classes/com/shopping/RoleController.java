package com.shopping;

import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.shopping.utils.RoleServiceUtil;
import com.shopping.model.Role;
import com.shopping.exception.ShopException;
import com.shopping.utils.DateUtil;

public class RoleController extends HttpServlet {
 
    static final Logger logger = Logger.getLogger(RoleController.class);  

    /**
     * <p>
     * Creating the role details
     * </p>
     *
     * @param request  - From client to create the new roles.
     * @param response - It displays the role view page
     */ 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
                                          throws ServletException, IOException {
        try {
            Role role = new Role();
            role.setName(request.getParameter("name"));
            role.setDescription(request.getParameter("description"));
            logger.info("Details of role name : " + role.getName() + "to add");
            RoleServiceUtil.getInstance().createRole(role);
            response.sendRedirect("display-role");
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
                case "/display-role":
                    display(request, response);
                    break;
                case "/delete-role":
                    delete(request, response);
                    break;
                 case "/show-update-role-form":
                    showUpdateForm(request, response);
                    break;
                 case "/update-role":
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
     * Displaying the list of roles.
     * </p>
     *
     * @param request  - To display list of roles.
     * @param response - Gets back to the roles view page.
     */
    private void display(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        List<Role> roleDetail = RoleServiceUtil.getInstance().getRole(); 
        request.setAttribute("view", roleDetail);
        RequestDispatcher dispatch = request.getRequestDispatcher("/viewrole.jsp");  
        dispatch.forward(request, response);  
    }

    /**
     * <p>
     * Deletion of specific roles
     * </p>
     *
     * @param request  - To delete the specific role
     * @param response - Gets back to the roles view page.
     */
    private void delete(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        logger.info("Details of role id : " + roleId + "to delete");
        RoleServiceUtil.getInstance().deleteRole(roleId);
        response.sendRedirect("display-role");
    }
   
    /**
     * <p>
     * To get role details to update
     * </p>
     *
     * @param request  - To update th role details
     * @param response - Passes to update form page. .
     */
    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        Role role = RoleServiceUtil.getInstance().getRoleById(roleId);
        request.setAttribute("update", role);
        RequestDispatcher dispatch = request.getRequestDispatcher("/updaterole.jsp");  
        dispatch.forward(request, response); 
    }
 
    /**
     * <p>
     * Updation of role details
     * </p>
     *
     * @param request  - To update the role details
     * @param response - Gets back to the roles view page.
     */
    private void update(HttpServletRequest request, HttpServletResponse response)
                           throws ServletException, IOException, ShopException {
        Role role = new Role();
        role.setId(Integer.parseInt(request.getParameter("roleId")));
        role.setName(request.getParameter("name"));
        role.setDescription(request.getParameter("description"));
        role.setStatus(Boolean.parseBoolean(request.getParameter("status")));
        String date = request.getParameter("createdDate");
        logger.info("Details of role name : " + role.getName() + "to update");
        RoleServiceUtil.getInstance().updateRole(role, date);
        response.sendRedirect("display-role");
    }
}

        

