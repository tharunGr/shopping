package com.shopping.service;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import com.shopping.utils.DateUtil;
import com.shopping.utils.RoleServiceUtil;
import com.shopping.dao.RoleDAO;
import com.shopping.dao.UserDAO;
import com.shopping.model.User;
import com.shopping.model.Role;
import com.shopping.validation.Validation;
import com.shopping.exception.ShopException;
import com.shopping.encryption.PasswordEncrypt;

/**
 * <p> 
 * Performing user module bussiness logics by getting informations from users 
 * and modify the details.
 * </p>
 *
 * @author Tharun
 * @date   19/06/2019
 */
public class UserService {

    private UserDAO userDAO = new UserDAO();
    
    /** 
     * <p>
     * Creating the new users from user input.
     * </p>
     *
     * @param user       - It is the Object of user. 
     * @param rolesId    - It has roles id to add it with user. 
     */
    public void createUser(User user, String password, String[] rolesId) throws ShopException {
        user.setPassword(PasswordEncrypt.getEncryptedPassword(password));
        user.setStatus(Boolean.TRUE);
        user.setCreatedDate(DateUtil.getDate());
        user.setUpdatedDate(DateUtil.getDate());
        user.setUserRoles(addRole(rolesId));
        userDAO.createUser(user);
    }
   
   /** 
     * <p>
     * Displaying the overall user details.
     * </p>
     *
     * @return - It returns the user details. 
     */
    public List<User> getUser() throws ShopException {
        return userDAO.getUser();
    }

    /** 
     * <p>
     * Deleting the User from overall User details
     * </p>
     *
     * @param userid - It has user id to get deleted.
     */
    public void deleteUser(int userId) throws ShopException {
        User user = getUserById(userId);
        user.setStatus(Boolean.FALSE);
        userDAO.removeUser(user);
    }

    /** 
     * <p>
     * Updating the specific user details of the users.
     * </p>
     *
     * @param user       - It is the Object of user.
     * @param date       - user Created date
     */
   public void updateUser(User user, String date) throws ShopException {
        user.setCreatedDate(DateUtil.getCorrectedDate(date));
        user.setUpdatedDate(DateUtil.getDate());
        userDAO.updateUser(user);   
    }

    /**
     * <p>
     * Setting the user password
     * </p>
     *
     * @param input - It is the user password input
     * @return      - It returns the valid password.
     */
    public String passwordValidation(String input) throws ShopException {
        Validation.getPassword(input);
        return input;
    }

    /**
     * <p>
     * Setting the user email
     * </p>
     *
     * @param input - It is the user email input
     * @return      - It returns the valid email.
     */
    public String EmailValidation(String input) throws ShopException {
        Validation.getEmail(input);
        return input;
    }
  
    /**
     * <p>
     * Setting the user mobile number
     * </p>
     *
     * @param input - It is the user mobile number input
     * @return      - It returns the valid mobile number.
     */
    public String MobileNumberValidation(String input) throws ShopException {
        Validation.getMobileNumber(input);
        return input;
    }

    /**
     * <p>
     * Getting the user object by user name
     * </p>
     *
     * @param userName - user name to get user details
     * @return         - It returns user details
     */
    public User getUserByUserName(String userName) throws ShopException {
        List<User> userDetail = new ArrayList<User>(); 
        userDetail = userDAO.getUserByUserName(userName);
        User user = null;
        if (0 < userDetail.size()) {
            user = userDetail.get(0);
        }
        return user;
    }


    /**
     * <p>
     * check user id does exist before assigning that user id..
     * </p>
     *
     * @param userId   - user Id to check.
     * @return         - if equals returns true or else returns false.
     */
    public User getUserById(int userId) throws ShopException {
        User user = userDAO.getUserById(userId);
        if (user == null) {
            throw new ShopException("User id is invalid");
        }
        return user;
    }

    /**
     * <p>
     * Adding role id to user
     * </p>
     *
     * @param rolesId    - It contains roles Id.
     * @return           - It returns set of role id for a user.
     */
    public Set<Role> addRole(String[] rolesId) throws ShopException {
        Set<Role> userRoles = new HashSet<Role>();
        for (String id : rolesId) {
            Role role = RoleServiceUtil.getInstance().getRoleById(Integer.parseInt(id));
            userRoles.add(role);
        }   
        return userRoles;
    } 
}






















