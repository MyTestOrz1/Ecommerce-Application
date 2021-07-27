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

import com.shopizer.ecommerce.features.platform.data.mapper.AddressMapper;
import com.shopizer.ecommerce.features.platform.data.mapper.CustomerMapper;
import com.shopizer.ecommerce.features.platform.data.mapper.OrderMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.CreateCustomerRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.Customer;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.UpdateCustomerRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.CustomerEntity;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Decorator implementation that maps / transforms data from an instance of type {@link CustomerEntity to {@link Customer and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
public abstract class CustomerMapperDecorator implements CustomerMapper {

    /** Mapper implementation of type {@link CustomerMapper}. */
    @Autowired private CustomerMapper customerMapper;

    /** Mapper implementation of type {@link AddressMapper}. */
    @Autowired private AddressMapper addressMapper;

    /** Mapper implementation of type {@link OrderMapper}. */
    @Autowired private OrderMapper orderMapper;

    @Override
    public CustomerEntity transform(final CreateCustomerRequest source) {
        // 1. Transform the CreateCustomerRequest to CustomerEntity object.
        final CustomerEntity customer = customerMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getOrders())) {
            customer.setOrders(
                    source.getOrders().stream()
                            .map(order -> orderMapper.transform(order))
                            .collect(Collectors.toList()));
        }
        if (Objects.nonNull(source.getAddress())) {
            customer.setAddress(addressMapper.transform(source.getAddress()));
        }
        // Return the transformed object.
        return customer;
    }

    @Override
    public Customer transform(final CustomerEntity source) {
        // 1. Transform the CustomerEntity to Customer object.
        final Customer customer = customerMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getOrders())) {
            customer.setOrders(
                    source.getOrders().stream()
                            .map(order -> orderMapper.transform(order))
                            .collect(Collectors.toList()));
        }
        if (Objects.nonNull(source.getAddress())) {
            customer.setAddress(addressMapper.transform(source.getAddress()));
        }
        // Return the transformed object.
        return customer;
    }

    @Override
    public void transform(
            final UpdateCustomerRequest source, final @MappingTarget CustomerEntity target) {

        // Transform from source to the target.
        customerMapper.transform(source, target);

        if (!CollectionUtils.isEmpty(source.getOrders())) {
            // As Hibernate does allow to override the value in case of composition, we need to
            // clear the collection first then add updated collection.
            target.getOrders().clear();
            target.getOrders().addAll(orderMapper.transformList(source.getOrders()));
        }
        if (Objects.nonNull(source.getAddress())) {
            addressMapper.transform(source.getAddress(), target.getAddress());
        }
    }

    @Override
    public CustomerEntity transform(final UpdateCustomerRequest source) {

        // Transform from source to the target.
        final CustomerEntity customer = customerMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getOrders())) {
            customer.setOrders(orderMapper.transformList(source.getOrders()));
        }
        if (Objects.nonNull(source.getAddress())) {
            customer.setAddress(addressMapper.transform(source.getAddress()));
        }
        // Return the response.
        return customer;
    }
}
