package com.hc.nacoscompare.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

class ConfigResponse {
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var pageItems: List<Config>? = emptyList()

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var totalCount: Int? = 0

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var pageNumber: Int? = 0
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    var pagesAvailable:  Int? = 0
}