package com.loyalty.auth0.proxy.components.restclient.getinfo

import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.springframework.stereotype.Component

@Component
class GetInfoClient implements BaseHttpClient {

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
        String fullUri = uriConfig + pathConfig
        HttpGet httpRequest = request.getRequest(fullUri)
        Logger.logHttpRequest(httpRequest)
        sendBasicGetRequest(httpRequest)
    }

}
