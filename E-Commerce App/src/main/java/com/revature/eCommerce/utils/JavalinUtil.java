package com.revature.eCommerce.utils;

import com.revature.eCommerce.services.*;
import com.revature.eCommerce.utils.ConnectionFactory;
import com.revature.eCommerce.dao.*;
import com.revature.eCommerce.models.User;
import com.revature.eCommerce.Controllers.*;

import java.io.IOException;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinUtil {

    public Javalin getJavalin() throws IOException{
        UserController userController = new UserController(getUserService(), new TokenService());
        ProductsController productsController = new ProductsController(new ProductsService(new ProductsDao()), new TokenService());
        CartController cartController = new CartController(new CartService(new CartDao(), new ProductsService(new ProductsDao())), new TokenService(), new OrderHistoryService(new OrderHistoryDao()));
        OrderHistoryController orderHistoryController = new OrderHistoryController(new OrderHistoryService(new OrderHistoryDao()), new TokenService());

        return Javalin.create(config -> {
            config.router.apiBuilder(()-> {
                path("/user", () -> {
                    post("/register", userController::register);
                    get("/login", userController::login);
                });
                    path("/products", () -> {
                    post("/create", productsController::create);
                    patch("/update", productsController::update);
                    delete("/delete", productsController::delete);
                    get("/productsCatalog", productsController::prodoctCatalog);
                });
                path("/cart", () -> {
                    post("/addTo", cartController::addTo);
                    delete("/deleteItem", cartController::deleteItem);
                    get("/cartLook", cartController::cartLook);
                    post("/checkout", cartController::checkout);

                });
                path("/orderHistory", () -> {
                    get("/myOrderHistory", orderHistoryController::myOrderHistory);
                    get("/allOrders", orderHistoryController::allOrders);
                });
            });
        });
    }



    private UserService getUserService(){
        return new UserService(new RoleService(new RoleDao()), new UserDao());
    }



}



