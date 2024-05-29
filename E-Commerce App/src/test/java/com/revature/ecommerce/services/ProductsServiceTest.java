package com.revature.ecommerce.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.eCommerce.dao.ProductsDao;
import com.revature.eCommerce.services.ProductsService;
import com.revature.eCommerce.models.Products;

public class ProductsServiceTest {

    @Mock
    private ProductsDao productsDao;

    @InjectMocks
    private ProductsService productsService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

        @Test
        public void ProductsServiceReturnTest() {
       // Create a product
        Products product = new Products();
        product.setProductID("1");
        product.setProductName("Test Product");


        Products savedProduct = productsService.save(product);

        // Verify that the returned product is the same as the one passed to save method
        assertEquals(product, savedProduct);
    }

        @Test
        public void ProductsServiceTestDeleteProduct() {
                // Create a product
                Products product = new Products();
                product.setProductID("1");

                doNothing().when(productsDao).delete(product.getProductID());

                productsService.delete(product);

                // Verify that the delete method was called
                verify(productsDao).delete(product.getProductID());
            }

            @Test
            public void ProductsServiceTestUpdateProduct() {
                    // Create a product
                    Products product = new Products();
                    product.setProductID("1");

                    doNothing().when(productsDao).update(product);

                    productsService.update(product);

                    // Verify that the delete method was called
                    verify(productsDao).update(product);
                }

            @Test
            public void ProductsServiceTestGetProductIDByName() {
                //  test products
                Products product1 = new Products();
                product1.setProductID("1");
                product1.setProductName("Test Product 1");

                Products product2 = new Products();
                product2.setProductID("2");
                product2.setProductName("Test Product 2");


                List<Products> productList = new ArrayList<>();
                productList.add(product1);
                productList.add(product2);

                when(productsDao.findAll()).thenReturn(productList);

                String productId = productsService.getProductIDByName("Test Product 1");

                // Verify that the correct product ID is returned
                assertEquals("1", productId);
            }

            @Test
            public void testGetProductIDByName_NotFound() {
                //  test products
                Products product1 = new Products();
                product1.setProductID("1");
                product1.setProductName("Test Product 1");

                Products product2 = new Products();
                product2.setProductID("2");
                product2.setProductName("Test Product 2");


                List<Products> productList = new ArrayList<>();
                productList.add(product1);
                productList.add(product2);

                when(productsDao.findAll()).thenReturn(productList);

                String productId = productsService.getProductIDByName("Test Product 2");

                // Verify that the correct product ID is returned
                assertNotEquals("1", productId);
            }


        @Test
        public void ProductsServiceTestGetProductByID() {
                    // Create some test products
                    Products product1 = new Products();
                    product1.setProductID("1");
                    product1.setProductName("Test Product 1");

                    Products product2 = new Products();
                    product2.setProductID("2");
                    product2.setProductName("Test Product 2");

                    List<Products> productList = new ArrayList<>();
                    productList.add(product1);
                    productList.add(product2);

                    when(productsDao.findAll()).thenReturn(productList);
                    Optional<Products> product = productsService.getProductbyID("1");

                    // Verify that the correct product is returned
                    assertEquals(product1, product.orElse(null));
        }

        @Test
        public void testGetProductsCatalog() {
            // Create some test products
            Products product1 = new Products();
            product1.setProductID("1");
            product1.setProductName("Test Product 1");

            Products product2 = new Products();
            product2.setProductID("2");
            product2.setProductName("Test Product 2");

            List<Products> productList = new ArrayList<>();
            productList.add(product1);
            productList.add(product2);

            when(productsDao.findAll()).thenReturn(productList);

            List<Products> result = productsService.getProductsCatalog();

            // Verify that the correct list of products is returned
            assertEquals(productList, result);
        }

}
