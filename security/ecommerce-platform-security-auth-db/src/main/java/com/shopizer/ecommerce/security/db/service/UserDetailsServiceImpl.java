/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.security.db.service;

import org.springframework.stereotype.Service;

import com.shopizer.ecommerce.security.db.repository.UserEntityRepository;


/**
 * Default implementation for {@link org.springframework.security.core.userdetails.UserDetailsService}.
 *
 * @author Murali Krishna Uppala
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl extends AbstractUserDetailsService {

    /**
     * Constructor.
     *
     * @param userEntityRepository
     *         Repository implementation of type {@link UserEntityRepository}.
     */
    public UserDetailsServiceImpl(final UserEntityRepository userEntityRepository) {
        super(userEntityRepository);
    }
}
