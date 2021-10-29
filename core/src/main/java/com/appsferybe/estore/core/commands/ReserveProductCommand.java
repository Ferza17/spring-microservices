package com.appsferybe.estore.core.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ReserveProductCommand {
    @TargetAggregateIdentifier
    public final String productId;
    public final int quantity;
    public final String orderId;
    public final String userId;
}
