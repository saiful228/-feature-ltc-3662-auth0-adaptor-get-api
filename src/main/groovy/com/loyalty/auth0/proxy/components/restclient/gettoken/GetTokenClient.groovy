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

    @Value ('${loyalty.auth-api-get-token.audience}')
    String clientAudienceConfig

    @Value ('${loyalty.auth-api-get-token.client_id_create}')
    String clientIdCreate

    @Value ('${loyalty.auth-api-get-token.client_secret_create}')
    String clientSecretCreate

    @Value ('${loyalty.auth-api-get-token.audience_create}')
    String audienceCreate

    AuthResponse sendRequest(AuthRequest request) {
        AuthResponse authResponse = new AuthResponse()
        authResponse.setHttpResponse(send(request))
        authResponse
    }

    AuthResponse getAccessToken() {
        AuthRequest request = new AuthRequest()
        request.clientId = Encryptor.decrypt(clientIdConfig)
        request.clientSecret = Encryptor.decrypt(clientSecretConfig)
        request.audience = clientAudienceConfig
        request.grantType = GrantType.CLIENT_CREDENTIALS.getValue()
        sendRequest(request)
    }


    AuthResponse getCreateToken() {
        AuthRequest request = new AuthRequest()
        request.clientId = Encryptor.decrypt(clientIdCreate)
        request.clientSecret = Encryptor.decrypt(clientSecretCreate)
        request.audience = audienceCreate
        request.grantType = GrantType.CLIENT_CREDENTIALS.getValue()
        sendRequest(request)
    }

    HttpResponse send(AuthRequest request) {
        HttpPost postRequest = request.getPostRequest(uriConfig)
        Logger.logHttpRequest(postRequest)
        sendBasicPostRequest(postRequest)
    }
}
