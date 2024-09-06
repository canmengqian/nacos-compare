package com.hc.nacoscompare.service.merge.impl

import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.service.merge.Merge
import com.hc.nacoscompare.util.DictUtil

/**
 * 以左边为基础,向右边合并数据
 * 1. 右侧缺失的key，使用左边数据补齐
 */
class RightMerger : Merge {
    override fun merge(context: MergeContext): MergeResult {
        // 平铺左边的数据
        val leftDict = DictUtil.flatten(context.left?.value!!, "")
        val rightDict = DictUtil.flatten(context.right?.value!!, "")
        leftDict.forEach { key, value ->
            // 判断是否需要忽略比对
            if (context.ignoreKey(key)) {
                return@forEach
            }
            if (!rightDict.containsKey(key)) {
                // 如果选择忽略,不再补齐右边
                if (context.left!!.ignoreMissKey) {
                    logger().warn("忽略key:${key}")
                    return@forEach
                }
                // 如果右边的key不存在于左边的dict中,则直接添加
                rightDict[key] = value
                return@forEach
            }
            //是否在待替换的key中
            if (context.rightReplace?.exstingKey(key) == true) {
                rightDict[key] = context.rightReplace?.replace(key, value)
                return@forEach
            }
            // 判断值是否一致,不一致仅提醒
            if (rightDict[key] != value) {
                // 提示不一致
                logger().error("左边替换策略,左右值不一致,比较key:${key},左边值:${leftDict[key]},右边值:${value}")
                // 使用左侧数据替换右侧数据
                rightDict[key] = value
            }
        }
        // 根据dataIdType类型决定是否进行字典折叠
        val leftFoldDict = DictUtil.fold(leftDict, context.left!!.dataIdType)
        val rightFoldDict = DictUtil.fold(rightDict, context.right!!.dataIdType)
        val leftData = MergeData(
            context.left!!.dataIdType, context.left!!.dataId,
            context.left!!.group, context.left!!.namespace, data = leftFoldDict
        )
        val rightData = MergeData(
            context.right!!.dataIdType, context.left!!.dataId,
            context.left!!.group, context.left!!.namespace, data = rightFoldDict
        )
        return MergeResult(
            leftData, rightData
        )

    }
}