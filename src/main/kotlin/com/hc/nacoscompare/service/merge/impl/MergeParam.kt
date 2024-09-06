package com.hc.nacoscompare.service.merge.impl

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.domain.DataIdType
import com.hc.nacoscompare.service.merge.Replacer

data class MergeParam(val value:Dict,
                      val replace: Replacer?,
                      val ignoreMissKey: Boolean=false,
                      val dataIdType: DataIdType,
                      val dataId:String,
                      val group:String,
                      val namespace : String
                      )

