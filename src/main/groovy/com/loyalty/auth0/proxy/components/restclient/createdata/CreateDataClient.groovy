package com.loyalty.auth0.proxy.components.restclient.createdata


import com.loyalty.auth0.proxy.components.restclient.BaseHttpClient
import com.loyalty.auth0.proxy.components.util.Logger
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.methods.HttpPost
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class CreateDataClient implements BaseHttpClient {

    @Value ('${loyalty.auth-create-client-api.uri}')
    String uriConfig

    @Value ('${loyalty.auth-create-client-api.path}')
    String pathConfig


    CreateDataRequest currentRequest


    def generateNewTestDataSet(String token){
        for(int i=0; i < 20; i++ ) {
            currentRequest = new CreateDataRequest()
            currentRequest.setDefaultValues(token)
            try{
                HttpResponse response = sendRequest(currentRequest)
                if ( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    break
                }
            }
            catch (Exception e) {
                Logger.logWarning("Try ${(i+1).toString()} was not processed.")
                currentRequest = null
            }
        }
    }

    def sendRequest(CreateDataRequest request) {
        send(request)
    }

    HttpResponse send(CreateDataRequest request) {
        String fullUri = uriConfig + pathConfig
        HttpPost httpRequest = request.getPostRequest(fullUri)
        Logger.logHttpRequest(httpRequest)
        sendBasicGetRequest(httpRequest)
    }

}
