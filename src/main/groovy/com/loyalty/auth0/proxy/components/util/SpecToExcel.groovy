package com.loyalty.auth0.proxy.components.util

import groovy.util.logging.Slf4j
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook

import java.nio.file.Paths

@Slf4j
class SpecToExcel {

    static def update(String inputFolder, String outputFolder) throws Exception {
        Logger.logMessage(inputFolder)
        Logger.logMessage(outputFolder)

        def listOfFiles = []
        def dir = new File(inputFolder)
        dir.eachFile {file -> listOfFiles << file}

        for (int i = 0; i < listOfFiles.size(); i++) {
            scriptToTestCase( listOfFiles[i], outputFolder )
        }
    }

    static void scriptToTestCase(File file, String outputPath) {
        Logger.logMessage(file.path.toString())
        if(file.isFile()) {
            File inputFile = new File(file.path)
            String [] lines = inputFile.readLines()
            String tmpLine
            Workbook wb = new HSSFWorkbook()
            Sheet sheet = wb.createSheet()
            String outputFileName = Paths.get(outputPath, file.name.split("\\.")[0] + "_TestCases.xls")

            Logger.logMessage(outputFileName)

            FileOutputStream fileOut = new FileOutputStream(outputFileName);
            int rowCounter = 0
            lines.each {
                if (lineContainsKeyWord(it)) {
                    Row row
                    if ( it.contains("def \"") || it.contains("def '")) {
                        sheet.createRow(rowCounter++)   // Emty Row between Test Cases
                        tmpLine = it.replace("()", "")
                        tmpLine = tmpLine.replace("{", "")
                        tmpLine = tmpLine.replace("def", "\nDescription:")
                    }
                    else {
                        tmpLine = it
                    }
                    row = sheet.createRow(rowCounter++)
                    addLineToTheWorkBook(row, tmpLine)
                }
            }
            wb.write(fileOut)
            fileOut.close()
        }
    }

    static def addLineToTheWorkBook(Row row, String tmpLine) {
        Cell cell = row.createCell(0)
        cell.setCellValue(tmpLine.split(":")[0] + ":")
        cell = row.createCell(1)
        cell.setCellValue(tmpLine.split(":")[1])

    }

    static boolean lineContainsKeyWord(String line) {
        boolean result = false
        ArrayList<String> keyWords = new ArrayList<String>()

        /** Setting Up Array of the keyWords  **/
        keyWords.add("def \"")
        keyWords.add("def '")
        keyWords.add("given:")
        keyWords.add("when:")
        keyWords.add("then:")
        keyWords.add("and:")
        keyWords.add("setup:")
        keyWords.add("expect:")
        keyWords.add("where:")
        keyWords.each {
            if (line.contains(it)) {
                result = true
            }
        }
        result
    }
}
