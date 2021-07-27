/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons;

/**
 * Enum that contains the field / parameter names used across the application (either in log statements, exceptions,
 * etc.).
 * <p>
 * Instead of hardcoding the field / parameter names across the application, they are collated in this single place
 * which makes it usable across the application.
 *
 * @author Murali Krishna Uppala
 */
public enum Name {
    API_KEY("apiKey"),
    DESCRIPTION("description"),
    EXAMPLE("example"),
    ID("id"),
    IDS("ids"),
    NAME("name"),
    PAYLOAD("payload"),
    PERMISSION("permission"),
    PRINCIPAL_ID("principalId"),
    ROLE("role"),
    ROLES("roles"),
    USERNAME("username"),
    USER_ID("userId"),
    USER_PRINCIPAL("userPrincipal");

    /** Internal name of the field. */
    private final String key;

    /**
     * Constructor.
     *
     * @param key
     *         Internal name of the field.
     */
    Name(final String key) {
        this.key = key;
    }

    /**
     * Returns the internal name of the field.
     *
     * @return Internal name of the field.
     */
    public String key() {
        return key;
    }
}
