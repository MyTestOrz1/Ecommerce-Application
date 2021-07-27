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

import com.shopizer.ecommerce.features.platform.data.model.experience.permission.Permission;
import java.util.Collection;
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
public class Role {
    /** Unique identifier of the role. */
    private Integer id;

    /** Name of the role, which is unique across the Vizr Platform. */
    private String name;

    /** Brief description about the role. */
    private String description;

    /** Collection of permissions that are assigned to this role. */
    private Collection<Permission> permissions;
}
