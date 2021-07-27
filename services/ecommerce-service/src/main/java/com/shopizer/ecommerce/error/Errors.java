/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of ecommerce-service.
 *
 * ecommerce-service project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.error;

import com.shopizer.ecommerce.commons.error.ErrorMessages;
import com.shopizer.ecommerce.commons.error.IError;
import com.shopizer.ecommerce.commons.error.IErrorMessages;

/**
 * Enum constants that represent the API error codes and messages that can be used across the application.
 * <p>
 * For more details, see the documentation on {@link IError} contract.
 *
 * @author Murali Krishna Uppala
 */
public enum Errors implements IError {
    // NOTE:
    // Whenever a new constant is added here, ensure that the error message for the same constant is added in
    // src/main/resources/l10n/error_messages.properties

    RESOURCE_NOT_FOUND,
    RESOURCE_NOT_FOUND_WITH_ID;

    /** Reference to {@link IErrorMessages}, which holds the error messages. */
    private static final ErrorMessages ERROR_MESSAGES = ErrorMessages.instance("l10n/error_messages", Errors.class.getClassLoader());

    @Override
    public IErrorMessages getErrorMessages() {
        return Errors.ERROR_MESSAGES;
    }
}
