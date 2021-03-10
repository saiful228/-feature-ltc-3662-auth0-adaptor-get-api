package com.loyalty.auth0.proxy.components.restclient.getinfo


import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class GetInfoClient implements BaseHttpClient {

    @Value ('${loyalty.auth-proxy-api.uri}')
    String uriConfig

    @Value ('${loyalty.auth-proxy-api.get-info-path}')
    String getInfoPath

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
        String fullUri = uriConfig + getInfoPath
        HttpGet httpRequest = request.getRequest(fullUri)
        Logger.logHttpRequest(httpRequest)
        sendBasicGetRequest(httpRequest)
    }

}
