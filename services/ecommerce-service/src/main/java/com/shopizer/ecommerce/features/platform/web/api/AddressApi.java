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

import com.shopizer.ecommerce.commons.web.api.AbstractApi;
import com.shopizer.ecommerce.commons.web.configuration.properties.ApiDocumentationSettings;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.Address;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.CreateAddressRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.UpdateAddressRequest;
import com.shopizer.ecommerce.features.platform.web.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implementation of APIs that provide CRUD (Create, Read, Update and Delete) functionality for
 * persistence models of type {@link
 * com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@RestController
public class AddressApi extends AbstractApi {
    /** Tag for this API. */
    public static final String API_TAG = "Addresses";

    /** Service implementation of type {@link AddressService}. */
    private final AddressService addressService;

    /**
     * Constructor.
     *
     * @param addressService Service instance of type {@link AddressService}.
     */
    public AddressApi(final AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * This API provides the capability to add a new instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity} into the
     * system.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity}.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Address}.
     */
    @Operation(
            method = "createAddress",
            summary = "Create a new Address.",
            description = "This API is used to create a new Address in the system.",
            tags = {AddressApi.API_TAG},
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
                        description = "Successfully created a new Address in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping(value = "/customers/{customerId}/addresses")
    public ResponseEntity<Address> createAddress(
            @PathVariable(name = "customerId") final Integer customerId,
            @Valid @RequestBody final CreateAddressRequest payload) {
        // Delegate to the service layer.
        final Address newInstance = addressService.createAddress(customerId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstance);
    }

    /**
     * This API provides the capability to update an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Address, which needs to
     *     be updated in the system.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Address}.
     */
    @Operation(
            method = "updateAddress",
            summary = "Update an existing Address.",
            description = "This API is used to update an existing Address in the system.",
            tags = {AddressApi.API_TAG},
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
                        description = "Successfully updated an existing Address in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @PutMapping(value = "/customers/{customerId}/addresses/{addressId}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "addressId") final Integer addressId,
            @Valid @RequestBody final UpdateAddressRequest payload) {
        // Delegate to the service layer.
        final Address updatedInstance =
                addressService.updateAddress(customerId, addressId, payload);

        // Build a response entity object and return it.
        return ResponseEntity.ok(updatedInstance);
    }

    /**
     * This API provides the capability to retrieve the details of an existing {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, whose details have to be
     *     retrieved.
     * @return Response of type {@link ResponseEntity} that wraps an instance of type {@link
     *     Address}.
     */
    @Operation(
            method = "findAddress",
            summary = "Find an existing Address.",
            description = "This API is used to find an existing Address in the system.",
            tags = {AddressApi.API_TAG},
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
                                "Successfully retrieved the details of an existing Address in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping(value = "/customers/{customerId}/addresses/{addressId}")
    public ResponseEntity<Address> findAddress(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "addressId") final Integer addressId) {
        // Delegate to the service layer.
        final Address matchingInstance = addressService.findAddress(customerId, addressId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(matchingInstance);
    }

    /**
     * This API provides the capability to delete an existing instance of type {@link
     * com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity} in the system.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, which needs to be deleted.
     * @return Response of type {@link ResponseEntity} that holds the unique identifier of the
     *     {@link com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity}
     *     that was deleted from the system.
     */
    @Operation(
            method = "deleteAddress",
            summary = "Delete an existing Address.",
            description = "This API is used to delete an existing Address in the system.",
            tags = {AddressApi.API_TAG},
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
                        description = "Successfully deleted an existing Address in the system.",
                        content = @Content),
                @ApiResponse(
                        responseCode = "403",
                        description = "You do not have permissions to perform this operation.",
                        content = @Content)
            })
    @PreAuthorize(value = "isAuthenticated()")
    @DeleteMapping(value = "/customers/{customerId}/addresses/{addressId}")
    public ResponseEntity<Integer> deleteAddress(
            @PathVariable(name = "customerId") final Integer customerId,
            @PathVariable(name = "addressId") final Integer addressId) {
        // Delegate to the service layer.
        final Integer deletedInstance = addressService.deleteAddress(customerId, addressId);

        // Build a response entity object and return it.
        return ResponseEntity.ok(deletedInstance);
    }
}
