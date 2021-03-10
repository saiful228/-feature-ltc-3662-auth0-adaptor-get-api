package com.loyalty.auth0.proxy.components.constant

enum GrantType {

    CLIENT_CREDENTIALS("client_credentials")

    private final String value

    private GrantType(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
