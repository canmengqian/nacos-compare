package com.hc.nacoscompare.service.merge.out

import cn.hutool.core.io.FileUtil
import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.domain.ConfigData
import com.hc.nacoscompare.service.ConfigParser
import com.hc.nacoscompare.service.merge.Out
import com.hc.nacoscompare.service.merge.impl.MergeResult

class FileWriter(val targetPath: String) : Out {
    init {
        logger().info("创建目录:${targetPath}")
        try {
            val file = java.io.File(targetPath)
            file.mkdirs()
        } catch (e: Exception) {
            logger().error("创建目录失败:${e.message}")
        }
    }

    override fun write(result: MergeResult) {
        val (left, right) = result
        val leftFilePath = "${targetPath}/left/${left.namespace}/${left.group}"
        val rightFilePath = "${targetPath}/right/${right.namespace}/${right.group}"
        try {
            val configParser = ConfigParser()
            val leftPath = java.io.File(leftFilePath)
            leftPath.mkdirs()
            val rightPath = java.io.File(rightFilePath)
            rightPath.mkdirs()
            val leftFilePathDir = "${leftFilePath}/${left.dataId}"
            val rightFilePathDir = "${rightFilePath}/${right.dataId}"
            val leftConfigData = ConfigData(left.dataId, left.dataIdType, left.data)
            val leftText = configParser.format(leftConfigData)
            val leftFile=FileUtil.file(leftFilePathDir)
            leftFile.writeText(leftText)
            logger().info("正在写入左边文件:${leftFilePath}")
            val rightConfigData = ConfigData(right.dataId, right.dataIdType, right.data)
            val rightText = configParser.format(rightConfigData)
            val rightFile=FileUtil.file(rightFilePathDir)
            rightFile.writeText(rightText)
            logger().info("正在写入右边文件:${rightFilePath}")
        } catch (e: Exception) {
            logger().error("创建文件失败:${e.message}")
        }
    }
}