package com.loyalty.auth0.proxy.specification

import com.loyalty.auth0.proxy.TestApplication
import com.loyalty.auth0.proxy.components.restclient.gettoken.AuthResponse
import com.loyalty.auth0.proxy.components.restclient.gettoken.GetTokenClient
import com.loyalty.auth0.proxy.components.util.Logger
import com.loyalty.auth0.proxy.components.validator.BaseValidator
import org.apache.http.HttpStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = TestApplication)
class BaseSpec extends Specification {

    @Autowired
    GetTokenClient getTokenClient

    def "Our first Try" () {
        given: "Get Token Client is instantiated"
        when: "Get Token"
        AuthResponse response = getTokenClient.getToken()
        then: "Success code is returned"
        BaseValidator.validateNumericValues(response.getStatus(), HttpStatus.SC_OK, "Response Status")
        and: "Response contains token"
        response.getAccessToken().length() > 0
    }
}
