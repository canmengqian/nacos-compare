package com.hc.nacoscompare.service

import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration
@SpringBootTest()
class NacosNacosCompareServiceTest {

    @Autowired
    lateinit var nacosCompareService: NacosCompareService

    @Test
    fun compare() {
        nacosCompareService.loadClients()
        nacosCompareService.compare()

    }
}