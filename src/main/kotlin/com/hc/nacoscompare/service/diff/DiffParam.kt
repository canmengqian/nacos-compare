package com.hc.nacoscompare.service.diff

import com.hc.nacoscompare.domain.DataIdType

data class DiffParam(val dataIdType: DataIdType,val left:Any,val right:Any)
