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

import com.shopizer.ecommerce.features.platform.data.mapper.decorator.UserMapperDecorator;
import com.shopizer.ecommerce.features.platform.data.model.experience.user.CreateUserRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.user.UpdateUserRequest;
import com.shopizer.ecommerce.features.platform.data.model.experience.user.User;
import com.shopizer.ecommerce.features.platform.data.model.persistence.UserEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link UserEntity to {@link User and vice-versa.
 *
 * @author Murali Krishna Uppala
 */
@DecoratedWith(value = UserMapperDecorator.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    /**
     * This method transforms an instance of type {@link CreateUserRequest} to an instance of type
     * {@link UserEntity}.
     *
     * @param source Instance of type {@link CreateUserRequest} that needs to be transformed to
     *     {@link UserEntity}.
     * @return Instance of type {@link UserEntity}.
     */
    @Mapping(source = "roles", target = "roles", ignore = true)
    UserEntity transform(CreateUserRequest source);

    /**
     * This method transforms an instance of type {@link UserEntity} to an instance of type {@link
     * User}.
     *
     * @param source Instance of type {@link UserEntity} that needs to be transformed to {@link
     *     User}.
     * @return Instance of type {@link User}.
     */
    @Mapping(source = "roles", target = "roles", ignore = true)
    User transform(UserEntity source);

    /**
     * This method converts / transforms the provided collection of {@link UserEntity} instances to
     * a collection of instances of type {@link User}.
     *
     * @param source Instance of type {@link UserEntity} that needs to be transformed to {@link
     *     User}.
     * @return Collection of instance of type {@link User}.
     */
    default Collection<User> transformListTo(Collection<UserEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateUserRequest} to an instance of type
     * {@link UserEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateUserRequest} that needs to be transformed to
     *     {@link UserEntity}.
     * @param target Instance of type {@link UserEntity} that will be updated instead of creating
     *     and returning a new instance.
     */
    @Mapping(source = "roles", target = "roles", ignore = true)
    void transform(UpdateUserRequest source, @MappingTarget UserEntity target);

    /**
     * This method transforms an instance of type {@link UpdateUserRequest} to an instance of type
     * {@link UserEntity}.
     *
     * @param source Instance of type {@link UpdateUserRequest} that needs to be transformed to
     *     {@link UserEntity}.
     * @return Instance of type {@link UserEntity}.
     */
    @Mapping(source = "roles", target = "roles", ignore = true)
    UserEntity transform(UpdateUserRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateUserRequest}
     * instances to a collection of instances of type {@link UserEntity}.
     *
     * @param source Instance of type {@link UpdateUserRequest} that needs to be transformed to
     *     {@link UserEntity}.
     * @return Instance of type {@link UserEntity}.
     */
    default Collection<UserEntity> transformList(Collection<UpdateUserRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
