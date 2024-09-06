package com.hc.nacoscompare.service.compare

import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.domain.NacosInstance

class NamespaceKeyCompare : NacosCompare<NacosInstance, NacosInstance> {
    override fun compare(left: NacosInstance, right: NacosInstance) {
        // 比较namaspace
        val leftNameSpace = left.namespaces2Map()
        val rightNameSpace = right.namespaces2Map()
        // 比较key
        val leftNameSpaceKeys = leftNameSpace.keys
        val rightNameSpaceKeys = rightNameSpace.keys
        val remainKeys = leftNameSpaceKeys.minus(rightNameSpaceKeys)
        logger().info("left 比 right 多的key有：${remainKeys} ")
        val remainKeys2 = rightNameSpaceKeys.minus(leftNameSpaceKeys)
        logger().info("left 比 right 少的key有：${remainKeys2} ")
    }
}