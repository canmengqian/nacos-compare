package com.hc.nacoscompare.service.merge.impl

import cn.hutool.core.collection.CollUtil
import com.hc.nacoscompare.service.merge.KeyDeletor
import com.hc.nacoscompare.service.merge.Replacer

class MergeContext {
    var left: MergeParam? = null
    var right: MergeParam? = null
    var mergeStrategy: MergeStrategy = MergeStrategy.MERGE_RIGHT
    var leftReplace: Replacer? = null
    var rightReplace: Replacer? = null
    var leftDeletor: KeyDeletor? = null
    var rightDeletor: KeyDeletor? = null
    var ignoreKeys: List<String> = listOf()

    fun ignoreKey(key: String): Boolean {
        if (CollUtil.isEmpty(ignoreKeys)) {
            return false
        }
        val rs = ignoreKeys.map {
            return if (key.contains(it)) true else false
        }.distinct()
        if (rs.size > 1) {
            return false
        }
        return rs[0]
    }

}