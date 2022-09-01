package com.data.controller;

import com.data.model.Role;
import com.data.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> listRoles(){
        List<Role> roles = roleService.initializeRoles();

        return roles ;
    }
}
