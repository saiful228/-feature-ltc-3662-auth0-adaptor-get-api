package com.loyalty.auth0.proxy.components.util


import groovy.json.JsonOutput
import org.apache.commons.lang3.RandomStringUtils
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.util.EntityUtils

import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.regex.Matcher
import java.util.regex.Pattern

class TestDataUtils {

    final static String CARD_NUMBER_PREFIX = "800000"
    final static int CARD_NUMBER_LENGTH = 11
    final static long RATIO_RAND_MAX = 100
    final static int MAX_MILES_ISSUED = 50
    final static int SECONDS_PER_MINUTE = 60
    final static int MINUTES_PER_HOUR = 60
    final static int HOURS_PER_DAY = 24
    final static int DAYS_PER_COMMON_YEAR = 365
    final long MAX_MILES = 100

    static String DEFAULT_DESCRIPTION = "Description for "
    static String DEFAULT_ISSUER_NAME = "Issuer Name "
    static final String DEFAULT_SPONSOR_NAME = "Sponsor Name"
    static final String ROBOT_NAME = "Robot Karambulya"
    static final String EMAIL_VERIFY_TOKEN_FIND_REGEX = ".*token= *(.*) *target.*"

    static UUID getRandomUUID() {
        UUID.randomUUID()
    }

    static getRandomRecordFromList(ArrayList listOfRecords) {
        if (listOfRecords?.size() > 0) {
            int randomIndex = getRandomNumberBetween(0, listOfRecords.size() - 1)
            listOfRecords.get(randomIndex)
        }
        else {
            null
        }
    }

    static String getRandomCardNumber() {
        String cardNumber = CARD_NUMBER_PREFIX + getRandomStringNumber(CARD_NUMBER_LENGTH - CARD_NUMBER_PREFIX.length())
        cardNumber
    }

    static def getRandomEnumValue(def enumerator) {
        enumerator.values().getAt(getRandomNumberBetween(0, (enumerator.values().size() - 1)).toInteger()).getValue()
    }

    static Long getRandomNumberBetween(long min, long max) {
        return (long) ((new SecureRandom().nextDouble() * (max - min + 1)) + min)
    }

    static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.getActualMaximum(Calendar.DAY_OF_YEAR) > TestDataUtils.DAYS_PER_COMMON_YEAR
    }

    static getRandomString(int stringLength) {
        return RandomStringUtils.random(stringLength, true, false)
    }

    static String getRandomStringNumber(int numberLength) {
        String stringNumber = ""
        for (int i = 0; i < numberLength; i++)
            stringNumber += getRandomNumberBetween(0, 9).toString()
        stringNumber
    }

    /***************************************************************************************************************************************
     *  Method:     smartPad
     *
     *  Fills the front or end (depends on atStart) of provided segment (segmentValue) of the record with provided filler (fillerCharcter)
     *  up to the provided segmentSize
     *--------------------------------------------------------------------------------------------------------------------------------------
     * @param atStart            <------------ if value is true it will fill segment from the start, if false , from the end
     * @param segmentValue       <------------ Value of the segment that should be filled
     * @param fillerCharacter     <------------ Character to fill with
     * @param segmentSize        <------------ Required size of the segment
     * @return result            <------------ result of the filling
     ***************************************************************************************************************************************/
    static String smartPad(boolean atStart, String segmentValue, String fillerCharacter, int segmentSize) {
        String filler = ""
        if (!segmentValue) {
            segmentValue = ""
        }
        String result
        if (segmentValue.size() < segmentSize) {
            1.upto(segmentSize - segmentValue.size()) {
                filler = filler + fillerCharacter
            }
            if (atStart) {
                result = filler + segmentValue
            } else {
                result = segmentValue + filler
            }
        }
        else {
            result = segmentValue
        }
        result
    }

    static String getDefaultDescription(String descriptionSubject) {
        DEFAULT_DESCRIPTION + descriptionSubject
    }

    static getRandomValueFromList(List listOfValues) {
        if (listOfValues?.size() > 0) {
            int randomIndex = getRandomNumberBetween(0, listOfValues.size() - 1)
            listOfValues.get(randomIndex)
        }
        else {
            null
        }
    }

    static String getYearMonth (def date) {
        date.getYear().toString() + smartPad(true, date.getMonthValue().toString(), "0", 2)
    }

    static String getResponseBody( HttpResponse response) {
        HttpEntity entity = response.getEntity()
        EntityUtils.toString(entity)
    }

    static def writeStringListToFile(List<String> contentList, String filename) {
        File file = new File(filename)
        file.write("")
        contentList.each {
            file.append(it + "\n")
        }
    }


    static LocalDateTime timeStampToLocalDateTime(String timeStamp) {
        LocalDateTime.parse(timeStamp.replace("Z", ""))
    }

    static String getRandomValidEmail() {
        List <String> domainList = new ArrayList<String>()
        domainList.add("@loyalty.com")
        domainList.add("@loyalty.ca")
        domainList.add("@robots.net")

        Integer emailLength = TestDataUtils.getRandomNumberBetween(4, 11)
        String email = TestDataUtils.getRandomString(emailLength).toLowerCase()
        email <<= TestDataUtils.getRandomValueFromList(domainList)
        email
    }

    static String mapToJson(def map) {
        JsonOutput.toJson(map).replace( "\\", "")
    }


    static String formatName(String name) {
        String formatName = name.substring(0,1).toUpperCase()
        formatName <<= name.substring(1).toLowerCase()
        formatName
    }

    static String getRandomPassword(Integer length=11) {
        String password = ""
        password <<= getRandomString(2).toUpperCase()
        password <<= getRandomStringNumber(2)
        password <<= getRandomString(length - 4)
        password
    }

}
