package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.constant.Audience
import com.loyalty.auth0.proxy.components.constant.GrantType
import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.restclient.gettoken.AuthRequest
import com.loyalty.auth0.proxy.components.restclient.gettoken.AuthResponse
import com.loyalty.auth0.proxy.components.util.Encryptor
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GetInfoClient implements BaseHttpClient {

    @Value ('${loyalty.auth-proxy-api.uri}')
    String uriConfig

    @Value ('${loyalty.auth-proxy-api.get-info-path}')
    String getInfoEndPoint

    GetInfoResponse getInfoForCollector(String cardNumber, String token) {
        GetInfoRequest request = new GetInfoRequest()
        request.cardNumber = cardNumber
        request.token = token
        sendRequest(request)
    }


    GetInfoResponse sendRequest(GetInfoRequest request) {
        GetInfoResponse response = new GetInfoResponse()
        response.setHttpResponse(send(request))
        response
    }

    HttpResponse send(GetInfoRequest request) {
        HttpGet httpRequest = request.getRequest(uriConfig)
        Logger.logHttpRequest(httpRequest)
        sendBasicGetRequest(httpRequest)
    }

}
