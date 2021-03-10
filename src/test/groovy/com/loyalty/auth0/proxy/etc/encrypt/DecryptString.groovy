package com.loyalty.auth0.proxy.etc.encrypt

import com.loyalty.auth0.proxy.components.util.Encryptor
import com.loyalty.auth0.proxy.components.util.Logger
import spock.lang.Specification

class DecryptString extends Specification{

    def "Decrypt String" () {
        setup: "Decrypt String"

        String myString = "NSno96Hv1uJ0fwj/4/nNzsxLzhW4TmAY+PyoL7rO13RImeMIQtbW0wQsXVu0oV0DZD4DSMdfoRkhMFtmiFh6mVXDqWtobvaf6+9hB4KwAjpBFvbCoRquF/ztlwpa4xGE1YMGm0pw2LIQJAq4HUsAmTr+imK7Layuz6thY4e3Ilo0SWiwn3qJzwkihOdZhNLnsn1tfVqLBsCwEIPlS80inVxky7wbTV5WzsXOYj9D4lEbxUwrikFMzL0oNYi7skaWdv3oRmbTu+R4PzNEdtLeJNkCOX2NfVvp8dwAvb+MGdr+48wCiDTLDZnhefZeiNi8SjseaB5BQlHrtY7aZmlzbA=="

        Logger.logMessage("Decrypted String: " + Encryptor.decrypt(myString))
        expect: "Done"
        true
    }
}
