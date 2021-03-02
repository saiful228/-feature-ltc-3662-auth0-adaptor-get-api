package com.loyalty.auth0.proxy.specification

import com.loyalty.auth0.proxy.TestApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = TestApplication)
class BaseSpecification extends Specification {


}
