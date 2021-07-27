/*
 * Copyright (c) 2020 Shopizer All rights reserved.
 *
 * This file is part of Ecommerce platform.
 *
 * Shopizer Ecommerce Platform and associated code cannot be copied and/or
 * distributed without a written permission of Shopizer.,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.features.platform.data.repository;

import com.shopizer.ecommerce.commons.data.jpa.repository.ExtendedJpaRepository;
import com.shopizer.ecommerce.features.platform.data.model.persistence.CustomerEntity;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to handle the operations pertaining to domain models of type
 * "CustomerEntity".
 *
 * @author Murali Krishna Uppala
 */
@Repository
public interface CustomerRepository extends ExtendedJpaRepository<CustomerEntity, Integer> {
    // Any custom methods can be added here.
}
