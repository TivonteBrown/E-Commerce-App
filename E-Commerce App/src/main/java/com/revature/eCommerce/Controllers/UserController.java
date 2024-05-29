package com.revature.eCommerce.Controllers;

import com.revature.eCommerce.dto.request.NewLoginRequest;
import com.revature.eCommerce.dto.request.NewRegisterRequest;
import com.revature.eCommerce.services.UserService;
import com.revature.eCommerce.models.User;
import com.revature.eCommerce.models.Role;
import com.revature.eCommerce.dto.responses.Principal;
import io.javalin.http.Context;
import java.util.*;

import com.revature.eCommerce.services.TokenService;
import java.util.Optional;

    public class UserController {
        private final UserService userService;
        private final TokenService tokenService;


    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }


    public void register(Context ctx){
        try{
            Map<String, String> errors = new HashMap<>();

            NewRegisterRequest req = ctx.bodyAsClass(NewRegisterRequest.class);

            if (!userService.isValidUsername(req.getName())){
                ctx.status(400); //Bad request
                return;
            }

            if (!userService.isUniqueUsername(req.getName())){
                ctx.status(409); //Conflict
                return;
            }

            if (!userService.isUniqueEmail(req.getEmail())){
                ctx.status(409); //Conflict
                return;
            }

            if (!userService.isValidPassword(req.getPassword())){
                ctx.status(400); //Bad request
                return;
            }

            //Save User
            User newUser = new User(req);
            newUser = userService.save(newUser);

            ctx.status(201); //Created

        } catch (Exception e){
            ctx.status(500); //Internal Error
            e.printStackTrace();
        }
    }

    public void login(Context ctx){
        try{
            Map<String, String> errors = new HashMap<>();

            NewLoginRequest req = ctx.bodyAsClass(NewLoginRequest.class);

            Optional<User> loginUser = userService.login(req.getName(), req.getPassword());
            if (loginUser.isEmpty()){
                errors.put("Error", "Invalid username or password");
                ctx.json(errors);
                ctx.status(400); // Bad request
                return;
            }

            //Sends user info back to postman
            User foundUser = loginUser.get();
            Principal principal = new Principal (foundUser);

            String token = tokenService.generateToken(principal);

            ctx.header("auth-token", token);
            ctx.json(principal);
            ctx.status(200); //ok

        } catch (Exception e){
            ctx.status(500); //Internal Error
            e.printStackTrace();
        }
    }

}
