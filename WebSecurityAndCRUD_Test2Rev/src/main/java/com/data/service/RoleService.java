package com.data.service;

import com.data.model.Role;
import com.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> initializeRoles(){

        return roleRepository.findAll();
    }
}
