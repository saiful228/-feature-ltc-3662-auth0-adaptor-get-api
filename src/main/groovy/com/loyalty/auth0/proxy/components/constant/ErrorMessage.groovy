package com.loyalty.auth0.proxy.components.constant

enum ErrorMessage {

    UNATHORIZED("Unauthorized")

    private final String value

    private ErrorMessage(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
