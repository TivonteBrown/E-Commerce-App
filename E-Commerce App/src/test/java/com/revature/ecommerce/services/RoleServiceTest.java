package com.revature.ecommerce.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.eCommerce.dao.RoleDao;
import com.revature.eCommerce.models.Role;
import com.revature.eCommerce.services.RoleService;


public class RoleServiceTest {

    @Mock
    private RoleDao roleDao;

    @InjectMocks
    private RoleService roleService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


        @Test
        public void RoleServiceTestGetRoleIdByName() {
            // Arrange
            String roleName = "DEFAULT";
            String roleId = "1";
            Role adminRole = new Role();
            adminRole.setID(roleId);
            adminRole.setRoleName(roleName);
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);

            when(roleDao.findAll()).thenReturn(roles);

            // Act
            String result = roleService.getRoleIDByName("DEFAULT");

            // Assert
            assertEquals(roleId, result);
        }

        @Test
        public void RoleServiceTestGetRoleIdNull() {
            // Arrange
            String roleName = "DEFAULT";
            String roleId = "1";
            Role adminRole = new Role();
            adminRole.setID(roleId);
            adminRole.setRoleName(roleName);
            List<Role> roles = new ArrayList<>();
            roles.add(adminRole);

            when(roleDao.findAll()).thenReturn(roles);

            // Act
            String result = roleService.getRoleIDByName("DEFAULT");

            // Assert
            assertNotNull(result); // Check that the result is not null
        }

}