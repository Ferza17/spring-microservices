package com.appsferybe.estore.OrdersService.core.events;

import com.appsferybe.estore.OrdersService.core.model.OrderStatus;
import lombok.Value;

@Value
public class OrderApprovedEvent {
    public final String orderId;
    public final OrderStatus orderStatus = OrderStatus.APPROVED;
}
