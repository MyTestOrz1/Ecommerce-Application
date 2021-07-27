/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.security;

import com.shopizer.ecommerce.security.db.EnableDBAuthentication;
import com.shopizer.ecommerce.security.properties.ApplicationSecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
/**
 * Main application class that is responsible to start the db auth service and exposes the functionality over the specified
 * port.
 *
 * @author Murali Krishna Uppala
 */
@EnableConfigurationProperties(value ={ApplicationSecurityProperties.class})
@EnableDBAuthentication
@SpringBootApplication
public class ExampleSecurityAuthDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSecurityAuthDbApplication.class, args);
	}

}