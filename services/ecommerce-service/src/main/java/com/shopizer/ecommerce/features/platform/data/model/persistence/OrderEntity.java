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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "order" table in the database to an entity in the ORM world.
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
@Table(name = "order")
@Entity
public class OrderEntity extends AbstractIdGeneratedEntity<Integer> {

    /** Description. */
    @Column(name = "description", length = 8192)
    private String description;

    /** Collection of Line Items of the order. */
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Collection<LineItemEntity> lineItems;

    /**
     * This entity method is used to add an existing LineItem in the system. {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} into the
     * system.
     *
     * @param lineItem containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
     */
    public void addLineItem(final LineItemEntity lineItem) {
        if (Objects.nonNull(lineItem)) {
            this.lineItems.add(lineItem);
        }
    }

    /**
     * This entity method is used to delete an existing LineItem in the system. instance of {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}}.
     *
     * @param lineItemId Unique identifier of LineItem.
     */
    public void deleteLineItemById(final Integer lineItemId) {

        final Optional<LineItemEntity> matchingRecord = findLineItemById(lineItemId);
        if (matchingRecord.isPresent()) {
            getLineItems().remove(matchingRecord.get());
        }
    }

    /**
     * This method is used to find an existing LineItem in the system. instance of {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}}.
     *
     * @param lineItemId Unique identifier of LineItem.
     * @return Return an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
     */
    public Optional<LineItemEntity> findLineItemById(final Integer lineItemId) {

        if (Objects.isNull(lineItems) || lineItems.isEmpty()) {
            return Optional.empty();
        }

        return lineItems.stream()
                .filter(line_item -> line_item.getId().equals(lineItemId))
                .findFirst();
    }

    /**
     * This entity method is used to retrieve the latest LineItem of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
     *
     * @return Return an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
     */
    public LineItemEntity getLatestLineItem() {
        final List<LineItemEntity> lineItems = (List<LineItemEntity>) this.lineItems;
        return lineItems.get(lineItems.size() - 1);
    }
}
