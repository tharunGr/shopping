package com.shopping.utils;

import com.shopping.service.RoleService;

public class RoleServiceUtil {

    private static RoleService roleService;
    private static RoleServiceUtil roleServiceUtil;

    private RoleServiceUtil() {
        roleService = new RoleService();
    }

    public static RoleService getInstance() {
        if (roleServiceUtil == null) {
            roleServiceUtil = new RoleServiceUtil();
        }
        return roleService; 
    }	
}
