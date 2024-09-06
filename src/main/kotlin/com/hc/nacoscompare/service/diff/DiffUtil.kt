package com.hc.nacoscompare.service.diff

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.domain.DataIdType

class DiffUtil {
    companion object {
        private val diffs: MutableMap<DataIdType, Diff<*, *>> = mutableMapOf(
            DataIdType.YAML to YmlDiff(),
            DataIdType.YML to YmlDiff(),
            DataIdType.PROPERTIES to KVDiff()
        )

        fun diff(diffParam: DiffParam) {
            when (diffParam.dataIdType) {
                DataIdType.YAML, DataIdType.YML -> YmlDiff().diff(diffParam.left as Dict, diffParam.right as Dict)
                DataIdType.PROPERTIES -> KVDiff().diff(diffParam.left as Dict, diffParam.right as Dict)
                DataIdType.NONE -> TODO()
                DataIdType.JSON -> TODO()
                DataIdType.XML -> TODO()
                DataIdType.TXT -> TextDiff().diff(diffParam.left as Dict, diffParam.right as Dict)
            }
        }


    }
}

