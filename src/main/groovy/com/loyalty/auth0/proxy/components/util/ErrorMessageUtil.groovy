package com.loyalty.auth0.proxy.components.util

import groovy.json.JsonOutput


class ErrorMessageUtil {
    String message
    String errorCode
    String parameter


    @Override
    String toString() {
        Map parameters = [:]
        if(message!= null && message.length() > 0 ) {parameters["message"] = message}
        if(errorCode!= null && errorCode.length() > 0 ) {parameters["code"] = errorCode}
        if(parameter!= null && parameter.length() > 0 ) {parameters["param"] = parameter}
        JsonOutput.toJson(parameters)
    }

    String getUnathorizedError() {
        JsonOutput.toJson(
            [
                message: message
            ]
        )
    }

    String getBadRequestError() {
        JsonOutput.toJson(
                [
                        code: errorCode,
                        param: parameter,
                        message: message
                ]
        )
    }

    String getProfileNotFoundError() {
        JsonOutput.toJson(
                [
                        error: message
                ]
        )
    }
}
