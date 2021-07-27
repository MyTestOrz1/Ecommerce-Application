/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.web.api;

import com.shopizer.ecommerce.commons.data.utils.PageUtils;
import com.shopizer.ecommerce.commons.web.api.AbstractApi;
import com.shopizer.ecommerce.commons.web.configuration.properties.ApiDocumentationSettings;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.CreatePermissionRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.Permission;
import com.shopizer.ecommerce.features.platform.data.model.experience.permission.UpdatePermissionRequest;
import com.shopizer.ecommerce.features.platform.web.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of APIs that provide CRUD (Create, Read, Update and Delete) functionality for
 * persistence models of type {@link
 * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@RestController
public class PermissionApi extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Permissions";

    /** Service implementation of type {@link PermissionService}. */
    private final PermissionService permissionService;

    /**
     * Constructor.
     *
     * @param permissionService Service instance of type {@link PermissionService}.
     */
    public PermissionApi(final PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity} into the
     * system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Permission}.
     */
    @Operation(
            method = "createPermission",
            summary = "Create a new Permission.",
            description = "This API is used to create a new Permission in the system.",
            tags = {PermissionApi.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully created a new Permission in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/permissions")
    public ResponseEntity<Permission> createPermission(
            @Valid @RequestBody final CreatePermissionRequest payload) {
        // Delegate to the service layer.
        final Permission newInstance = permissionService.createPermission(payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity} in the
     * system.
     *
     * @param permissionId Unique identifier of Permission in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Permission, which needs
     *     to be updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Permission}.
     */
    @Operation(
            method = "updatePermission",
            summary = "Update an existing Permission.",
            description = "This API is used to update an existing Permission in the system.",
            tags = {PermissionApi.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully updated an existing Permission in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/permissions/{permissionId}")
    public ResponseEntity<Permission> updatePermission(
            @PathVariable(name = "permissionId") final Integer permissionId,
            @Valid @RequestBody final UpdatePermissionRequest payload) {
        // Delegate to the service layer.
        final Permission updatedInstance =
                permissionService.updatePermission(permissionId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity} in the
     * system.
     *
     * @param permissionId Unique identifier of Permission in the system, whose details have to be
     *     retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Permission}.
     */
    @Operation(
            method = "findPermission",
            summary = "Find an existing Permission.",
            description = "This API is used to find an existing Permission in the system.",
            tags = {PermissionApi.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the details of an existing Permission in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/permissions/{permissionId}")
    public ResponseEntity<Permission> findPermission(
            @PathVariable(name = "permissionId") final Integer permissionId) {
        // Delegate to the service layer.
        final Permission matchingInstance = permissionService.findPermission(permissionId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity} in the
     * system in a paginated manner.
     *
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type
     *     Permission based on the provided pagination settings.
     */
    @Operation(
            method = "findAllPermissions",
            summary = "Find all Permissions.",
            description = "This API is used to find all Permissions in the system.",
            tags = {PermissionApi.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description =
                                "Successfully retrieved the Permissions in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/permissions")
    public ResponseEntity<Page<Permission>> findAllPermissions(
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Permission> matchingInstances =
                permissionService.findAllPermissions(pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity} in the
     * system.
     *
     * @param permissionId Unique identifier of Permission in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.shopizer.ecommerce.features.platform.data.model.persistence.PermissionEntity}
     *     that was deleted from the system.
     */
    @Operation(
            method = "deletePermission",
            summary = "Delete an existing Permission.",
            description = "This API is used to delete an existing Permission in the system.",
            tags = {PermissionApi.API_TAG},
            security = {
                @SecurityRequirement(
                        name =
                                ApiDocumentationSettings.ApiSecurityScheme
                                        .DEFAULT_SECURITY_SCHEME_NAME)
            })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully deleted an existing Permission in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/permissions/{permissionId}")
    public ResponseEntity<Integer> deletePermission(
            @PathVariable(name = "permissionId") final Integer permissionId) {
        // Delegate to the service layer.
        final Integer deletedInstance = permissionService.deletePermission(permissionId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
