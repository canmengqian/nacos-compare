package com.hc.nacoscompare.service.merge.out

import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.domain.ConfigData
import com.hc.nacoscompare.service.ConfigParser
import com.hc.nacoscompare.service.merge.Out
import com.hc.nacoscompare.service.merge.impl.MergeResult
import java.io.StringWriter

class PrintWriter : Out {
    override fun write(result: MergeResult) {
        val configParser = ConfigParser()

        val (left, right) = result
        logger().info("正在写入左边配置:dataid:${left.dataId},dataType:${left.dataIdType}")
        if (left.data.isNullOrEmpty()) {
            logger().warn("左侧配置项为空,无法进行配置写入")
        } else {
            val leftConfigData = ConfigData(left.dataId, left.dataIdType, left.data)
            val leftText = configParser.format(leftConfigData)
            val stringWriter = StringWriter()
            stringWriter.write(leftText)
            println(leftText)
        }
        logger().info("正在写入右边配置:dataid:${right.dataId},dataType:${right.dataIdType}")
        if (right.data.isNullOrEmpty()) {
            logger().warn("右侧配置项为空,无法进行配置写入")
        } else {
            val rightConfigData = ConfigData(right.dataId, right.dataIdType, right.data)
            val rightText = configParser.format(rightConfigData)
            val stringWriter = StringWriter()
            stringWriter.write(rightText)
            println(rightText)
        }

    }

}