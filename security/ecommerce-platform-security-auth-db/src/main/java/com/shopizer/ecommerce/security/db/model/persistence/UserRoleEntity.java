/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.security.db.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.shopizer.ecommerce.commons.data.jpa.persistence.AbstractUUIDGeneratedEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "role" table in the database to an entity in the ORM world.
 *
 * @author Murali Krishna Uppala
 */
@ToString(of = {"name"},callSuper = true)
@EqualsAndHashCode(of = {"name"},callSuper = true)
@Getter
@Setter
@Table(name = "role")
@Entity
public class UserRoleEntity extends AbstractUUIDGeneratedEntity {
    /** Name of the role. */
    @Column(name = "name", unique = true, length = 64, nullable = false)
    private String name;

    /** Description of the role. */
    @Column(name = "description", length = 128)
    private String description;
}
