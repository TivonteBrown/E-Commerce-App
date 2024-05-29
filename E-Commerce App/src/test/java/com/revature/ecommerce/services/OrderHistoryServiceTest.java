package com.revature.ecommerce.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.eCommerce.dao.OrderHistoryDao;
import com.revature.eCommerce.services.OrderHistoryService;
import com.revature.eCommerce.models.Cart;
import com.revature.eCommerce.models.OrderHistory;

import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderHistoryServiceTest {

    @Mock
    private OrderHistoryDao orderHistoryDao;

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

        @Test
        public void OrderHistoryServiceSaveTest() {
            // Mock data
            OrderHistory orderHistory = new OrderHistory();
            List<OrderHistory> orderHistoryList = new ArrayList<>();
            orderHistoryList.add(new OrderHistory());

            when(orderHistoryDao.createOrderHistoryByID(any(OrderHistory.class))).thenReturn(orderHistoryList);
            doNothing().when(orderHistoryDao).save(any(OrderHistory.class));

            orderHistoryService.save(orderHistory);

            // Verify that the orderHistoryDao methods were called with correct parameters
            verify(orderHistoryDao, times(1)).createOrderHistoryByID(any(OrderHistory.class));
            verify(orderHistoryDao, times(1)).save(any(OrderHistory.class));

        }

        @Test
        public void OrderHistoryServiceTestCreateOrderHistoryByID() {
            // Mock data
            OrderHistory orderHistory = new OrderHistory();

            List<OrderHistory> expectedOrderHistoryList = new ArrayList<>();
            expectedOrderHistoryList.add(new OrderHistory());
            when(orderHistoryDao.createOrderHistoryByID(any(OrderHistory.class))).thenReturn(expectedOrderHistoryList);

            List<OrderHistory> actualOrderHistoryList = orderHistoryDao.createOrderHistoryByID(orderHistory);

            // Verify that the correct orderHistoryList is returned
            assertEquals(expectedOrderHistoryList, actualOrderHistoryList);
        }

        @Test
        public void OrderHistoryServiceTestFindOrderHistory() {
            // Mock data
            List<OrderHistory> expectedOrderHistoryList = new ArrayList<>();
            expectedOrderHistoryList.add(new OrderHistory());

            when(orderHistoryDao.getOrderHistory()).thenReturn(expectedOrderHistoryList);

            List<OrderHistory> actualOrderHistoryList = orderHistoryService.findOrderHistory();

            assertEquals(expectedOrderHistoryList, actualOrderHistoryList);

        }


}




