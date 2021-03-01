package com.loyalty.auth0.proxy.components.restclient.gettoken

import com.loyalty.auth0.proxy.components.util.TestDataUtils
import groovy.json.JsonSlurper
import org.apache.http.HttpResponse


class AuthResponse {
    HttpResponse httpResponse
    String accessToken
    String scope
    Integer expiresIn
    String tokenType
    Integer status
    def body

    String toJson() {
        Map bodyMap = [
                accessToken: accessToken,
                scope: scope,
                expir: expiresIn,
                tokenType: tokenType,
                status: status

        ]

        TestDataUtils.mapToJson(bodyMap)
    }

    def setHttpResponse(HttpResponse response) {
        this.httpResponse = response
        this.body = TestDataUtils.getResponseBody(httpResponse)
        this.status = httpResponse.getStatusLine().getStatusCode()
        parseRequest(body)
    }

    def parseRequest(String jsonString) {
        JsonSlurper parser = new JsonSlurper()
        def result = parser.parseText(jsonString)
        this.accessToken = result.access_token
        this.scope = result.scope
        this.expiresIn = result.expires_in
        this.tokenType = result.token_type
    }

}
