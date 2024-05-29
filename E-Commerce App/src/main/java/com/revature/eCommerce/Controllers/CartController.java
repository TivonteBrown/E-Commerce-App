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

public class CartController {
    private final CartService cartService;
    private final TokenService tokenService;
    private final OrderHistoryService orderHistoryService;


    public CartController(CartService cartService, TokenService tokenService, OrderHistoryService orderHistoryService) {
        this.cartService = cartService;
        this.tokenService = tokenService;
        this.orderHistoryService = orderHistoryService;
    }


        public void addTo(Context ctx){

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

                //add String productName, int quantity to Cart object

                NewCartRequest req = ctx.bodyAsClass(NewCartRequest.class);

                Cart newItem = new Cart(req);

                ////add String cartID, String buyer,
                newItem.setCartID(principal.getUserID());
                newItem.setBuyer(principal.getName());

                newItem = cartService.save(newItem);

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }

        }

        public void deleteItem(Context ctx){

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


                NewCartDelete req = ctx.bodyAsClass(NewCartDelete.class);
                if (req.getProductName() == null ){
                    ctx.status(401);
                    errors.put("Error:", "Invalid delete request.");
                    ctx.json(errors);
                    return;
                }

                req.setCartID(principal.getUserID());

                Cart newProductDelete = new Cart(req);
                newProductDelete = cartService.deleteItem(newProductDelete);

                ctx.status(200); //ok

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }



        public void cartLook(Context ctx){

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


                List<Cart> cartLook = cartService.findCart(principal.getUserID());

                long totalPrice = cartService.calculateTotalPrice(cartLook);

                Map<String, Object> cartData = new HashMap<>();
                cartData.put("total price in cents", totalPrice);
                cartData.put("cartItems", cartLook);

                ctx.json(cartData);
                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }

        public void checkout(Context ctx){

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


                List<Cart> cartLook = cartService.findCart(principal.getUserID());

                LocalDateTime localDateTime = LocalDateTime.now();

                long totalPrice = cartService.calculateTotalPrice(cartLook);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                timestamp.setNanos(0);


                Map<String, Object> checkoutData = new HashMap<>();
                checkoutData.put("Check Out timestamp", timestamp);
                checkoutData.put("total price in cents", totalPrice);
                checkoutData.put("cartItems", cartLook);

                OrderHistory orderHistory = new OrderHistory();
                orderHistory.setTotalOrderPrice(totalPrice);
                orderHistory.setCartID(principal.getUserID());


                ctx.json(checkoutData);

                orderHistoryService.save(orderHistory);

                cartService.deleteAll(principal.getUserID());

                ctx.status(201); //Created

            } catch (Exception e){
                ctx.status(500); //Internal Error
                e.printStackTrace();
            }
        }

}
