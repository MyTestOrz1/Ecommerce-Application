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
import com.shopizer.ecommerce.features.platform.data.model.experience.order.CreateOrderRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.Order;
import com.shopizer.ecommerce.features.platform.data.model.experience.order.UpdateOrderRequest;
import com.shopizer.ecommerce.features.platform.web.service.OrderService;
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
 * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@RestController
public class OrderApi extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Orders";

    /** Service implementation of type {@link OrderService}. */
    private final OrderService orderService;

    /**
     * Constructor.
     *
     * @param orderService Service instance of type {@link OrderService}.
     */
    public OrderApi(final OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} into the system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Order}.
     */
    @Operation(
            method = "createOrder",
            summary = "Create a new Order.",
            description = "This API is used to create a new Order in the system.",
            tags = {OrderApi.API_TAG},
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
                        description = "Successfully created a new Order in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<Order> createOrder(
            @PathVariable(name = "customerId") final Integer customerId,
            @Valid @RequestBody final CreateOrderRequest payload) {
        // Delegate to the service layer.
        final Order newInstance = orderService.createOrder(customerId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Order, which needs to be
     *     updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Order}.
     */
    @Operation(
            method = "updateOrder",
            summary = "Update an existing Order.",
            description = "This API is used to update an existing Order in the system.",
            tags = {OrderApi.API_TAG},
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
                        description = "Successfully updated an existing Order in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId,
            @Valid @RequestBody final UpdateOrderRequest payload) {
        // Delegate to the service layer.
        final Order updatedInstance = orderService.updateOrder(customerId, orderId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, whose details have to be retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link Order}.
     */
    @Operation(
            method = "findOrder",
            summary = "Find an existing Order.",
            description = "This API is used to find an existing Order in the system.",
            tags = {OrderApi.API_TAG},
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
                                "Successfully retrieved the details of an existing Order in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Order> findOrder(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId) {
        // Delegate to the service layer.
        final Order matchingInstance = orderService.findOrder(customerId, orderId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to retrieve all instances of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} in the system in
     * a paginated manner.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param page Page number.
     * @param size Page size.
     * @return Response of type {@link ResponseEntity} that holds a page of instances of type Order
     *     based on the provided pagination settings.
     */
    @Operation(
            method = "findAllOrders",
            summary = "Find all Orders.",
            description = "This API is used to find all Orders in the system.",
            tags = {OrderApi.API_TAG},
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
                                "Successfully retrieved the Orders in the system based on the provided pagination settings.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/customers/{customerId}/orders")
    public ResponseEntity<Page<Order>> findAllOrders(
            @PathVariable(name = "customerId") final Integer customerId,
            @RequestParam(name = "page", required = false, defaultValue = "0") final Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
                    final Integer size) {
        // Delegate to the service layer.
        final Pageable pageSettings = PageUtils.createPaginationConfiguration(page, size);
        final Page<Order> matchingInstances = orderService.findAllOrders(customerId, pageSettings);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstances);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param orderId Unique identifier of Order in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity} that
     *     was deleted from the system.
     */
    @Operation(
            method = "deleteOrder",
            summary = "Delete an existing Order.",
            description = "This API is used to delete an existing Order in the system.",
            tags = {OrderApi.API_TAG},
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
                        description = "Successfully deleted an existing Order in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/customers/{customerId}/orders/{orderId}")
    public ResponseEntity<Integer> deleteOrder(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "orderId") final Integer orderId) {
        // Delegate to the service layer.
        final Integer deletedInstance = orderService.deleteOrder(customerId, orderId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
