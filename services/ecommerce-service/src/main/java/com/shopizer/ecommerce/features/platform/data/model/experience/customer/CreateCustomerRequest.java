/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.experience.customer;

import com.shopizer.ecommerce.features.platform.data.model.experience.order.CreateOrderRequest;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author Murali Krishna Uppala
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class CreateCustomerRequest {
    /** Collection of orders that are assigned to this customer. */
    @NotNull(message = "customer.orders.not.null.message")
    private Collection<CreateOrderRequest> orders;

    /** address assigned to this customer. */
    @NotNull(message = "customer.address.not.null.message")
    private CreateAddressRequest address;
}
