package com.revature.eCommerce.services;

import com.revature.eCommerce.dao.OrderHistoryDao;
import com.revature.eCommerce.models.OrderHistory;

import java.util.Optional;
import java.util.UUID;
import java.util.List;


public class OrderHistoryService {

    private final OrderHistoryDao orderHistoryDao;



    public OrderHistoryService(OrderHistoryDao orderHistoryDao) {
        this.orderHistoryDao = orderHistoryDao;
    }

        public void save(OrderHistory orderHistory ) {
            //add timestamp and totalOrderPrice to orderHistory object

            List<OrderHistory> OrderHistoryList = orderHistoryDao.createOrderHistoryByID(orderHistory);
            String orderID = UUID.randomUUID().toString();
            // Iterate over each object in the OrderHistoryList
            for (OrderHistory oH : OrderHistoryList) {
                oH.setOrderID(orderID);
                // Save the current orderHistory object using orderHistoryDao.save()
                orderHistoryDao.save(oH);
            }

        }


        public List<OrderHistory> findOrderHistoryByID(String orderHistoryID){
            return orderHistoryDao.getOrderHistoryByUserID(orderHistoryID);
        }

        public List<OrderHistory> findOrderHistory(){
            return orderHistoryDao.getOrderHistory();
        }


}
