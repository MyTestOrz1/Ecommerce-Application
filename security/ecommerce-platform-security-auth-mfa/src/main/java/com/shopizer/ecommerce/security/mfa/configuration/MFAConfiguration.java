/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.security.mfa.configuration;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.shopizer.ecommerce.security.mfa.EnableMFA;
import com.shopizer.ecommerce.security.mfa.configuration.properties.TotpProperties;

/**
 * Configuration class that is responsible for configuration of the beans required for Multi-Factor Authentication (i.e.
 * MFA).
 * <p>
 * There are two ways in which an application can leverage MFA functionality.
 * <p>
 * First approach involves using the @{@link org.springframework.context.annotation.Import} annotation and provide this
 * configuration class to initialize the beans required for MFA functionality.
 * <p>
 * Second approach is to use the @{@link EnableMFA} annotation, which takes care of initializing the necessary beans.
 *
 * @author Murali Krishna Uppala
 */
@EnableConfigurationProperties(TotpProperties.class)
@ComponentScan(basePackageClasses = {EnableMFA.class})
@Configuration
public class MFAConfiguration {
}
