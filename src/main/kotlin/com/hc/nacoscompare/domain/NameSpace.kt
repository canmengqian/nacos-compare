package com.hc.nacoscompare.domain

class NameSpace {
    var namespace: String? = ""
    var namespaceDesc: String? = ""
    var configCount: Int? = 0
    var type: Int? = 0
    var namespaceShowName: String? = ""
    var quota: Int? = 200

    var configs: List<Config>? = emptyList()
}