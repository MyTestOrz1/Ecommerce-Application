/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons.properties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Default implementation of a properties provider where the key is of type {@link String} and value is of type {@link
 * Object}.
 *
 * @author Murali Krishna Uppala
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(callSuper = true, of = {})
@EqualsAndHashCode(callSuper = true, of = {})
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class DefaultPropertiesProvider extends AbstractPropertiesProvider<String, Object> {
}
