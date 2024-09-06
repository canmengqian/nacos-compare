package com.hc.nacoscompare.service.merge.impl

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.domain.DataIdType

data class MergeData (
    val dataIdType: DataIdType,
    val dataId:String,
    val group:String,
    val namespace : String,
    val data: Dict
)