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

import com.shopizer.ecommerce.features.platform.data.model.experience.permission.CreatePermissionRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.Permission;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.UpdatePermissionRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link PermissionEntity to {@link Permission and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PermissionMapper {

    /**
     * This method transforms an instance of type {@link CreatePermissionRequest} to an instance of
     * type {@link PermissionEntity}.
     *
     * @param source Instance of type {@link CreatePermissionRequest} that needs to be transformed
     *     to {@link PermissionEntity}.
     * @return Instance of type {@link PermissionEntity}.
     */
    PermissionEntity transform(CreatePermissionRequest source);

    /**
     * This method transforms an instance of type {@link PermissionEntity} to an instance of type
     * {@link Permission}.
     *
     * @param source Instance of type {@link PermissionEntity} that needs to be transformed to
     *     {@link Permission}.
     * @return Instance of type {@link Permission}.
     */
    Permission transform(PermissionEntity source);

    /**
     * This method converts / transforms the provided collection of {@link PermissionEntity}
     * instances to a collection of instances of type {@link Permission}.
     *
     * @param source Instance of type {@link PermissionEntity} that needs to be transformed to
     *     {@link Permission}.
     * @return Collection of instance of type {@link Permission}.
     */
    default Collection<Permission> transformListTo(Collection<PermissionEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdatePermissionRequest} to an instance of
     * type {@link PermissionEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdatePermissionRequest} that needs to be transformed
     *     to {@link PermissionEntity}.
     * @param target Instance of type {@link PermissionEntity} that will be updated instead of
     *     creating and returning a new instance.
     */
    void transform(UpdatePermissionRequest source, @MappingTarget PermissionEntity target);

    /**
     * This method transforms an instance of type {@link UpdatePermissionRequest} to an instance of
     * type {@link PermissionEntity}.
     *
     * @param source Instance of type {@link UpdatePermissionRequest} that needs to be transformed
     *     to {@link PermissionEntity}.
     * @return Instance of type {@link PermissionEntity}.
     */
    PermissionEntity transform(UpdatePermissionRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdatePermissionRequest}
     * instances to a collection of instances of type {@link PermissionEntity}.
     *
     * @param source Instance of type {@link UpdatePermissionRequest} that needs to be transformed
     *     to {@link PermissionEntity}.
     * @return Instance of type {@link PermissionEntity}.
     */
    default Collection<PermissionEntity> transformList(Collection<UpdatePermissionRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
