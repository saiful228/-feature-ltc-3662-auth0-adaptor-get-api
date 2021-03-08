package com.loyalty.auth0.proxy.components.constant

enum Channel {

    WEB("WEB"),
    MOBILE("MOBILE")

    private final String value

    private Channel(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
