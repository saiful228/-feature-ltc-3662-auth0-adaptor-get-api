package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.util.TestDataUtils
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus

class GetInfoResponse {
    HttpResponse httpResponse
    String type
    String userId
    String username
    String email
    Boolean emailVerified
    String createdAt
    String updatedAt
    String lastIp
    String lastLogin
    String loginCount
    Boolean blocked
    String metaDataKey
    String metaDataValue

    Integer status
    String body

    String errorMessage

    @Override
    String toString() {
        toJson()
    }

    String toJson() {
        Map bodyMap = [
                type: type,
                userId: userId,
                username: username,
                email: email,
                emailVerified: emailVerified,
                createdAt: createdAt,
                updatedAt: updatedAt,
                lastIp: lastIp,
                lastLogin: lastLogin,
                loginCount: loginCount,
                blocked: blocked,
                metaDataKey: metaDataKey,
                metaDataValue: metaDataValue
        ]

        TestDataUtils.mapToJson(bodyMap)
    }

    def setHttpResponse(HttpResponse response) {
        this.httpResponse = response
        this.body = TestDataUtils.getResponseBody(httpResponse)
        this.status = httpResponse.getStatusLine().getStatusCode()
        if(status == HttpStatus.SC_OK ) {
            parseResponse(body)
        }

    }

    def parseResponse(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        this.type = result.getAt("users").getAt("type").getAt(0)
        this.userId = result.getAt("users").getAt("user_id").getAt(0)
        this.email = result.getAt("users").getAt("email").getAt(0)
        this.emailVerified = result.getAt("users").getAt("email_verified").getAt(0)
        this.createdAt = result.getAt("users").getAt("created_at").getAt(0)
        this.updatedAt = result.getAt("users").getAt("updated_at").getAt(0)
    }


}
