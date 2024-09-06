package com.hc.nacoscompare.domain

enum class DataIdType(val type: String) {
    NONE(""),
    PROPERTIES("properties"),
    YAML("yaml"),
    YML("yml"),
    JSON("json"),
    XML("xml"),
    TXT("txt")
}