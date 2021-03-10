package com.loyalty.auth0.proxy.etc.spec2excel

import com.loyalty.auth0.proxy.components.util.SpecToExcel
import spock.lang.Specification

class ExtractTestCases extends Specification {

    def setup() {
        String rootFolder = "src\\test\\groovy\\com\\loyalty\\auth0\\proxy\\specification"
        String destinationRootFolder = "src\\test\\groovy\\com\\loyalty\\auth0\\proxy\\etc\\spec2excel\\exported"
        String sourceFolderSIT

        sourceFolderSIT = "$rootFolder\\getinfo"
        SpecToExcel.update(sourceFolderSIT,destinationRootFolder)

    }

    def "Done" () {
        expect: "Done"
            true
    }
}