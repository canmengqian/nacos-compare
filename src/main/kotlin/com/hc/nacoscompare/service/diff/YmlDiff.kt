package com.hc.nacoscompare.service.diff

import cn.hutool.core.lang.Dict
import cn.hutool.core.text.CharSequenceUtil

class YmlDiff : Diff<Dict, Dict> {
    companion object {
        private fun flatten(dict: Dict, parentKey: String): Dict {
            val flattenDict = Dict.create()
            dict.forEach { key, value ->
                var indexKey = key
                if (CharSequenceUtil.isNotBlank(parentKey)) {
                    indexKey = "$parentKey.$key"
                }

                if (value is Dict) {
                    flattenDict.putAll(flatten(value, indexKey))
                } else if (value is Map<*, *>) {
                    flattenDict.putAll(flatten(Dict.of(value), indexKey))
                } else {
                    flattenDict[indexKey] = value
                }
            }
            return flattenDict
        }
    }

    override fun diff(left: Dict, right: Dict) {
        val leftMap = flatten(left, "")
        val rightMap = flatten(right, "")
        KVDiff().diff(leftMap, rightMap)
    }

}

