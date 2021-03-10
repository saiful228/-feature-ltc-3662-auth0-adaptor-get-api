package com.loyalty.auth0.proxy.components.restclient.createdata

import com.loyalty.auth0.proxy.components.constant.Constants
import com.loyalty.auth0.proxy.components.util.TestDataUtils
import org.apache.http.HttpEntity
import org.apache.http.HttpHeaders
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.ByteArrayEntity

//import org.springframework.http.HttpEntity

class CreateDataRequest {

    String email
    Boolean emailVerified
    Boolean blocked
    String userId
    String connection
    String password
    String token

    String cardNumber


    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        TestDataUtils.mapToJson(getBodyMap())
    }

    Map getBodyMap () {
        Map bodyMap = [
                email: email,
                email_verified: emailVerified,
                blocked: blocked,
                user_id: userId,
                connection: connection,
                password: password
        ]
        bodyMap
    }

    HttpPost getPostRequest(String uri) {
        HttpEntity entity = new ByteArrayEntity(toJson().getBytes(Constants.UTF_8))
        HttpPost httpRequest = new HttpPost(uri)
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        httpRequest.setHeader(HttpHeaders.ACCEPT, "*/*" )
        httpRequest.setEntity(entity)
        httpRequest
    }

    def setDefaultValues(String token) {
        this.token = token
        this.cardNumber = TestDataUtils.getRandomCardNumber()
        this.email = TestDataUtils.getRandomValidEmail()
        this.emailVerified = true
        this.blocked = false
        this.userId = Constants.USER_ID_PREFIX_CREATE + cardNumber
        this.connection = Constants.DEFAULT_CONNECTION_CREATE_DATA
        this.password = TestDataUtils.getRandomPassword()
    }

}