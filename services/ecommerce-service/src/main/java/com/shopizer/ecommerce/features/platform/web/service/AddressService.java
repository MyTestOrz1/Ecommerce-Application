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

import com.shopizer.ecommerce.commons.exception.ServiceException;
import com.shopizer.ecommerce.commons.instrumentation.Instrument;
import com.shopizer.ecommerce.error.Errors;
import com.shopizer.ecommerce.features.platform.data.mapper.AddressMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.Address;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.CreateAddressRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.UpdateAddressRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity;
import com.shopizer.ecommerce.features.platform.data.model.persistence.CustomerEntity;
import com.shopizer.ecommerce.features.platform.data.repository.CustomerRepository;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link AddressEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@Validated
@Service
public class AddressService {

    /** Mapper implementation of type {@link AddressMapper} to transform between different types. */
    private final AddressMapper addressMapper;

    /** Repository implementation of type {@link CustomerRepository}. */
    private final CustomerRepository customerRepository;

    /**
     * Constructor.
     *
     * @param addressMapper Mapper instance of type {@link AddressMapper}.
     * @param customerRepository Repository instance of type {@link CustomerRepository}.
     */
    public AddressService(
            final AddressMapper addressMapper, final CustomerRepository customerRepository) {
        this.addressMapper = addressMapper;
        this.customerRepository = customerRepository;
    }
    /**
     * This method attempts to create an instance of type {@link AddressEntity} in the system based
     * on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     AddressEntity}.
     * @return An experience model of type {@link Address} that represents the newly created entity
     *     of type {@link AddressEntity}.
     */
    @Instrument
    @Transactional
    public Address createAddress(
            final Integer customerId, @Valid final CreateAddressRequest payload) {
        // 0. Validate the dependencies.
        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));
        // 1. Transform the experience model to a persistence model.
        final AddressEntity addressEntity = addressMapper.transform(payload);

        // 2. Add the mapped model to parent model.
        matchingCustomer.setAddress(addressEntity);

        // 3. Save the entity.
        final CustomerEntity updatedInstance = customerRepository.save(matchingCustomer);

        // 4. Transform and return.
        return addressMapper.transform(matchingCustomer.getAddress());
    }

    /**
     * This method attempts to update an existing instance of type {@link AddressEntity} using the
     * details from the provided input, which is an instance of type {@link UpdateAddressRequest}.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Address, which needs to
     *     be updated in the system.
     * @return A instance of type {@link Address} containing the updated details.
     */
    @Instrument
    @Transactional
    public Address updateAddress(
            final Integer customerId,
            final Integer addressId,
            @Valid final UpdateAddressRequest payload) {

        // 0. Validate the dependencies.
        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        // 1. Verify that the entity being updated truly exists.
        final AddressEntity matchingInstance = matchingCustomer.getAddress();

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        addressMapper.transform(payload, matchingInstance);

        // 3. Save the entity
        AddressService.LOGGER.debug("Saving the updated entity - CustomerEntity");
        final CustomerEntity updatedInstance = customerRepository.save(matchingCustomer);

        // 4. Transform updated entity to output object
        return addressMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find a {@link AddressEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link Address} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Address findAddress(final Integer customerId, final Integer addressId) {

        // 0. Validate the dependencies.
        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        // 1. Verify that the entity being updated truly exists.
        final AddressEntity matchingInstance = matchingCustomer.getAddress();

        // 2. Transform updated entity to output object
        return addressMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to delete an existing instance of type {@link AddressEntity} whose
     * unique identifier matches the provided identifier.
     *
     * @param customerId Unique identifier of Customer in the system, whose details have to be
     *     retrieved.
     * @param addressId Unique identifier of Address in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type AddressEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteAddress(final Integer customerId, final Integer addressId) {

        // 0. Validate the dependencies.
        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        // 1. Verify that the entity being deleted truly exists.
        final AddressEntity matchingInstance = matchingCustomer.getAddress();
        if (Objects.isNull(matchingInstance)) {
            throw ServiceException.instance(Errors.RESOURCE_NOT_FOUND);
        }

        // 2. Remove the matchingInstance form its parent.
        matchingCustomer.setAddress(null);

        // 3. Persist the parentInstance.
        customerRepository.save(matchingCustomer);

        // 4. Return the unique identifier of deleted instance.
        return addressId;
    }
}
