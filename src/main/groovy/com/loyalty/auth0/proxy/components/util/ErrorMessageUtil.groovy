package com.loyalty.auth0.proxy.components.util

import groovy.json.JsonOutput


class ErrorMessageUtil {
    String message
    String errorCode


    @Override
    String toString() {
        Map parameters = [:]
        if(message!= null && message.length() > 0 ) {parameters["message"] = message}
        JsonOutput.toJson(parameters)
    }

    String getGetInfoError() {
        JsonOutput.toJson(
            [
                message: message
            ]
        )
    }

}
