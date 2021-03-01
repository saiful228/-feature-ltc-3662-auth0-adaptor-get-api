package com.loyalty.auth0.proxy.etc.encrypt

import com.loyalty.auth0.proxy.components.util.Encryptor
import com.loyalty.auth0.proxy.components.util.Logger
import spock.lang.Specification

class EncryptString extends Specification{

    def "Encrypt String" () {
        setup: "Encrypt String"

        String myString = "k2RScLOcxlKC9YMoy8yQoL4TtpIKHj-KSEpLE9pSFOM2tr1_0zrJ9mjaSBwqu5gY"

        Logger.logMessage("Encrypted String: " + Encryptor.encrypt(myString))
        expect: "Done"
        true
    }
}
