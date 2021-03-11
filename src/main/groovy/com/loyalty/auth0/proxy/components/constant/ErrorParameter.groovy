package com.loyalty.auth0.proxy.components.constant

enum ErrorParameter {

    EMAIL("email")

    private final String value

    private ErrorParameter(final String key) {
        this.value = key
    }

    String getValue() {
        this.value
    }

}
