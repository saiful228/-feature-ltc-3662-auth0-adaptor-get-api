package com.loyalty.auth0.proxy.specification.getinfo

import com.loyalty.auth0.proxy.components.constant.ErrorMessage
import com.loyalty.auth0.proxy.components.restclient.createdata.CreateDataClient
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoClient
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoResponse
import com.loyalty.auth0.proxy.components.restclient.gettoken.GetTokenClient
import com.loyalty.auth0.proxy.components.util.ErrorMessageUtil
import com.loyalty.auth0.proxy.components.util.Logger
import com.loyalty.auth0.proxy.components.validator.GetInfoValidator
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

    @Shared
    String accessToken

    @Shared
    Boolean startFlag = true

    def setup() {
        String createToken = getGetTokenClient().getCreateToken().accessToken
        Logger.logMessage("Create Token = $createToken")
        createDataClient.generateNewTestDataSet(createToken)
        Logger.logMessage(createDataClient.currentRequest.toString())

        if (startFlag == true) {
            accessToken = getTokenClient.getAccessToken().accessToken
            startFlag = false
        }
        Logger.logMessage("Get Info Access Token = $accessToken")
    }

    def "When valid request with valid access token sent to Get Info end point API returns expected response in JSON format" () {
        given: "Test data for collector exists in DB"
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Response contains success status (200 OK)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_OK, "Get Info Response status")
        and: "Response body matches our expected test data values"
        GetInfoValidator.validateCollectorInfo(getInfoResponse, createDataClient.currentRequest)
    }

    def "When valid request with invalid access token sent to Get Info end point API returns expected response in JSON format" () {
        given: "Access token is invalid"
        accessToken = "BlahBlahBlah"
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(message: ErrorMessage.UNATHORIZED.getValue()).getGetInfoError()
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Response contains success status (401 Unauthorized)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_UNAUTHORIZED, "Get Info Response status")
        and: "Actual error message matches expected"
        GetInfoValidator.validateStringValues(getInfoResponse.body, expectedErrorMessage, "Error message")
    }

}
