/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.mapper;

import com.shopizer.ecommerce.features.platform.data.mapper.decorator.OrderMapperDecorator;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.CreateOrderRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.Order;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.UpdateOrderRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link OrderEntity to {@link Order and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@DecoratedWith(value = OrderMapperDecorator.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderMapper {

    /**
     * This method transforms an instance of type {@link CreateOrderRequest} to an instance of type
     * {@link OrderEntity}.
     *
     * @param source Instance of type {@link CreateOrderRequest} that needs to be transformed to
     *     {@link OrderEntity}.
     * @return Instance of type {@link OrderEntity}.
     */
    @Mapping(source = "lineItems", target = "lineItems", ignore = true)
    OrderEntity transform(CreateOrderRequest source);

    /**
     * This method transforms an instance of type {@link OrderEntity} to an instance of type {@link
     * Order}.
     *
     * @param source Instance of type {@link OrderEntity} that needs to be transformed to {@link
     *     Order}.
     * @return Instance of type {@link Order}.
     */
    @Mapping(source = "lineItems", target = "lineItems", ignore = true)
    Order transform(OrderEntity source);

    /**
     * This method converts / transforms the provided collection of {@link OrderEntity} instances to
     * a collection of instances of type {@link Order}.
     *
     * @param source Instance of type {@link OrderEntity} that needs to be transformed to {@link
     *     Order}.
     * @return Collection of instance of type {@link Order}.
     */
    default Collection<Order> transformListTo(Collection<OrderEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateOrderRequest} to an instance of type
     * {@link OrderEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateOrderRequest} that needs to be transformed to
     *     {@link OrderEntity}.
     * @param target Instance of type {@link OrderEntity} that will be updated instead of creating
     *     and returning a new instance.
     */
    @Mapping(source = "lineItems", target = "lineItems", ignore = true)
    void transform(UpdateOrderRequest source, @MappingTarget OrderEntity target);

    /**
     * This method transforms an instance of type {@link UpdateOrderRequest} to an instance of type
     * {@link OrderEntity}.
     *
     * @param source Instance of type {@link UpdateOrderRequest} that needs to be transformed to
     *     {@link OrderEntity}.
     * @return Instance of type {@link OrderEntity}.
     */
    @Mapping(source = "lineItems", target = "lineItems", ignore = true)
    OrderEntity transform(UpdateOrderRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateOrderRequest}
     * instances to a collection of instances of type {@link OrderEntity}.
     *
     * @param source Instance of type {@link UpdateOrderRequest} that needs to be transformed to
     *     {@link OrderEntity}.
     * @return Instance of type {@link OrderEntity}.
     */
    default Collection<OrderEntity> transformList(Collection<UpdateOrderRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
