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
import com.shopizer.ecommerce.commons.exception.ServiceException;
import com.shopizer.ecommerce.commons.instrumentation.Instrument;
import com.shopizer.ecommerce.error.Errors;
import com.shopizer.ecommerce.features.platform.data.mapper.LineItemMapper;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.CreateLineItemRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.LineItem;
import com.shopizer.ecommerce.features.platform.data.model.experience.lineitem.UpdateLineItemRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.CustomerEntity;
import com.shopizer.ecommerce.features.platform.data.model.persistence.LineItemEntity;
import com.shopizer.ecommerce.features.platform.data.model.persistence.OrderEntity;
import com.shopizer.ecommerce.features.platform.data.repository.CustomerRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link LineItemEntity}.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@Validated
@Service
public class LineItemService {

    /** Repository implementation of type {@link CustomerRepository}. */
    private final CustomerRepository customerRepository;

    /**
     * Mapper implementation of type {@link LineItemMapper} to transform between different types.
     */
    private final LineItemMapper lineItemMapper;

    /**
     * Constructor.
     *
     * @param customerRepository Repository instance of type {@link CustomerRepository}.
     * @param lineItemMapper Mapper instance of type {@link LineItemMapper}.
     */
    public LineItemService(
            final CustomerRepository customerRepository, final LineItemMapper lineItemMapper) {

        this.customerRepository = customerRepository;
        this.lineItemMapper = lineItemMapper;
    }

    /**
     * This method attempts to create an instance of type {@link LineItemEntity} in the system based
     * on the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     LineItemEntity}.
     * @return An experience model of type {@link LineItem} that represents the newly created entity
     *     of type {@link LineItemEntity}.
     */
    @Instrument
    @Transactional
    public LineItem createLineItem(
            final Integer customerId,
            final Integer orderId,
            @Valid final CreateLineItemRequest payload) {
        // 0. Validate the dependencies.

        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        final OrderEntity matchingOrder =
                matchingCustomer
                        .findOrderById(orderId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, orderId));
        // 1. Transform the experience model to a persistence model.
        final LineItemEntity lineItemEntity = lineItemMapper.transform(payload);

        // 2. Add the mapped model to parent model.
        matchingOrder.addLineItem(lineItemEntity);

        // 3. Save the entity.
        LineItemService.LOGGER.debug("Saving added instance of type - LineItemEntity");
        final CustomerEntity updatedInstance = customerRepository.save(matchingCustomer);

        // 4. Transform and return.
        return lineItemMapper.transform(matchingOrder.getLatestLineItem());
    }

    /**
     * This method attempts to update an existing instance of type {@link LineItemEntity} using the
     * details from the provided input, which is an instance of type {@link UpdateLineItemRequest}.
     *
     * @param lineItemId Unique identifier of LineItem in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing LineItem, which needs to
     *     be updated in the system.
     * @return A instance of type {@link LineItem} containing the updated details.
     */
    @Instrument
    @Transactional
    public LineItem updateLineItem(
            final Integer customerId,
            final Integer orderId,
            final Integer lineItemId,
            @Valid final UpdateLineItemRequest payload) {

        // 0. Validate the dependencies.

        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        final OrderEntity matchingOrder =
                matchingCustomer
                        .findOrderById(orderId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, orderId));

        // 1. Verify that the entity being updated truly exists.
        final LineItemEntity matchingInstance =
                matchingOrder
                        .findLineItemById(lineItemId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, lineItemId));

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        lineItemMapper.transform(payload, matchingInstance);

        // 3. Save the entity
        LineItemService.LOGGER.debug("Saving the updated entity - CustomerEntity");
        final CustomerEntity updatedInstance = customerRepository.save(matchingCustomer);

        // 4. Transform updated entity to output object
        return lineItemMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find a {@link LineItemEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param lineItemId Unique identifier of LineItem in the system, whose details have to be
     *     retrieved.
     * @return Matching entity of type {@link LineItem} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public LineItem findLineItem(
            final Integer customerId, final Integer orderId, final Integer lineItemId) {

        // 0. Validate the dependencies.

        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        final OrderEntity matchingOrder =
                matchingCustomer
                        .findOrderById(orderId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, orderId));

        // 1. Verify that the entity being updated truly exists.
        final LineItemEntity matchingLineItem =
                matchingOrder
                        .findLineItemById(lineItemId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, lineItemId));

        // 2. Transform updated entity to output object
        return lineItemMapper.transform(matchingLineItem);
    }

    /**
     * This method attempts to find instances of type LineItemEntity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link LineItem}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<LineItem> findAllLineItems(
            final Integer customerId, final Integer orderId, final Pageable page) {
        // 0. Validate the dependencies.

        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        final OrderEntity matchingOrder =
                matchingCustomer
                        .findOrderById(orderId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, orderId));

        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        LineItemService.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<LineItemEntity> pageData =
                new PageImpl(
                        (List) matchingOrder.getLineItems(),
                        pageSettings,
                        matchingOrder.getLineItems().size());

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<LineItem> dataToReturn =
                    pageData.getContent().stream()
                            .map(lineItemMapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }
        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link LineItemEntity} whose
     * unique identifier matches the provided identifier.
     *
     * @param lineItemId Unique identifier of LineItem in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type LineItemEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteLineItem(
            final Integer customerId, final Integer orderId, final Integer lineItemId) {

        // 0. Validate the dependencies.

        final CustomerEntity matchingCustomer =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, customerId));

        final OrderEntity matchingOrder =
                matchingCustomer
                        .findOrderById(orderId)
                        .orElseThrow(
                                () ->
                                        ServiceException.instance(
                                                Errors.RESOURCE_NOT_FOUND_WITH_ID, orderId));

        // 1. Remove the matchingInstance form its parent.
        matchingOrder.deleteLineItemById(lineItemId);

        // 2. Persist the parentInstance.
        customerRepository.save(matchingCustomer);

        // 3. Return the deleted identifier.
        return lineItemId;
    }
}
