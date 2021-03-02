package com.loyalty.auth0.proxy.specification.getinfo

import com.loyalty.auth0.proxy.components.restclient.createdata.CreateDataClient
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoClient
import com.loyalty.auth0.proxy.components.restclient.gettoken.AuthResponse
import com.loyalty.auth0.proxy.components.restclient.gettoken.GetTokenClient
import com.loyalty.auth0.proxy.components.util.Logger
import com.loyalty.auth0.proxy.components.validator.BaseValidator
import com.loyalty.auth0.proxy.specification.BaseSpecification
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

class GetInfoSpec extends BaseSpecification {

    @Autowired
    GetTokenClient getTokenClient

    @Autowired
    GetInfoClient getInfoClient

    @Autowired
    CreateDataClient createDataClient


    def setup() {
        String createToken = getGetTokenClient().getCreateToken().accessToken
        Logger.logMessage("Create Token = $createToken")
        createDataClient.generateNewTestDataSet(createToken)
        Logger.logMessage(createDataClient.currentRequest.toString())
    }

    def "Our first Try" () {
        given: "Get Token Client is instantiated"
        when: "Get Token"
        AuthResponse response = getTokenClient.getAccessToken()
        and: "Access token is retrieved"
        String accessToken = response.accessToken
        then: "Success code is returned"
        BaseValidator.validateNumericValues(response.getStatus(), HttpStatus.SC_OK, "Response Status")
        and: "Response contains token"
        accessToken.length() > 0
    }

    def "Request (Get) Customer info using valid token should be successful" () {
        given: "Access token was retrieved for get collector info session"
        when: "Valid Get Info Request is sent to Auth0 Proxy using this token"
        then: "Response contains success status code (Http_OK 200)"
        and: ""
    }

}
