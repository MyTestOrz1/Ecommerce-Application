/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.experience.order;

import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.LineItem;
import java.util.Collection;
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
public class Order {
    /** Unique identifier of the record that captures the preferences of a specific user. */
    private Integer id;

    /** Description. */
    private String description;

    /** Collection of Line Items of the order. */
    private Collection<LineItem> lineItems;
}
