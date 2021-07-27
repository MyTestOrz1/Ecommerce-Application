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

import com.shopizer.ecommerce.features.platform.data.model.experience.customer.Address;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.CreateAddressRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.customer.UpdateAddressRequest;
import com.shopizer.ecommerce.features.platform.data.model.persistence.AddressEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link AddressEntity to {@link Address and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AddressMapper {

    /**
     * This method transforms an instance of type {@link CreateAddressRequest} to an instance of
     * type {@link AddressEntity}.
     *
     * @param source Instance of type {@link CreateAddressRequest} that needs to be transformed to
     *     {@link AddressEntity}.
     * @return Instance of type {@link AddressEntity}.
     */
    AddressEntity transform(CreateAddressRequest source);

    /**
     * This method transforms an instance of type {@link AddressEntity} to an instance of type
     * {@link Address}.
     *
     * @param source Instance of type {@link AddressEntity} that needs to be transformed to {@link
     *     Address}.
     * @return Instance of type {@link Address}.
     */
    Address transform(AddressEntity source);

    /**
     * This method converts / transforms the provided collection of {@link AddressEntity} instances
     * to a collection of instances of type {@link Address}.
     *
     * @param source Instance of type {@link AddressEntity} that needs to be transformed to {@link
     *     Address}.
     * @return Collection of instance of type {@link Address}.
     */
    default Collection<Address> transformListTo(Collection<AddressEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateAddressRequest} to an instance of
     * type {@link AddressEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateAddressRequest} that needs to be transformed to
     *     {@link AddressEntity}.
     * @param target Instance of type {@link AddressEntity} that will be updated instead of creating
     *     and returning a new instance.
     */
    void transform(UpdateAddressRequest source, @MappingTarget AddressEntity target);

    /**
     * This method transforms an instance of type {@link UpdateAddressRequest} to an instance of
     * type {@link AddressEntity}.
     *
     * @param source Instance of type {@link UpdateAddressRequest} that needs to be transformed to
     *     {@link AddressEntity}.
     * @return Instance of type {@link AddressEntity}.
     */
    AddressEntity transform(UpdateAddressRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateAddressRequest}
     * instances to a collection of instances of type {@link AddressEntity}.
     *
     * @param source Instance of type {@link UpdateAddressRequest} that needs to be transformed to
     *     {@link AddressEntity}.
     * @return Instance of type {@link AddressEntity}.
     */
    default Collection<AddressEntity> transformList(Collection<UpdateAddressRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
