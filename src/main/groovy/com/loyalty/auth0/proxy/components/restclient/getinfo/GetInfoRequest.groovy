package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.constant.Channel
import com.loyalty.auth0.proxy.components.util.TestDataUtils
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpGet

class GetInfoRequest {

    String cardNumber
    String token
    String channel = Channel.WEB.getValue()



    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        TestDataUtils.mapToJson(getBodyMap())
    }


    Map getBodyMap () {
        Map bodyMap = [
                cardNumber   : cardNumber,
                token    : token
        ]
        bodyMap
    }

    HttpGet getRequest(String uri) {
        String fullUri = uri + cardNumber
        HttpGet request = new HttpGet(fullUri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        request.addHeader("channel", channel)
        request
    }
}