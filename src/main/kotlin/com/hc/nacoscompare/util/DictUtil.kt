package com.hc.nacoscompare.util

import cn.hutool.core.lang.Dict
import cn.hutool.core.text.CharSequenceUtil
import com.hc.nacoscompare.domain.DataIdType

class DictUtil {
    companion object {
        /**
         * 平铺字典
         */
        fun flatten(dict: Dict, parentKey: String): Dict {
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

        /**
         * 折叠字典
         */
        fun fold(dict: Dict): Dict {
            val foldDict = Dict.create()
            dict.forEach { key, value ->
                val keys = key.split(".")
                var currentDict = foldDict
                for (i in 0 until keys.size - 1) {
                    val currentKey = keys[i]
                    if (!currentDict.containsKey(currentKey)) {
                        currentDict[currentKey] = Dict.create()
                    }
                    currentDict = currentDict[currentKey] as Dict
                }
                currentDict[keys.last()] = value
            }
            return foldDict
        }
        /**
         * 折叠字典,只对yaml和yml格式进行折叠
         */
        fun fold(dict: Dict,dataIdType: DataIdType):Dict{
            return when(dataIdType){
                DataIdType.YAML,DataIdType.YML->fold(dict)
                else->dict
            }
        }

        fun isEmpty(dict: Dict?): Boolean {
            return dict.isNullOrEmpty()
        }
    }
}