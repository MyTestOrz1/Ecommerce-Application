/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of ecommerce-service.
 *
 * ecommerce-service project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.configuration;

import com.shopizer.ecommerce.security.token.JwtTokenProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import lombok.extern.slf4j.Slf4j;

import com.shopizer.ecommerce.commons.web.filter.JwtAuthenticationFilter;
import com.shopizer.ecommerce.commons.web.security.AbstractWebSecurityConfigurerAdapter;
import com.shopizer.ecommerce.security.properties.ApplicationSecurityProperties;


/**
 * Configuration class responsible to configure the security aspects for the microservice / application in
 * consideration.
 *
 * @author Murali Krishna Uppala
 */
@Slf4j
@Order(SecurityProperties.BASIC_AUTH_ORDER - 2)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends AbstractWebSecurityConfigurerAdapter {
    /**
     * Constructor.
     *
     * @param applicationSecurityProperties
     *         Configurable properties instance of type {@link ApplicationSecurityProperties}.
     * @param jwtAuthenticationFilter
     *         Filter instance of type {@link JwtAuthenticationFilter}.
     */
    public WebSecurityConfiguration(final ApplicationSecurityProperties applicationSecurityProperties,
                                    final JwtAuthenticationFilter jwtAuthenticationFilter, final JwtTokenProvider jwtTokenProvider) {
        super(applicationSecurityProperties, jwtAuthenticationFilter,jwtTokenProvider);
    }
}