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

import com.shopizer.ecommerce.features.platform.data.mapper.PermissionMapper;
import com.shopizer.ecommerce.features.platform.data.mapper.RoleMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.CreateRoleRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.Role;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.UpdateRoleRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.RoleEntity;
import com.shopizer.ecommerce.features.platform.data.repository.PermissionRepository;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * Decorator implementation that maps / transforms data from an instance of type {@link RoleEntity to {@link Role and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
public abstract class RoleMapperDecorator implements RoleMapper {

    /** Mapper implementation of type {@link RoleMapper}. */
    @Autowired private RoleMapper roleMapper;

    /** Mapper implementation of type {@link PermissionMapper}. */
    @Autowired private PermissionMapper permissionMapper;

    /** Repository implementation of type PermissionRepository. */
    @Autowired private PermissionRepository permissionRepository;

    @Override
    public RoleEntity transform(final CreateRoleRequest source) {
        // 1. Transform the CreateRoleRequest to RoleEntity object.
        final RoleEntity role = roleMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getPermissions())) {
            role.setPermissions(permissionRepository.findAllById(source.getPermissions()));
        }
        // Return the transformed object.
        return role;
    }

    @Override
    public Role transform(final RoleEntity source) {
        // 1. Transform the RoleEntity to Role object.
        final Role role = roleMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getPermissions())) {
            role.setPermissions(
                    source.getPermissions().stream()
                            .map(permission -> permissionMapper.transform(permission))
                            .collect(Collectors.toList()));
        }
        // Return the transformed object.
        return role;
    }

    @Override
    public void transform(final UpdateRoleRequest source, final @MappingTarget RoleEntity target) {

        // Transform from source to the target.
        roleMapper.transform(source, target);

        if (!CollectionUtils.isEmpty(source.getPermissions())) {
            target.setPermissions(permissionRepository.findAllById(source.getPermissions()));
        }
    }

    @Override
    public RoleEntity transform(final UpdateRoleRequest source) {

        // Transform from source to the target.
        final RoleEntity role = roleMapper.transform(source);

        if (!CollectionUtils.isEmpty(source.getPermissions())) {
            role.setPermissions(permissionRepository.findAllById(source.getPermissions()));
        }

        // Return the response.
        return role;
    }
}
