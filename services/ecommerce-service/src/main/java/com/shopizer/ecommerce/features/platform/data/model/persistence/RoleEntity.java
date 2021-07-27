/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.model.persistence;

import com.shopizer.ecommerce.commons.data.jpa.persistence.AbstractIdGeneratedEntity;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Implementation that maps the "role" table in the database to an entity in the ORM world.
 *
 * @author Murali Krishna Uppala
 */
@EqualsAndHashCode(
        callSuper = true,
        of = {})
@ToString(
        callSuper = true,
        of = {})
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
@Entity
public class RoleEntity extends AbstractIdGeneratedEntity<Integer> {

    /** Name of the role, which is unique across the Vizr Platform. */
    @Column(name = "name", unique = true, nullable = false, length = 64)
    private String name;

    /** Brief description about the role. */
    @Column(name = "description", length = 256)
    private String description;

    /** Collection of permissions that are assigned to this role. */
    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Collection<PermissionEntity> permissions;
}
