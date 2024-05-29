package com.revature.ecommerce.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.eCommerce.dao.CartDao;
import com.revature.eCommerce.services.CartService;
import com.revature.eCommerce.services.ProductsService;
import com.revature.eCommerce.models.Cart;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartServiceTest {

    @Mock
    private ProductsService productsService;

    @Mock
    private CartDao cartDao;

    @InjectMocks
    private CartService cartService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

        @Test
        public void CartServiceTestSave() {
                // Mock data
                Cart cart = new Cart();
                cart.setProductName("Product1");
                String productID = "123";
                Long productValue = 100L;


                when(productsService.getProductIDByName("Product1")).thenReturn(productID);
                when(productsService.getProductValueByName("Product1")).thenReturn(productValue);


                cartService.save(cart);

                // Verify that the cart object has correct product ID and price
                assertEquals(productID, cart.getProductID());
                assertEquals(productValue.doubleValue(), cart.getPrice(), 0.001);

                // Verify that the cartDao.save() method is called once
                verify(cartDao, times(1)).save(cart);

            }

        @Test
        public void CartServiceTestDeleteAll() {
            String cartID = "123";

            cartService.deleteAll(cartID);

            // Verify that the cartDao.deleteAll() method is called once
            verify(cartDao, times(1)).deleteAll(cartID);

        }

        @Test
        public void CartServiceTestDeleteItem() {
            // Mock data
            Cart cart = new Cart();
            cart.setCartID("123");
            cart.setProductName("Product1");

            Cart result = cartService.deleteItem(cart);

            // Verify that the cartDao.deleteItem() method is called once
            verify(cartDao, times(1)).deleteItem(cart.getCartID(), cart.getProductName());

            // Verify that the method returns the same cart object
            assertEquals(cart, result);

        }

        @Test
        public void CartServiceTestFindCart() {
            String cartID = "123";
            List<Cart> expectedCartList = new ArrayList<>();

            expectedCartList.add(new Cart());


            when(cartDao.findByCartId(cartID)).thenReturn(expectedCartList);


            List<Cart> actualCartList = cartService.findCart(cartID);

            // Verify that the correct list of carts is returned
            assertEquals(expectedCartList, actualCartList);
        }

        @Test
        public void CartServiceTestCalculateTotalPrice() {
            // Mock data
            List<Cart> cartList = new ArrayList<>();
            cartList.add(new Cart("1", "Product1", 2, 100L));
            cartList.add(new Cart("2", "Product2", 3, 50L));

            Long totalPrice = cartService.calculateTotalPrice(cartList);

            // Expected total price calculation: (2 * 100) + (3 * 50) = 200 + 150 = 350
            Long expectedTotalPrice = 350L;

            // Verify that the correct total price is returned
            assertEquals(expectedTotalPrice, totalPrice);
        }


    }
