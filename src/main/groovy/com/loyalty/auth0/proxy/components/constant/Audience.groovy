package com.loyalty.auth0.proxy.components.constant

enum Audience {

    WEB_COLLECTOR("airmiles-web-collector")

    private final String value

    private Audience(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
