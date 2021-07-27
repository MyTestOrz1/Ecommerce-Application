/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.mapper.decorator;

import com.shopizer.ecommerce.features.platform.data.mapper.LineItemMapper;
import com.shopizer.ecommerce.features.platform.data.mapper.OrderMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.CreateOrderRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.Order;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.UpdateOrderRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Decorator implementation that maps / transforms data from an instance of type {@link OrderEntity to {@link Order and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
public abstract class OrderMapperDecorator implements OrderMapper {

    /** Mapper implementation of type {@link OrderMapper}. */
    @Autowired private OrderMapper orderMapper;

    /** Mapper implementation of type {@link LineItemMapper}. */
    @Autowired private LineItemMapper lineItemMapper;

    @Override
    public OrderEntity transform(final CreateOrderRequest source) {
        // 1. Transform the CreateOrderRequest to OrderEntity object.
        final OrderEntity order = orderMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getLineItems())) {
            order.setLineItems(
                    source.getLineItems().stream()
                            .map(line_item -> lineItemMapper.transform(line_item))
                            .collect(Collectors.toList()));
        }
        // Return the transformed object.
        return order;
    }

    @Override
    public Order transform(final OrderEntity source) {
        // 1. Transform the OrderEntity to Order object.
        final Order order = orderMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getLineItems())) {
            order.setLineItems(
                    source.getLineItems().stream()
                            .map(line_item -> lineItemMapper.transform(line_item))
                            .collect(Collectors.toList()));
        }
        // Return the transformed object.
        return order;
    }

    @Override
    public void transform(
            final UpdateOrderRequest source, final @MappingTarget OrderEntity target) {

        // Transform from source to the target.
        orderMapper.transform(source, target);

        if (!CollectionUtils.isEmpty(source.getLineItems())) {
            // As Hibernate does allow to override the value in case of composition, we need to
            // clear the collection first then add updated collection.
            target.getLineItems().clear();
            target.getLineItems().addAll(lineItemMapper.transformList(source.getLineItems()));
        }
    }

    @Override
    public OrderEntity transform(final UpdateOrderRequest source) {

        // Transform from source to the target.
        final OrderEntity order = orderMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getLineItems())) {
            order.setLineItems(lineItemMapper.transformList(source.getLineItems()));
        }
        // Return the response.
        return order;
    }
}
