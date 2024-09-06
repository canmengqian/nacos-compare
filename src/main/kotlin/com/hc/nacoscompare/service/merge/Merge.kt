package com.hc.nacoscompare.service.merge

import com.hc.nacoscompare.service.merge.impl.MergeContext
import com.hc.nacoscompare.service.merge.impl.MergeResult

/**
 * 合并器,用于将左右两边不一致的数据进行合并
 */
interface Merge {
    fun merge(mergeConfig: MergeContext): MergeResult

}