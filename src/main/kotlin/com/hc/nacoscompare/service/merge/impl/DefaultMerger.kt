package com.hc.nacoscompare.service.merge.impl

import com.hc.nacoscompare.service.merge.Merge

class DefaultMerger : Merge {
    override fun merge(context: MergeContext): MergeResult {
        // 删除key
        context.leftDeletor?.delete(context.left?.value!!)
        context.rightDeletor?.delete(context.right?.value!!)
        when (context.mergeStrategy) {
            MergeStrategy.MERGE_LEFT -> {
                return LeftMerger().merge(context)
            }

            MergeStrategy.MERGE_RIGHT -> {
                return RightMerger().merge(context)
            }

            MergeStrategy.MERGE_MUTUAL -> {
                return MutualMerger().merge(context)
            }
        }
    }


}