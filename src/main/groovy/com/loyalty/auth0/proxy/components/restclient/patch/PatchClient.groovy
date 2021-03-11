package com.loyalty.auth0.proxy.components.restclient.patch


import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoRequest
import com.loyalty.auth0.proxy.components.restclient.getinfo.GetInfoResponse
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPatch
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class PatchClient implements BaseHttpClient {

    HttpResponse sendUpdateInfoRequest(String collectorNumber, String newEmail, String token) {
        UpdateInfoRequest updateInfoRequest = new UpdateInfoRequest()
        updateInfoRequest.cardNumber = collectorNumber
        updateInfoRequest.email = newEmail
        updateInfoRequest.token = token
        String fullPath = uriConfig + pathConfig
        Logger.logHttpRequest(updateInfoRequest.getRequest(fullPath))
        Logger.logMessage("Update Info Request values: ${updateInfoRequest.toString()}")
        sendBasicGetRequest(updateInfoRequest.getRequest(fullPath))
    }
}
