package com.loyalty.auth0.proxy.components.restclient.gettoken

import com.loyalty.auth0.proxy.components.constant.Audience
import com.loyalty.auth0.proxy.components.constant.GrantType
import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.util.Encryptor
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GetTokenClient implements BaseHttpClient {

    @Value ('${loyalty.auth-api-get-token.uri}')
    String uriConfig

    @Value ('${loyalty.auth-api-get-token.client_id}')
    String clientIdConfig

    @Value ('${loyalty.auth-api-get-token.client_secret}')
    String clientSecretConfig

    AuthResponse sendRequest(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse()
        authResponse.setHttpResponse(send(request))
        authResponse
    }

    AuthResponse getToken() {
        AuthRequest request = new AuthRequest()
        request.clientId = Encryptor.decrypt(clientIdConfig)
        request.clientSecret = Encryptor.decrypt(clientSecretConfig)
        request.audience = Audience.WEB_COLLECTOR.getValue()
        request.grantType = GrantType.CLIENT_CREDENTIALS.getValue()
        sendRequest(request)
    }

    HttpResponse send(AuthRequest request) {
        HttpPost postRequest = request.getPostRequest(uriConfig)
        Logger.logHttpRequest(postRequest)
        sendBasicPostRequest(postRequest)
    }
        //org.apache.http.util.EntityUtils.toString(sendBasicPostRequest(postRequest).getEntity())

}
