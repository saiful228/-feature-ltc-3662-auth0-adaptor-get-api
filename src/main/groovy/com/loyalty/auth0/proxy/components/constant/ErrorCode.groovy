package com.loyalty.auth0.proxy.components.constant

enum ErrorCode {

    REQUEST_OBJECT("InvalidRequestObject")

    private final String value

    private ErrorCode(final String key) {
        this.value = key
    }

    String getValue() {
        this.value
    }

}
