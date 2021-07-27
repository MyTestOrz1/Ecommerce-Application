/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons.web.model.experience;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A data transfer object that is used to return the authentication response and typically contains the Bearer token
 * details.
 *
 * @author Murali Krishna Uppala
 */
@ToString(of = {"accessToken"})
@EqualsAndHashCode(of = {"accessToken"})
@Data
@Builder
public class AuthenticationResponse {
    /** Access token. */
    private String accessToken;
}
