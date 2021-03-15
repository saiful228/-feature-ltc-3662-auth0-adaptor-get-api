package com.loyalty.auth0.proxy.specification.getinfo

import com.loyalty.auth0.proxy.components.constant.Constants
import com.loyalty.auth0.proxy.components.constant.ErrorCode
import com.loyalty.auth0.proxy.components.constant.ErrorMessage
import com.loyalty.auth0.proxy.components.constant.ErrorParameter
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoResponse
import com.loyalty.auth0.proxy.components.util.ErrorMessageUtil
import com.loyalty.auth0.proxy.components.util.TestDataUtils
import com.loyalty.auth0.proxy.components.validator.GetInfoValidator
import com.loyalty.auth0.proxy.specification.BaseSpecification
import org.apache.http.HttpStatus

class GetInfoSpec extends BaseSpecification {

    def "TC-1. When valid request with valid access token sent to Get Info end point API returns expected response in JSON format" () {
        given: "Test data for collector exists in DB"
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Response contains success status (200 OK)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_OK, "Get Info Response status")
        and: "Response body matches our expected test data values"
        GetInfoValidator.validateCollectorInfo(getInfoResponse, createDataClient.currentRequest)
    }

    def "TC-2 N. When valid request with invalid Card number format (> 11 characters length) sent to Get Info end point API returns expected error response in JSON format" () {
        given: "invalid Card Number format"
        String cardNumber = Constants.INVALID_CARD_NO_FORMAT
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(
                message: ErrorMessage.CARD_NUMBER_TOO_LONG.getValue(),
                parameter: ErrorParameter.ID.getValue(),
                errorCode: ErrorCode.REQUEST_OBJECT.getValue()
        ).getBadRequestError()
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(cardNumber, accessToken)
        then: "Response contains success status (400 Bad request)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_BAD_REQUEST, "Get Info Response status")
        and: "Actual error message matches expected"
        GetInfoValidator.validateStringValues(getInfoResponse.body, expectedErrorMessage, "Error message")
    }

    def "TC-3 N. When valid request with valid Card number that does not exist in db sent to Get Info end point API returns expected error response in JSON format" () {
        given: "invalid Card Number format"
        String cardNumber = TestDataUtils.getRandomCardNumber()
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(message: ErrorMessage.PROFILE_NOT_FOUND.getValue()).getProfileNotFoundError()
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(cardNumber, accessToken)
        then: "Response contains success status (404 Not Found)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_NOT_FOUND, "Get Info Response status")
        and: "Actual error message matches expected"
        GetInfoValidator.validateStringValues(getInfoResponse.body, expectedErrorMessage, "Error message")
    }

    def "TC-4 N. When valid request with invalid access token sent to Get Info end point API returns expected error response in JSON format" () {
        given: "Access token is invalid"
        accessToken = "InvalidAccessToken"
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(message: ErrorMessage.UNATHORIZED.getValue()).getUnathorizedError()
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Response contains success status (401 Unauthorized)"
        GetInfoValidator.validateNumericValues(getInfoResponse.getStatus(), HttpStatus.SC_UNAUTHORIZED, "Get Info Response status")
        and: "Actual error message matches expected"
        GetInfoValidator.validateStringValues(getInfoResponse.body, expectedErrorMessage, "Error message")
    }
    
}
