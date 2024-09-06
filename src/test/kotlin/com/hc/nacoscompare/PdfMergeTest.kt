package com.hc.nacoscompare

import org.apache.pdfbox.io.MemoryUsageSetting
import org.apache.pdfbox.multipdf.PDFMergerUtility
import org.junit.jupiter.api.Test
import java.io.File

class PdfMergeTest {
    @Test
    fun test_merge_100() {
        var pdfMergerUtility = PDFMergerUtility()
        for (i in 1..1000) {
            pdfMergerUtility.addSource(File("D:\\test\\pdf\\1.pdf"))
        }
        pdfMergerUtility.destinationFileName = "D:\\test\\pdf\\merge\\merged.pdf"
        pdfMergerUtility.isIgnoreAcroFormErrors = true
        pdfMergerUtility.documentMergeMode= PDFMergerUtility.DocumentMergeMode.OPTIMIZE_RESOURCES_MODE
        pdfMergerUtility.mergeDocuments(null)

    }
}