package com.loyalty.auth0.proxy.etc.encrypt

import com.loyalty.auth0.proxy.components.util.Encryptor
import com.loyalty.auth0.proxy.components.util.Logger
import spock.lang.Specification

class EncryptString extends Specification{

    def "Encrypt String" () {
        setup: "Encrypt String"

        String myString = "Airmiles80!"
        Logger.logMessage("Encrypted String: " + Encryptor.encrypt(myString))
        expect: "Done"
        true
    }
}
