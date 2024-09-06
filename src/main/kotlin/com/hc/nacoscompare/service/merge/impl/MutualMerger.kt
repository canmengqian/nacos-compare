package com.hc.nacoscompare.service.merge.impl

import com.hc.nacoscompare.service.merge.Merge

class MutualMerger : Merge {
    override fun merge(context: MergeContext): MergeResult {
        val leftResult = LeftMerger().merge(context)
        val rightResult = RightMerger().merge(context)
        return MergeResult(
            leftResult.left,
            rightResult.right
        )
    }
}