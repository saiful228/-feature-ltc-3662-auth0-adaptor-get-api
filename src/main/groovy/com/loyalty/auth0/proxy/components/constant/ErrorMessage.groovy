package com.loyalty.auth0.proxy.components.constant

enum ErrorMessage {

    UNATHORIZED("Unauthorized"),
    FORBIDDEN("User is not authorized to access this resource with an explicit deny"),
    EMAIL_INVALID("the email attribute in the request is invalid"),
    PROFILE_NOT_FOUND("Profile Not Found")

    private final String value

    private ErrorMessage(final String channel) {
        this.value = channel
    }

    String getValue() {
        return this.value
    }

}
