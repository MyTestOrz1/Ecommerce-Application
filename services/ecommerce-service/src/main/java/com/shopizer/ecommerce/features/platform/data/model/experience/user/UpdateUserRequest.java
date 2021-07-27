/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.experience.user;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author Murali Krishna Uppala
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateUserRequest {
    /** Unique identifier of the user. */
    @NotNull(message = "user.id.not.null.message")
    private Integer id;

    /** User name of the user. */
    @NotBlank(message = "user.username.not.blank.message")
    @Size(max = 32, message = "user.username.size.message")
    private String username;

    /** Password of the user. */
    @NotBlank(message = "user.password.not.blank.message")
    private String password;

    /** Reference to the roles. */
    @NotNull(message = "user.roles.not.null.message")
    private Collection<Integer> roles;
}
