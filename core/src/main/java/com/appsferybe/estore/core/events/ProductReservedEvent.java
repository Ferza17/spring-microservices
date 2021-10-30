package com.appsferybe.estore.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {
    public final String productId;
    public final int quantity;
    public final String orderId;
    public final String userId;
}
