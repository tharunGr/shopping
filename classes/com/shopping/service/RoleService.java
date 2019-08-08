package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.shopping.utils.DateUtil;
import com.shopping.dao.RoleDAO;
import com.shopping.model.Role;
import com.shopping.exception.ShopException;

/**
 * <p> 
 * Performing role module business logics by managing role information.
 * </p>
 *
 * @author Tharun
 * @date   21/06/2019
 */
public class RoleService {

    private RoleDAO roleDAO = new RoleDAO();

    /** 
     * <p>
     * Creating the new roles stores their details to DAO.
     * </p>
     *
     * @param role       - It is the Object of role.  
     */
    public void createRole(Role role) throws ShopException {
        role.setStatus(Boolean.TRUE);
        role.setCreatedDate(DateUtil.getDate());
        role.setUpdatedDate(DateUtil.getDate());
        roleDAO.addRole(role);
    }
    
    /** 
     * <p>
     * Deleting the Role from overall Role details
     * </p>
     *
     * @param roleid     - It is the roleId to get deleted. 
     */
    public void deleteRole(int roleId) throws ShopException {
        roleDAO.removeRole(roleId);
    }

    /** 
     * <p>
     * Updating the specific role details from overall Roles.
     * </p>
     *
     * @param role - It is the Object of role. 
     * @param date - It is the created date
     */
    public void updateRole(Role role, String date) throws ShopException { 
        role.setCreatedDate(DateUtil.getCorrectedDate(date));
        role.setUpdatedDate(DateUtil.getDate());
        roleDAO.updateRole(role);          
    }

    /** 
     * <p>
     * Displaying the overall Roles.
     * </p>
     *
     * @return  - It returns the role details. 
     */
    public List<Role> getRole() throws ShopException {
        return roleDAO.getRole();         
    }

    /**
     * <p>
     * check role id does exist before assigning that role id to any user.
     * </p>
     *
     * @param roleId   - role Id to check.
     * @return         - if equals returns true or else returns false.
     */
    public Role getRoleById(int roleId) throws ShopException {
        Role role = roleDAO.getRoleById(roleId);
        if (role == null) {
            throw new ShopException("Role id is not invalid");
        }
        return role;
    }
}

























