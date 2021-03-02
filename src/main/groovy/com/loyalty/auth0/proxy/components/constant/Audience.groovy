package com.loyalty.auth0.proxy.components.constant

enum Audience {

    WEB_COLLECTOR("airmiles-web-collector"),
    CREATE_TEST_DATA("https://airmiles-v2-dev.airmiles-v2-dev.auth0.com/api/v2/")

    private final String value

    private Audience(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
