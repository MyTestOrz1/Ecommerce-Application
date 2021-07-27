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
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.CreateLineItemRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.LineItem;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.UpdateLineItemRequest;
import com.shopizer.ecommerce.features.platform.web.service.LineItemService;
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
 * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@RestController
public class LineItemApi extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "LineItems";

    /** Service implementation of type {@link LineItemService}. */
    private final LineItemService lineItemService;

    /**
     * Constructor.
     *
     * @param lineItemService Service instance of type {@link LineItemService}.
     */
    public LineItemApi(final LineItemService lineItemService) {
        this.lineItemService = lineItemService;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} into the
     * system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     LineItem}.
     */
    @Operation(
            method = "createLineItem",
            summary = "Create a new LineItem.",
            description = "This API is used to create a new LineItem in the system.",
            tags = {LineItemApi.API_TAG},
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
                        description = "Successfully created a new LineItem in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/customers/{customerId}/orders/{orderId}/lineItems")
    public ResponseEntity<LineItem> createLineItem(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @Valid @RequestBody final CreateLineItemRequest payload) {
        // Delegate to the service layer.
        final LineItem newInstance = lineItemService.createLineItem(customerId, orderId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} in the
     * system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, whose details have to be retrieved.
     * @param lineItemId Unique identifier of LineItem in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing LineItem, which needs to
     *     be updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     LineItem}.
     */
    @Operation(
            method = "updateLineItem",
            summary = "Update an existing LineItem.",
            description = "This API is used to update an existing LineItem in the system.",
            tags = {LineItemApi.API_TAG},
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
                        description = "Successfully updated an existing LineItem in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/customers/{customerId}/orders/{orderId}/lineItems/{lineItemId}")
    public ResponseEntity<LineItem> updateLineItem(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @PathVariable(name = "lineItemId") final Integer lineItemId,
            @Valid @RequestBody final UpdateLineItemRequest payload) {
        // Delegate to the service layer.
        final LineItem updatedInstance =
                lineItemService.updateLineItem(customerId, orderId, lineItemId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} in the
     * system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, whose details have to be retrieved.
     * @param lineItemId Unique identifier of LineItem in the system, whose details have to be
     *     retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     LineItem}.
     */
    @Operation(
            method = "findLineItem",
            summary = "Find an existing LineItem.",
            description = "This API is used to find an existing LineItem in the system.",
            tags = {LineItemApi.API_TAG},
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
                                "Successfully retrieved the details of an existing LineItem in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/customers/{customerId}/orders/{orderId}/lineItems/{lineItemId}")
    public ResponseEntity<LineItem> findLineItem(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @PathVariable(name = "lineItemId") final Integer lineItemId) {
        // Delegate to the service layer.
        final LineItem matchingInstance =
                lineItemService.findLineItem(customerId, orderId, lineItemId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} in the system
     * in a paginated manner.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, whose details have to be retrieved.
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type
     *     LineItem based on the provided pagination settings.
     */
    @Operation(
            method = "findAllLineItems",
            summary = "Find all LineItems.",
            description = "This API is used to find all LineItems in the system.",
            tags = {LineItemApi.API_TAG},
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
                                "Successfully retrieved the LineItems in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/customers/{customerId}/orders/{orderId}/lineItems")
    public ResponseEntity<Page<LineItem>> findAllLineItems(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<LineItem> matchingInstances =
                lineItemService.findAllLineItems(customerId, orderId, pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity} in the
     * system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, whose details have to be retrieved.
     * @param lineItemId Unique identifier of LineItem in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity}
     *     that was deleted from the system.
     */
    @Operation(
            method = "deleteLineItem",
            summary = "Delete an existing LineItem.",
            description = "This API is used to delete an existing LineItem in the system.",
            tags = {LineItemApi.API_TAG},
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
                        description = "Successfully deleted an existing LineItem in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/customers/{customerId}/orders/{orderId}/lineItems/{lineItemId}")
    public ResponseEntity<Integer> deleteLineItem(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @PathVariable(name = "lineItemId") final Integer lineItemId) {
        // Delegate to the service layer.
        final Integer deletedInstance =
                lineItemService.deleteLineItem(customerId, orderId, lineItemId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
