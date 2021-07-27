/*
 * Copyright (c) 2021 DruvStar. All rights reserved.
 *
 * This file is part of Ecommerce-Application.
 *
 * Ecommerce-Application project and associated code cannot be copied
 * and/or distributed without a written permission of DruvStar,
 * and/or its subsidiaries.
 */
package com.shopizer.ecommerce.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum that contains the constants used across the application (e.g. DOT, COMMA, etc.).
 *
 * @author Murali Krishna Uppala
 */
@Getter
@AllArgsConstructor
public enum SpecialCharacter implements IEnumValueProvider<SpecialCharacter> {
    AMPERSAND("&"),
    ARROW("->"),
    BACKWARD_SLASH("\\"),
    CLOSE_PARENTHESIS(")"),
    CLOSE_SQUARE_BRACKET("]"),
    COLON(":"),
    COMMA(","),
    COMMA_SPACE(", "),
    DASH("-"),
    DOLLAR("$"),
    DOT("."),
    DOT_IN_REGEXP("\\."),
    FORWARD_SLASH("/"),
    OPEN_PARENTHESIS("("),
    OPEN_SQUARE_BRACKET("["),
    QUESTION_MARK("?"),
    SEMI_COLON(";"),
    SPACE(" "),
    UNDERSCORE("_");

    /** Value for this enum constant. */
    private final String value;
}
