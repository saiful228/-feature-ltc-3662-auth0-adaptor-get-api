package com.loyalty.auth0.proxy.etc.spec2excel

import com.loyalty.auth0.proxy.components.util.SpecToExcel
import spock.lang.Specification

import java.nio.file.Paths

class ExtractTestCases extends Specification {

    def setup() {
        String basePath = Paths.get("src", "test", "groovy", "com", "loyalty", "auth0", "proxy").toString()
        String rootFolder = Paths.get(basePath, "specification").toString()
        String destinationRootFolder = Paths.get(basePath, "etc", "spec2excel", "exported").toString()
        String sourceFolderSIT

        sourceFolderSIT = Paths.get(rootFolder,"getinfo" )
        SpecToExcel.update(sourceFolderSIT,destinationRootFolder)

        sourceFolderSIT = Paths.get(rootFolder,"patch" )
        SpecToExcel.update(sourceFolderSIT,destinationRootFolder)


    }

    def "Done" () {
        expect: "Done"
            true
    }
}