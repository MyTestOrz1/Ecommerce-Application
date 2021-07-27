/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons.data.persistence;

import java.io.Serializable;

/**
 * Base contract for an entity definition.
 *
 * @param <ID>
 *         Type of the primary key.
 *
 * @author Murali Krishna Uppala
 */
public interface IEntity<ID extends Serializable> extends Serializable {
    /**
     * This method returns a unique identifier (typically value for the primary key) of the corresponding entity.
     *
     * @return Unique identifier of the identity.
     */
    ID getId();

    /**
     * This method returns the display name of the entity.
     *
     * @return Display name of the entity.
     */
    default String displayName() {
        final String className = getClass().getSimpleName();
        final int entitySuffixPosition = className.indexOf("Entity");
        if (entitySuffixPosition > 0) {
            return className.substring(0, entitySuffixPosition);
        }
        return className;
    }
}
