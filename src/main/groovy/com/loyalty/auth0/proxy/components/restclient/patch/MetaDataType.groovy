package com.loyalty.auth0.proxy.components.restclient.patch

import com.loyalty.auth0.proxy.components.util.TestDataUtils

class MetaDataType {

    String key
    String value

    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        TestDataUtils.mapToJson(getBodyMap())
    }

    Map getBodyMap () {
        Map bodyMap = [
                key   : key,
                value : value
        ]
        bodyMap
    }

    def setDefaultValues() {
        this.key = TestDataUtils.getRandomString(3)
        this.value = TestDataUtils.getRandomString(11)
    }
}