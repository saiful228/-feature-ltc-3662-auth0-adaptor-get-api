package com.loyalty.auth0.proxy.components.util

import groovy.util.logging.Slf4j
import org.apache.http.util.EntityUtils

@Slf4j
class Logger {

    final static String LOG_TITLE = "\n\n[TEST AUTOMATION]"

    def static logValidationValues(String actualResult, String expectedResult, String message = "") {
        String messageOutput = "$LOG_TITLE\n"
        if (message.length() > 0) {
            messageOutput <<= "$message\n"
        }
        messageOutput <<= "Expected Result Data: $expectedResult\n"
        messageOutput <<= "Actual  Result  Data: $actualResult\n"
        log.info(messageOutput)
    }

    def static logError(String error) {
        log.info("$LOG_TITLE\n[ERROR]: $error\n")
    }

    def static logWarning(String msg) {
        log.info("$LOG_TITLE\n[WARNING]: $msg\n")
    }

    static def logEventValidationError(String validationField, def expectedResult, def actualResult) {
        String errorMessage = "\n\tExpected $validationField: ${expectedResult.toString()}"
        errorMessage <<= "\n\tActual   $validationField: ${actualResult.toString()}\n"
        Logger.logError(errorMessage)
    }

    static def logMessage(String message) {
        log.info("$LOG_TITLE\n$message\n")
    }

    static def logEvent(String event, String EventName) {
        log.info("\n\n$LOG_TITLE \n$EventName Event:\n $event\n")
    }

    static def logHttpRequest(def request) {
        String logMsg = "URI: ${request.getURI()}\n"
        logMsg <<= "Body Parameters: ${EntityUtils?.toString(request.getEntity())}"
        logMessage(logMsg)
    }

    static def logHttpURIRequest(def request) {
        String logMsg = "URI: ${request.getURI()}\n"
        logMessage(logMsg)
    }

    static def logString(String string) {
        log.info("$string")
    }
}
