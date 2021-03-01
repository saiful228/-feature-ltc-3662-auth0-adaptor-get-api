package com.loyalty.auth0.proxy.components.validator

import com.loyalty.auth0.proxy.components.util.Logger


class BaseValidator {


    static boolean validateStringValues(String actual, String expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual, expected, fieldName)
        if(!actual.equalsIgnoreCase(expected)) {
            Logger.logEventValidationError(fieldName, expected, actual)
            result = false
        }
        result
    }

    static boolean validateNumericValues(def actual, def expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual.toString(), expected.toString(), fieldName)
        if( actual!=expected ) {
            Logger.logEventValidationError(fieldName, expected.toString(), actual.toString())
            result = false
        }
        result
    }

    static boolean validateDateValues(def actual, def expected, String fieldName) {
        boolean result = true
        Logger.logValidationValues(actual.toString(), expected.toString(), fieldName)
        if( !actual.isEqual(expected) ) {
            Logger.logEventValidationError(fieldName, expected.toString(), actual.toString())
            result = false
        }
        result
    }
}
