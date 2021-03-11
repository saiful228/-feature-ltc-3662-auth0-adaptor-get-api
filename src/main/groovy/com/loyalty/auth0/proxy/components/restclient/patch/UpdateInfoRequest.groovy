package com.loyalty.auth0.proxy.components.restclient.patch

import com.loyalty.auth0.proxy.components.constant.Channel
import com.loyalty.auth0.proxy.components.constant.Constants
import com.loyalty.auth0.proxy.components.util.TestDataUtils
import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpPatch
import org.apache.http.entity.ByteArrayEntity

class UpdateInfoRequest {

    String cardNumber
    String email
    String token
    MetaDataType metaData
    String channel = Channel.WEB.getValue()


    UpdateInfoRequest() {
        metaData = new MetaDataType()
        metaData.setDefaultValues()
    }

    @Override
    String toString() {
        toJson(getAllValuesMap())
    }

    String toJson(Map valuesMap = getBodyMap()) {
        TestDataUtils.mapToJson(valuesMap)
    }

    Map getBodyMap () {
        Map bodyMap = [
                email: email,
                meta_data: metaData.getBodyMap()
        ]
        bodyMap
    }

    Map getAllValuesMap() {
        Map bodyMap = [
                cardNumber: cardNumber,
                email: email,
                meta_data: metaData.getBodyMap(),
                token: token
        ]
        bodyMap
    }

    HttpPatch getRequest(String uri) {
        HttpEntity entity = new ByteArrayEntity(toJson().getBytes(Constants.UTF_8))
        String fullUri = uri + cardNumber
        HttpPatch request = new HttpPatch(fullUri)
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        request.addHeader(HttpHeaders.ACCEPT_CHARSET, "utf-8")
        request.addHeader(HttpHeaders.ACCEPT, "*/*")
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        request.addHeader("channel", channel)
        request.setEntity(entity)
        request
    }
}