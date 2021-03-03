package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.util.TestDataUtils
import org.apache.http.HttpHeaders
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.message.BasicNameValuePair

import java.nio.charset.StandardCharsets

//import org.springframework.http.HttpEntity

class GetInfoRequest {

    String cardNumber
    String token



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
        request
    }
}