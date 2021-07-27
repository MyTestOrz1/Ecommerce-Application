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

import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.CreateLineItemRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.LineItem;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.UpdateLineItemRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link LineItemEntity to {@link LineItem and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface LineItemMapper {

    /**
     * This method transforms an instance of type {@link CreateLineItemRequest} to an instance of
     * type {@link LineItemEntity}.
     *
     * @param source Instance of type {@link CreateLineItemRequest} that needs to be transformed to
     *     {@link LineItemEntity}.
     * @return Instance of type {@link LineItemEntity}.
     */
    LineItemEntity transform(CreateLineItemRequest source);

    /**
     * This method transforms an instance of type {@link LineItemEntity} to an instance of type
     * {@link LineItem}.
     *
     * @param source Instance of type {@link LineItemEntity} that needs to be transformed to {@link
     *     LineItem}.
     * @return Instance of type {@link LineItem}.
     */
    LineItem transform(LineItemEntity source);

    /**
     * This method converts / transforms the provided collection of {@link LineItemEntity} instances
     * to a collection of instances of type {@link LineItem}.
     *
     * @param source Instance of type {@link LineItemEntity} that needs to be transformed to {@link
     *     LineItem}.
     * @return Collection of instance of type {@link LineItem}.
     */
    default Collection<LineItem> transformListTo(Collection<LineItemEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateLineItemRequest} to an instance of
     * type {@link LineItemEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateLineItemRequest} that needs to be transformed to
     *     {@link LineItemEntity}.
     * @param target Instance of type {@link LineItemEntity} that will be updated instead of
     *     creating and returning a new instance.
     */
    void transform(UpdateLineItemRequest source, @MappingTarget LineItemEntity target);

    /**
     * This method transforms an instance of type {@link UpdateLineItemRequest} to an instance of
     * type {@link LineItemEntity}.
     *
     * @param source Instance of type {@link UpdateLineItemRequest} that needs to be transformed to
     *     {@link LineItemEntity}.
     * @return Instance of type {@link LineItemEntity}.
     */
    LineItemEntity transform(UpdateLineItemRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateLineItemRequest}
     * instances to a collection of instances of type {@link LineItemEntity}.
     *
     * @param source Instance of type {@link UpdateLineItemRequest} that needs to be transformed to
     *     {@link LineItemEntity}.
     * @return Instance of type {@link LineItemEntity}.
     */
    default Collection<LineItemEntity> transformList(Collection<UpdateLineItemRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
