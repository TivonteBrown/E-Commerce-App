package com.revature.eCommerce.Controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.eCommerce.dto.request.NewCartDelete;
import com.revature.eCommerce.dto.request.NewCartRequest;
import com.revature.eCommerce.dto.responses.Principal;
import com.revature.eCommerce.models.Cart;
import com.revature.eCommerce.models.OrderHistory;
import com.revature.eCommerce.services.CartService;
import com.revature.eCommerce.services.OrderHistoryService;
import com.revature.eCommerce.services.TokenService;

import io.javalin.http.Context;


public class OrderHistoryController {

        //get("/myOrderHistory", cartController::myOrderHistory);
        //get("/allOrders", cartController::allOrders);

        private final OrderHistoryService orderHistoryService;
        private final TokenService tokenService;



        public OrderHistoryController(OrderHistoryService orderHistoryService, TokenService tokenService) {
            this.orderHistoryService = orderHistoryService;
            this.tokenService = tokenService;
        }

        public void myOrderHistory(Context ctx){

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

                List<OrderHistory> orderHistoryLook = orderHistoryService.findOrderHistoryByID(principal.getUserID());


                ctx.json(orderHistoryLook);
                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }

        public void allOrders(Context ctx){

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


                List<OrderHistory> orderHistoryLook = orderHistoryService.findOrderHistory();


                ctx.json(orderHistoryLook);
                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }



}
