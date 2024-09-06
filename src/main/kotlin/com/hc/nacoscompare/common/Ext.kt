package com.hc.nacoscompare.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun Any.objectMapper(): ObjectMapper {
    return ObjectMapper().registerKotlinModule()
}

fun Any.logger():Logger{
   return LoggerFactory.getLogger(this.javaClass)
}