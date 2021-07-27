/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of ecommerce-service.
 *
 * ecommerce-service project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class that is responsible to start the service and exposes the functionality over the specified
 * port.
 *
 * @author Murali Krishna Uppala
 */
@SpringBootApplication
public class EcommercePlatformService {
    /**
     * Entry point method.
     *
     * @param args
     *         Arguments to the main program.
     */
    public static void main(String[] args) {
        SpringApplication.run(EcommercePlatformService.class);
    }
}
