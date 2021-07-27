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
 * Implementation that maps the "user" table in the database to an entity in the ORM world.
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
@Table(name = "user")
@Entity
public class UserEntity extends AbstractIdGeneratedEntity<Integer> {

    /** User name of the user. */
    @Column(name = "username", nullable = false, length = 32)
    private String username;

    /** Password of the user. */
    @Column(name = "password", nullable = false)
    private String password;

    /** Reference to the roles. */
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Collection<RoleEntity> roles;
}
