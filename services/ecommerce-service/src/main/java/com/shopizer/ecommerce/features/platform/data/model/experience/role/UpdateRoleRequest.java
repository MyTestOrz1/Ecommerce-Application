/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.experience.role;

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
public class UpdateRoleRequest {
    /** Unique identifier of the role. */
    @NotNull(message = "role.id.not.null.message")
    private Integer id;

    /** Name of the role, which is unique across the Vizr Platform. */
    @NotBlank(message = "role.name.not.blank.message")
    @Size(max = 64, message = "role.name.size.message")
    private String name;

    /** Brief description about the role. */
    @Size(max = 256, message = "role.description.size.message")
    private String description;

    /** Collection of permissions that are assigned to this role. */
    @NotNull(message = "role.permissions.not.null.message")
    private Collection<Integer> permissions;
}
