/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons.data.jpa.persistence;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.shopizer.ecommerce.commons.data.persistence.IEntity;

/**
 * Abstract implementation of an entity where the value for the primary key is not auto-generated and it is the
 * responsibility of the application using the entity to set the value for the primary key.
 *
 * @param <ID>
 *         Type of the primary key.
 *
 * @author Murali Krishna Uppala
 */
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"})
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable> implements IEntity<ID> {
    /** Unique identifier of the entity - typically a primary key. */
    @Id
    @Column(name = "id", nullable = false)
    private ID id;
}
