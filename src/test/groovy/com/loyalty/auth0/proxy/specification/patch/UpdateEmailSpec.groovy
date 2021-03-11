package com.loyalty.auth0.proxy.specification.patch

import com.loyalty.auth0.proxy.components.constant.Constants
import com.loyalty.auth0.proxy.components.constant.ErrorCode
import com.loyalty.auth0.proxy.components.constant.ErrorMessage
import com.loyalty.auth0.proxy.components.constant.ErrorParameter
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoResponse
import com.loyalty.auth0.proxy.components.restclient.patch.PatchClient
import com.loyalty.auth0.proxy.components.util.ErrorMessageUtil
import com.loyalty.auth0.proxy.components.util.Logger
import com.loyalty.auth0.proxy.components.util.TestDataUtils
import com.loyalty.auth0.proxy.components.validator.BaseValidator
import com.loyalty.auth0.proxy.components.validator.GetInfoValidator
import com.loyalty.auth0.proxy.specification.BaseSpecification
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired

class UpdateEmailSpec extends BaseSpecification {

    @Autowired
    PatchClient updateClient

    def "TC-1. When valid request with valid access token sent, end point API returns expected status code." () {
        given: "Test data for collector exists in DB"
        and: "There is a new email for the collector."
        String newEmail = TestDataUtils.getRandomValidEmail()
        when: "Get Info request sent to get info end point"
        GetInfoResponse getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Existing collector's email is not the same as new email"
        !GetInfoValidator.validateStringValues(getInfoResponse.getEmail(), newEmail, "Existing and new Emails")
        and: "Response body matches our expected test data values"
        GetInfoValidator.validateCollectorInfo(getInfoResponse, createDataClient.currentRequest)
        when: "Update Profile with new email is sent to API"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response status matches expected status 204 Status"
        BaseValidator.validateNumericValues(response.getStatusLine().statusCode, HttpStatus.SC_NO_CONTENT, "Response Status")
        when: "Get Info request sent to get info end point again"
        getInfoResponse = getInfoClient.getInfoForCollector(createDataClient.currentRequest.cardNumber, accessToken)
        then: "Email in this response now matches new Email"
        GetInfoValidator.validateStringValues(getInfoResponse.getEmail(), newEmail, "Existing and new Emails")
    }


    def "TC-2N. When valid request contains invalid email format (email does not contain prefix - @abc.com) sent, API returns expected error response in JSON format" () {
        given: "New email collector has wrong formatting and does not contain prefix."
        String newEmail = "@abc.com"
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(
                message: ErrorMessage.EMAIL_INVALID.getValue(),
                errorCode: ErrorCode.REQUEST_OBJECT.getValue(),
                parameter: ErrorParameter.EMAIL.getValue())
                .getBadRequestError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response contains success status (400 Bad Request)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

    def "TC-3N. When valid request contains invalid email format (email does not contain @ - abc-abc.com) sent, API returns expected error response in JSON format" () {
        given: "New email collector has wrong formatting and does not contain '@'."
        String newEmail = "abc-abc.com"
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(
                message: ErrorMessage.EMAIL_INVALID.getValue(),
                errorCode: ErrorCode.REQUEST_OBJECT.getValue(),
                parameter: ErrorParameter.EMAIL.getValue())
                .getBadRequestError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response contains success status (400 Bad Request)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

    def "TC-4N. When valid request contains invalid email format (email does not contain domain - abc@abc) sent, API returns expected error response in JSON format" () {
        given: "New email collector has wrong formatting and does not contain domain."
        String newEmail = "abc@abc"
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(
                message: ErrorMessage.EMAIL_INVALID.getValue(),
                errorCode: ErrorCode.REQUEST_OBJECT.getValue(),
                parameter: ErrorParameter.EMAIL.getValue())
                .getBadRequestError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response contains success status (400 Bad Request)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_BAD_REQUEST, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

    def "TC-5N. When valid request contains card number that does not exist sent, API returns expected error response in JSON format" () {
        given: "There is a new valid email for the collector."
        String newEmail = TestDataUtils.getRandomValidEmail()
        and: "There is a collector number that does not exist in DB."
        String cardNumber = TestDataUtils.getRandomCardNumber()
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(
                message: ErrorMessage.PROFILE_NOT_FOUND.getValue())
                .getProfileNotFoundError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(cardNumber, newEmail, accessToken)
        then: "Response contains success status (404 Not Found)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

    def "TC-6N. When valid request with invalid access token sent, API returns expected error response in JSON format" () {
        given: "Access token is invalid"
        accessToken = "InvalidAccessToken"
        and: "There is a new email for the collector."
        String newEmail = TestDataUtils.getRandomValidEmail()
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(message: ErrorMessage.UNATHORIZED.getValue()).getUnathorizedError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response contains success status (401 Unauthorized)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_UNAUTHORIZED, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

    def "TC-7N. When valid request with expired access token sent, API returns expected error response in JSON format" () {
        given: "Access token is expired"
        accessToken = Constants.EXPIRED_TOKEN
        Logger.logMessage("Expired token = $accessToken")
        and: "There is a new email for the collector."
        String newEmail = TestDataUtils.getRandomValidEmail()
        and: "Expected error message is generated"
        String expectedErrorMessage = new ErrorMessageUtil(message: ErrorMessage.FORBIDDEN.getValue()).getUnathorizedError()
        when: "Update Info request sent to the end point"
        HttpResponse response = updateClient.sendUpdateInfoRequest(createDataClient.currentRequest.cardNumber, newEmail, accessToken)
        then: "Response contains success status (403 Forbidden)"
        BaseValidator.validateNumericValues(response.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN, "Update Info Response status")
        and: "Actual error message matches expected"
        BaseValidator.validateStringValues(TestDataUtils.getResponseBody(response), expectedErrorMessage, "Error message")
    }

}
