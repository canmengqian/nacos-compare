package com.hc.nacoscompare.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude

class Config {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var dataId: String = ""

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var id: String = ""

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var content: String = ""

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var tenant: String = ""

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var namespace: String = tenant

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var group: String? = ""

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    val md5: String? = ""

    @JsonIgnore
    var encryptedDataKey: String? = ""
    @JsonIgnore
    var appName: String? = ""
    @JsonIgnore
    var type: String? = ""
}