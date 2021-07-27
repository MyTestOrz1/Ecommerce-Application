/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons.exception;

import com.shopizer.ecommerce.commons.error.CommonErrors;

/**
 * Specialized exception implementation to handle the cases where a resource is not found.
 * <p>
 * This exception is generally used in the Service layer to report the cases where a resource is not found. For example:
 * find a specific user, get the details of a specific user, etc.
 *
 * @author Murali Krishna Uppala
 */
public class ResourceNotFoundException extends ServiceException {
    /**
     * Constructor.
     */
    public ResourceNotFoundException() {
        super(CommonErrors.RESOURCE_NOT_FOUND);
    }
}
