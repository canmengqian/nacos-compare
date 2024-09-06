package com.hc.nacoscompare.service.merge

import com.hc.nacoscompare.service.merge.impl.MergeResult

/**
 * 写出器,用于写出合并好的结果
 */
interface Out {
    fun write(result: MergeResult)
}