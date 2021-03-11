package com.loyalty.auth0.proxy.specification

import com.loyalty.auth0.proxy.TestApplication
import com.loyalty.auth0.proxy.components.restclient.createdata.CreateDataClient
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoClient
import com.loyalty.auth0.proxy.components.restclient.gettoken.GetTokenClient
import com.loyalty.auth0.proxy.components.util.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

@ActiveProfiles("int")
@SpringBootTest(classes = TestApplication)
class BaseSpecification extends Specification {

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
        if (startFlag == true) {
            createNewTestDataSet()
            accessToken = getTokenClient.getAccessToken().accessToken
            startFlag = false
        }
        Logger.logMessage("Auth0 Access Token: $accessToken")
    }

    def createNewTestDataSet() {
        String createToken = getGetTokenClient().getCreateToken().accessToken
        Logger.logMessage("Auth0 Create Token: $createToken")
        createDataClient.generateNewTestDataSet(createToken)
        Logger.logMessage(createDataClient.currentRequest.toString())
    }

}
