/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.web.service;

import com.shopizer.ecommerce.commons.data.utils.PageUtils;
import com.shopizer.ecommerce.commons.instrumentation.Instrument;
import com.shopizer.ecommerce.features.platform.data.mapper.RoleMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.CreateRoleRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.Role;
import com.shopizer.ecommerce.features.platform.data.model.experience.role.UpdateRoleRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity;
import com.shopizer.ecommerce.features.platform.data.model.persistence.RoleEntity;
import com.shopizer.ecommerce.features.platform.data.repository.PermissionRepository;
import com.shopizer.ecommerce.features.platform.data.repository.RoleRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link RoleEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@Validated
@Service
public class RoleService {

    /** Repository implementation of type {@link RoleRepository}. */
    private final RoleRepository roleRepository;

    /** Mapper implementation of type {@link RoleMapper} to transform between different types. */
    private final RoleMapper roleMapper;

    /** Repository implementation of type {@link PermissionRepository}. */
    private final PermissionRepository permissionRepository;

    /**
     * Constructor.
     *
     * @param roleRepository Repository instance of type {@link RoleRepository}.
     * @param roleMapper Mapper instance of type {@link RoleMapper}.
     * @param permissionRepository Repository instance of type {@link PermissionRepository}.
     */
    public RoleService(
            final RoleRepository roleRepository,
            final RoleMapper roleMapper,
            final PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.permissionRepository = permissionRepository;
    }

    /**
     * This method attempts to create an instance of type {@link RoleEntity} in the system based on
     * the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     RoleEntity}.
     * @return An experience model of type {@link Role} that represents the newly created entity of
     *     type {@link RoleEntity}.
     */
    @Instrument
    @Transactional
    public Role createRole(@Valid final CreateRoleRequest payload) {
        // 1. Transform the experience model to a persistence model.
        final RoleEntity roleEntity = roleMapper.transform(payload);

        // 2. Save the entity.
        RoleService.LOGGER.debug("Saving a new instance of type - RoleEntity");
        final RoleEntity newInstance = roleRepository.save(roleEntity);

        // 3. Transform the created entity to an experience model and return it.
        return roleMapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link RoleEntity} using the
     * details from the provided input, which is an instance of type {@link UpdateRoleRequest}.
     *
     * @param roleId Unique identifier of Role in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Role, which needs to be
     *     updated in the system.
     * @return A instance of type {@link Role} containing the updated details.
     */
    @Instrument
    @Transactional
    public Role updateRole(final Integer roleId, @Valid final UpdateRoleRequest payload) {
        // 1. Verify that the entity being updated truly exists.
        final RoleEntity matchingInstance = roleRepository.findByIdOrThrow(roleId);

        // 2. Transform the experience model to a persistence model and delegate to the save(..)
        // method.
        roleMapper.transform(payload, matchingInstance);

        // 3. Save the entity
        RoleService.LOGGER.debug("Saving the updated entity - RoleEntity");
        final RoleEntity updatedInstance = roleRepository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return roleMapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link RoleEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param roleId Unique identifier of Role in the system, whose details have to be retrieved.
     * @return Matching entity of type {@link Role} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Role findRole(final Integer roleId) {
        // 1. Find a matching entity and throw an exception if not found.
        final RoleEntity matchingInstance = roleRepository.findByIdOrThrow(roleId);

        // 2. Transform the matching entity to the desired output.
        return roleMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type RoleEntity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Role}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Role> findAllRoles(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        RoleService.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<RoleEntity> pageData = roleRepository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Role> dataToReturn =
                    pageData.getContent().stream()
                            .map(roleMapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link RoleEntity} whose unique
     * identifier matches the provided identifier.
     *
     * @param roleId Unique identifier of Role in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type RoleEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteRole(final Integer roleId) {
        // 1. Delegate to our repository method to handle the deletion.
        return roleRepository.deleteOne(roleId);
    }

    /**
     * This method attempts to update an existing instance of type {@link RoleEntity} by adding
     * permissions.
     *
     * @param roleId Unique identifier of Role in the system, whose details have to be retrieved.
     * @param ids Collection of unique identifiers pertaining to permission.
     * @return An instance of type {@link Role} containing the updated details.
     */
    @Instrument
    @Transactional
    public Role addPermissionsToRole(final Integer roleId, final Collection<Integer> ids) {

        // 1. Find a matching entity and throw an exception if not found.
        final RoleEntity matchingRole = roleRepository.findByIdOrThrow(roleId);

        // 2. Find all entities based on the given id's.
        final Iterable<PermissionEntity> matchingPermissions =
                permissionRepository.findAllOrThrow(ids);

        // 3. Set the other models to matching instance.
        matchingRole.setPermissions(
                StreamSupport.stream(matchingPermissions.spliterator(), false)
                        .collect(Collectors.toSet()));

        // 4. Persist the updated instance.
        final RoleEntity updatedRole = roleRepository.save(matchingRole);

        // 5. Transform updated entity to output object.
        return roleMapper.transform(updatedRole);
    }

    /**
     * This method attempts to delete some of other instances from an existing instance of type
     * {@link RoleEntity} using the details from the provided input.
     *
     * @param roleId Unique identifier of Role in the system, whose details have to be retrieved.
     * @param ids unique identifiers pertaining to permission.
     * @return Instance of type {@link Role} containing the updated details.
     */
    @Instrument
    @Transactional
    public Role deletePermissionsFromRole(final Integer roleId, final Collection<Integer> ids) {
        // 1. Find a matching entity and throw an exception if not found.
        final RoleEntity matchingRole = roleRepository.findByIdOrThrow(roleId);

        // 2. Find all entities based on the given id's.
        final Iterable<PermissionEntity> matchingPermissions =
                permissionRepository.findAllOrThrow(ids);

        // 3. Convert iterable to collection of entities.
        final Collection<PermissionEntity> permissionsToDelete =
                StreamSupport.stream(matchingPermissions.spliterator(), false)
                        .collect(Collectors.toSet());

        // 4. Delete the requested entities from the matchingInstance.
        matchingRole.setPermissions(
                matchingRole.getPermissions().stream()
                        .filter(permissionsToDelete::contains)
                        .collect(Collectors.toSet()));

        // 5. Save the updated roles to user.
        final RoleEntity updatedRole = roleRepository.save(matchingRole);

        // 6. Transform updated entity to output object.
        return roleMapper.transform(updatedRole);
    }
}
