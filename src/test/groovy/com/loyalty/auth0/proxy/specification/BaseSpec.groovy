package com.loyalty.auth0.proxy.specification

import com.loyalty.auth0.proxy.TestApplication
import com.loyalty.auth0.proxy.components.util.Logger
import com.loyalty.auth0.proxy.components.validator.BaseValidator
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = TestApplication)
class BaseSpec extends Specification {


    def "Our first Try" () {
        given: "A is 1"
        int a= 1
        Logger.logMessage("A = 1")
        and: "B is 1"
        int b = 1
        Logger.logMessage("B = 1")
        when: "we we add A and B"
        Logger.logMessage("C = ${a.toString()} + ${b.toString()}")
        int c= a + b
        then: "Results equals to 2"
        BaseValidator.validateNumericValues(c, 3, "Total of A + B")
    }
}
