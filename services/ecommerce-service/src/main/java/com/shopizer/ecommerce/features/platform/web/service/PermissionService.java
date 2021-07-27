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
import com.shopizer.ecommerce.features.platform.data.mapper.PermissionMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.CreatePermissionRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.Permission;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.UpdatePermissionRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity;
import com.shopizer.ecommerce.features.platform.data.repository.PermissionRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link PermissionEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@Validated
@Service
public class PermissionService {
    /** Repository implementation of type {@link PermissionRepository}. */
    private final PermissionRepository permissionRepository;

    /**
     * Mapper implementation of type {@link PermissionMapper} to transform between different types.
     */
    private final PermissionMapper permissionMapper;

    /**
     * Constructor.
     *
     * @param permissionRepository Repository instance of type {@link PermissionRepository}.
     * @param permissionMapper Mapper instance of type {@link PermissionMapper}.
     */
    public PermissionService(
            final PermissionRepository permissionRepository,
            final PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }

    /**
     * This method attempts to create an instance of type {@link PermissionEntity} in the system
     * based on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     PermissionEntity}.
     * @return An experience model of type {@link Permission} that represents the newly created
     *     entity of type {@link PermissionEntity}.
     */
    @Instrument
    @Transactional
    public Permission createPermission(@Valid final CreatePermissionRequest payload) {
        // 1. Transform the experience model to a persistence model.
        final PermissionEntity permissionEntity = permissionMapper.transform(payload);

        // 2. Save the entity.
        PermissionService.LOGGER.debug("Saving a new instance of type - PermissionEntity");
        final PermissionEntity newInstance = permissionRepository.save(permissionEntity);

        // 3. Transform the created entity to an experience model and return it.
        return permissionMapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link PermissionEntity} using
     * the details from the provided input, which is an instance of type {@link
     * UpdatePermissionRequest}.
     *
     * @param permissionId Unique identifier of Permission in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Permission, which needs
     *     to be updated in the system.
     * @return A instance of type {@link Permission} containing the updated details.
     */
    @Instrument
    @Transactional
    public Permission updatePermission(
            final Integer permissionId, @Valid final UpdatePermissionRequest payload) {
        // 1. Verify that the entity being updated truly exists.
        final PermissionEntity matchingInstance =
                permissionRepository.findByIdOrThrow(permissionId);

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        permissionMapper.transform(payload, matchingInstance);

        // 3. Save the entity
        PermissionService.LOGGER.debug("Saving the updated entity - PermissionEntity");
        final PermissionEntity updatedInstance = permissionRepository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return permissionMapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link PermissionEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param permissionId Unique identifier of Permission in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link Permission} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Permission findPermission(final Integer permissionId) {
        // 1. Find a matching entity and throw an exception if not found.
        final PermissionEntity matchingInstance =
                permissionRepository.findByIdOrThrow(permissionId);

        // 2. Transform the matching entity to the desired output.
        return permissionMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type PermissionEntity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Permission}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Permission> findAllPermissions(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        PermissionService.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<PermissionEntity> pageData = permissionRepository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Permission> dataToReturn =
                    pageData.getContent().stream()
                            .map(permissionMapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link PermissionEntity} whose
     * unique identifier matches the provided identifier.
     *
     * @param permissionId Unique identifier of Permission in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type PermissionEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deletePermission(final Integer permissionId) {
        // 1. Delegate to our repository method to handle the deletion.
        return permissionRepository.deleteOne(permissionId);
    }
}
