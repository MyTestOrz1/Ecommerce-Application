/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.experience.permission;

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
public class UpdatePermissionRequest {
    /** Unique identifier of the permission. */
    @NotNull(message = "permission.id.not.null.message")
    private Integer id;

    /** Unique identifiable code for the permission (e.g. CREATE_TENANT_USER). */
    @Size(max = 64, message = "permission.code.size.message")
    private String code;

    /** Brief description for the permission. */
    @Size(max = 256, message = "permission.description.size.message")
    private String description;
}
