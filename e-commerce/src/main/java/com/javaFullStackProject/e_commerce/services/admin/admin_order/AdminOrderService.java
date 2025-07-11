package com.javaFullStackProject.e_commerce.services.admin.admin_order;

import com.javaFullStackProject.e_commerce.dto.AnalyticsResponse;
import com.javaFullStackProject.e_commerce.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrders();
    OrderDto changeOrderStatus(Long orderId, String status);
    AnalyticsResponse calculateAnalytics();
}
