package com.hc.nacoscompare.domain

import cn.hutool.core.lang.Dict

data class ConfigData(val dataId:String, val dataIdType: DataIdType,val data:Dict?)
