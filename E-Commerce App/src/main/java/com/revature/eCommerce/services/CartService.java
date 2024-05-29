package com.revature.eCommerce.services;

import com.revature.eCommerce.models.Cart;
import com.revature.eCommerce.models.User;

import java.util.List;
import java.util.Optional;


import com.revature.eCommerce.dao.CartDao;
import com.revature.eCommerce.services.ProductsService;

public class CartService {
    private final CartDao cartDao;
    private final ProductsService productsService;


    public CartService(CartDao cartDao, ProductsService productsService) {
        this.cartDao = cartDao;
        this.productsService = productsService;
    }

        public Cart save(Cart cart) {
            //add product id and price to cart object
            //find productId and price using productName
            String productID = productsService.getProductIDByName(cart.getProductName());
            Long productValue = productsService.getProductValueByName(cart.getProductName());

            cart.setProductID(productID);
            cart.setPrice(productValue);

            cartDao.save(cart);
            return cart;
        }

        public void deleteAll(String cartID) {
            cartDao.deleteAll(cartID);

        }

        public Cart deleteItem(Cart cart) {
            cartDao.deleteItem(cart.getCartID(), cart.getProductName());
            return cart;
        }

        public List<Cart> findCart(String cartID){
            return cartDao.findByCartId(cartID);
        }

        public Long calculateTotalPrice(List<Cart> cart){
            Long totalPrice = 0L;
            // Iterate through each cart in the list
            for (Cart c : cart) {
                Long itemValue = c.getPrice() * c.getQuantity();
              totalPrice += itemValue;
            }
            return totalPrice;
        }

        public Cart setItemPrice(Cart cart){
            Long itemPrice = cart.getPrice() * cart.getQuantity();
            cart.setitemPrice(itemPrice);
            return cart;
        }

}
