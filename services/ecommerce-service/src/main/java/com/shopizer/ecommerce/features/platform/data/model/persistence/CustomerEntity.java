/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.persistence;

import com.shopizer.ecommerce.commons.data.jpa.persistence.AbstractIdGeneratedEntity;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "customer" table in the database to an entity in the ORM world.
 *
 * @author Murali Krishna Uppala
 */
@EqualsAndHashCode(
        callSuper = true,
        of = {})
@ToString(
        callSuper = true,
        of = {})
@Getter
@Setter
@NoArgsConstructor
@Table(name = "customer")
@Entity
public class CustomerEntity extends AbstractIdGeneratedEntity<Integer> {

    /** Collection of orders that are assigned to this customer. */
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Collection<OrderEntity> orders;
    /** address assigned to this customer. */
    @OneToOne(
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private AddressEntity address;

    /**
     * This entity method is used to add an existing Order in the system. {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} into the system.
     *
     * @param order containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
     */
    public void addOrder(final OrderEntity order) {
        if (Objects.nonNull(order)) {
            this.orders.add(order);
        }
    }

    /**
     * This entity method is used to delete an existing Order in the system. instance of {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}}.
     *
     * @param orderId Unique identifier of Order.
     */
    public void deleteOrderById(final Integer orderId) {

        final Optional<OrderEntity> matchingRecord = findOrderById(orderId);
        if (matchingRecord.isPresent()) {
            getOrders().remove(matchingRecord.get());
        }
    }

    /**
     * This method is used to find an existing Order in the system. instance of {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}}.
     *
     * @param orderId Unique identifier of Order.
     * @return Return an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
     */
    public Optional<OrderEntity> findOrderById(final Integer orderId) {

        if (Objects.isNull(orders) || orders.isEmpty()) {
            return Optional.empty();
        }

        return orders.stream().filter(order -> order.getId().equals(orderId)).findFirst();
    }

    /**
     * This entity method is used to retrieve the latest Order of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
     *
     * @return Return an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
     */
    public OrderEntity getLatestOrder() {
        final List<OrderEntity> orders = (List<OrderEntity>) this.orders;
        return orders.get(orders.size() - 1);
    }
}
