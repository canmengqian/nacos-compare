package com.hc.nacoscompare.service.merge

import cn.hutool.core.lang.Dict
import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.domain.DataIdType
import com.hc.nacoscompare.service.merge.impl.DefaultMerger
import com.hc.nacoscompare.service.merge.impl.MergeContext
import com.hc.nacoscompare.service.merge.impl.MergeParam
import com.hc.nacoscompare.service.merge.impl.MergeStrategy
import com.hc.nacoscompare.service.merge.out.PrintWriter
import org.junit.jupiter.api.Test

class DefaultMergerTest {

    @Test
    fun merge() {
        var context = MergeContext()
        val leftDict = Dict.create()
        val sub = Dict.create()
        sub.put("b", 1)
        leftDict.put("a", sub)
        val rightDict = Dict.create()
        rightDict.put("c",2)
        val leftParam = MergeParam(leftDict, null, false, DataIdType.PROPERTIES, "a.properties","test","test")
        val rightParam = MergeParam(rightDict, null, false, DataIdType.PROPERTIES, "a.properties","test","test")

        context.left = leftParam
        context.right = rightParam
        context.mergeStrategy = MergeStrategy.MERGE_LEFT
        val merge = DefaultMerger()
        val result = merge.merge(context)
        logger().info(result.toString())
        val out = PrintWriter()
        out.write(result)
    }
}