package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.util.TestDataUtils
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse

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
        parseResponse(body)
    }

    def parseResponse(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        this.type = result.type
        this.userId = result.user_id
        this.username = result.username
        this.email = result.email
        this.emailVerified = result.email_verified
        this.createdAt = result.created_at
        this.updatedAt = result.updated_at
        this.lastIp = result.lastIp
        this.lastLogin = result.last_login
        this.loginCount = result.logins_count
        this.blocked = result.blocked
        this.metaDataKey = result.meta_data.key
        this.metaDataValue = result.meta_data.value
    }

}
