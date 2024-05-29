package com.revature.eCommerce.Controllers;

import com.revature.eCommerce.services.ProductsService;
import com.revature.eCommerce.services.TokenService;
import com.revature.eCommerce.models.Products;

import com.revature.eCommerce.dto.request.NewLoginRequest;
import com.revature.eCommerce.dto.request.NewProductsDelete;
import com.revature.eCommerce.dto.request.NewProductsRequest;
import com.revature.eCommerce.dto.request.NewProductsUpdate;
import com.revature.eCommerce.dto.request.NewRegisterRequest;
import com.revature.eCommerce.dto.responses.Principal;
import com.revature.eCommerce.services.UserService;

import io.javalin.http.Context;
import java.util.*;

public class ProductsController {
    private final ProductsService productsService;
    private final TokenService tokenService;


    public ProductsController(ProductsService productsService, TokenService tokenService) {
        this.productsService = productsService;
        this.tokenService = tokenService;
    }


        public void create(Context ctx){

            try{
                Map<String, String> errors = new HashMap<>();

                String token = ctx.header("auth-token");
                if (token == null || token.isEmpty()) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                Principal principal = tokenService.parseToken(token);
                if (principal == null) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                if(!principal.getRole().getRoleName().equalsIgnoreCase("admin")){
                    ctx.status(403);
                    errors.put("Error:", "Invalid authorization");
                    ctx.json(errors);
                    return;
                }

                NewProductsRequest req = ctx.bodyAsClass(NewProductsRequest.class);

                Products newProduct = new Products(req);
                newProduct = productsService.save(newProduct);

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }

        }

        public void delete(Context ctx){

            try{
                Map<String, String> errors = new HashMap<>();

                String token = ctx.header("auth-token");
                if (token == null || token.isEmpty()) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                Principal principal = tokenService.parseToken(token);
                if (principal == null) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                if(!principal.getRole().getRoleName().equalsIgnoreCase("admin")){
                    ctx.status(403);
                    errors.put("Error:", "Invalid authorization");
                    ctx.json(errors);
                    return;
                }

                NewProductsDelete req = ctx.bodyAsClass(NewProductsDelete.class);

                if (productsService.getProductbyID(req.getProductID()) == null){
                    ctx.status(401);
                    errors.put("Error:", "Invalid ID.");
                    ctx.json(errors);
                    return;
                }

                Products newProduct = new Products(req);
                newProduct = productsService.delete(newProduct);

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }

        }

        public void update(Context ctx){

            try{
                Map<String, String> errors = new HashMap<>();

                String token = ctx.header("auth-token");
                if (token == null || token.isEmpty()) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                Principal principal = tokenService.parseToken(token);
                if (principal == null) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                if(!principal.getRole().getRoleName().equalsIgnoreCase("admin")){
                    ctx.status(403);
                    errors.put("Error:", "Invalid authorization");
                    ctx.json(errors);
                    return;
                }

                NewProductsUpdate req = ctx.bodyAsClass(NewProductsUpdate.class);

                Products newProduct = new Products(req);

                if (productsService.getProductbyID(req.getProductID()) == null){
                    ctx.status(401);
                    errors.put("Error:", "Invalid ID.");
                    ctx.json(errors);
                    return;
                }

                newProduct = productsService.update(newProduct);

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }


        public void prodoctCatalog(Context ctx){

            try{
                Map<String, String> errors = new HashMap<>();

                String token = ctx.header("auth-token");
                if (token == null || token.isEmpty()) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                Principal principal = tokenService.parseToken(token);
                if (principal == null) {
                    ctx.status(401);
                    errors.put("Error:", "Invalid token.");
                    ctx.json(errors);
                    return;
                }

                List<Products> productCatalog = productsService.getProductsCatalog();

                ctx.json(productCatalog);

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }
    }
