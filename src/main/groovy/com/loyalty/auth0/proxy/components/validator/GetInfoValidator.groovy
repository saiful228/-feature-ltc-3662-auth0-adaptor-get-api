package com.loyalty.auth0.proxy.components.validator

import com.loyalty.auth0.proxy.components.constant.Constants
import com.loyalty.auth0.proxy.components.restclient.createdata.CreateDataRequest
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoResponse
import com.loyalty.auth0.proxy.components.util.TestDataUtils

import java.time.LocalDate

class GetInfoValidator extends BaseValidator{

    static Boolean validateCollectorInfo(GetInfoResponse response, CreateDataRequest currentRequest ) {
        Boolean result = true

        LocalDate expectedCreatedAt = LocalDate.now()
        LocalDate expectedUpdatedAt = LocalDate.now()

        LocalDate actualCreatedAt = TestDataUtils.timeStampToLocalDateTime(response.getCreatedAt()).toLocalDate()
        LocalDate actualUpdatedAt = TestDataUtils.timeStampToLocalDateTime(response.getUpdatedAt()).toLocalDate()

        if(!validateStringValues(response.type, Constants.TYPE_EMAIL, "Type")) {result = false}
        if(!validateStringValues(response.userId, Constants.USER_ID_PREFIX_GET_INFO + currentRequest.userId, "User Id")) {result = false}
        if(!validateStringValues(response.email, currentRequest.email, "Email")) {result = false}
        if(!validateNumericValues(response.emailVerified, currentRequest.emailVerified, "Email verified")) {result = false}
        if(!validateDateValues(actualCreatedAt, expectedCreatedAt, "Created At")) {result = false}
        if(!validateDateValues(actualUpdatedAt, expectedUpdatedAt, "Updated At")) {result = false}
        result
    }

}
