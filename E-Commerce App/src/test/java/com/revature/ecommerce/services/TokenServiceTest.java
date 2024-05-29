package com.revature.ecommerce.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.util.Properties;

import com.revature.eCommerce.dao.CartDao;
import com.revature.eCommerce.dto.responses.Principal;
import com.revature.eCommerce.models.Role;
import com.revature.eCommerce.services.CartService;
import com.revature.eCommerce.services.ProductsService;
import com.revature.eCommerce.services.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.io.*;

import java.util.Date;

@SuppressWarnings("deprecation")
public class TokenServiceTest {


    @Mock
    private InputStream inputStream;

    @Mock
    private Properties properties;

    @InjectMocks
    private TokenService tokenService;

    @Before
    public void setup() throws IOException {
        MockitoAnnotations.initMocks(this);


    }

            @Test
            public void TokenServiceTestGenerateToken_successfulGeneration() {
                // Mock data for Principal
                Role role = new Role("1", "ROLE_ADMIN");
                Principal principal = new Principal("userId123", "john.doe@example.com", role);



                String token = tokenService.generateToken(principal);

                // Verify token generation
                assertNotNull(token);
            }

            @Test
            public void TokenServiceTestParseToken() {

                Role role = new Role("1", "ROLE_ADMIN");
                Principal principal = new Principal("userId123", "john", role);
                String token = tokenService.generateToken(principal);
                Principal principalT = tokenService.parseToken(token);

                assertEquals(principalT.getUserID(), "userId123");
                assertEquals(principalT.getName(), "john");
                assertEquals(principalT.getRole().getID(), "1");
                assertEquals(principalT.getRole().getRoleName(), "ROLE_ADMIN");
            }
}
