package com.loyalty.auth0.proxy.components.config

import org.springframework.context.annotation.Configuration

import javax.annotation.PostConstruct

@Configuration
class SSLConfig {
    @PostConstruct
    void trustAllSSL() {
        def trustAllTrustManager = [
                checkClientTrusted: { chain, authType -> },
                checkServerTrusted: { chain, authType -> },
                getAcceptedIssuers: { null }
        ]
        def trustAllHostnameVerifier = [
                verify: { hostname, session -> true }
        ]

        def sc = javax.net.ssl.SSLContext.getInstance("SSL")
        sc.init(null, [trustAllTrustManager as javax.net.ssl.X509TrustManager] as javax.net.ssl.TrustManager[], null)
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                trustAllHostnameVerifier as javax.net.ssl.HostnameVerifier)
    }
}
