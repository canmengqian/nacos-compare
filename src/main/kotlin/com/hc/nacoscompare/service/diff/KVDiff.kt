package com.hc.nacoscompare.service.diff

import cn.hutool.core.lang.Dict
import cn.hutool.core.lang.Pair
import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.domain.KV

class KVDiff : Diff<Dict, Dict> {
    override fun diff(left: Dict, right: Dict) {
        val minusKeys = right.keys.minus(left.keys)
        val addedKeys = left.keys.minus(right.keys)
        if (minusKeys.isNotEmpty()) {
            logger().error("left 比 right 少的key有：${minusKeys} ")
        }
        if (addedKeys.isNotEmpty()) {
            logger().error("left 比 right 多的key有：${addedKeys} ")
        }
        val diffKeys = mutableListOf<Pair<KV, KV>>()
        left.keys.forEach { key ->
            val leftValue = left[key]
            if (right.containsKey(key)) {
                val rightValue = right[key] ?: ""
                if (leftValue != rightValue) {
                    logger().error("left:${leftValue}，right:${rightValue}")
                    diffKeys.add(Pair(KV(key, leftValue), KV(key, rightValue)))
                }
            } else {
                diffKeys.add(Pair(KV(key, leftValue), KV(key, null)))
                logger().error("left:${leftValue}，right:${null}")
            }

        }
    }

}