package com.hc.nacoscompare.service.merge.impl

import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.service.merge.Merge
import com.hc.nacoscompare.util.DictUtil

/**
 * 以右边为基础,向左边合并
 */
class LeftMerger : Merge {
    override fun merge(context: MergeContext): MergeResult {
        // 平铺左边的数据
        val leftDict = DictUtil.flatten(context.left?.value!!, "")
        val rightDict = DictUtil.flatten(context.right?.value!!, "")
        rightDict.forEach { key, value ->
            // 判断是否需要忽略比对
            if (context.ignoreKey(key)) {
                return@forEach
            }
            if (!leftDict.containsKey(key)) {
                if (context.left!!.ignoreMissKey) {
                    logger().warn("忽略key:${key}")
                    return@forEach
                }
                // 如果右边的key不存在于左边的dict中,则直接添加
                leftDict[key] = value
                return@forEach
            }

            //是否在待替换的key中
            if (context.leftReplace?.exstingKey(key) == true) {
                leftDict[key] = context.leftReplace?.replace(key, value)
                return@forEach
            }
            // 判断值是否一致,不一致仅提醒
            if (leftDict[key] != value) {
                // 提示不一致
                logger().error("左边替换策略,左右值不一致,比较key:${key},左边值:${leftDict[key]},右边值:${value}")
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
            context.left!!.group, context.left!!.namespace, data = leftFoldDict
        )
        return MergeResult(
            leftData,
            rightData
        )
    }
}