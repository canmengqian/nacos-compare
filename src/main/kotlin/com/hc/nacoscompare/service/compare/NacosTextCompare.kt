package com.hc.nacoscompare.service.compare

import cn.hutool.core.text.CharSequenceUtil
import com.github.difflib.text.DiffRow
import com.github.difflib.text.DiffRowGenerator
import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.config.NacosResourceConfig
import com.hc.nacoscompare.domain.NameSpace
import com.hc.nacoscompare.service.matcher.MatcherManager
import java.util.stream.Collectors

class NacosTextCompare(private val nacosResourceConfig: NacosResourceConfig) :
    NacosCompare<Map<String?, NameSpace>, Map<String?, NameSpace>> {

    override fun compare(left: Map<String?, NameSpace>, right: Map<String?, NameSpace>) {
        val nameMatcher = nacosResourceConfig.compare.nameMatcher
        val fileNameMatcher = MatcherManager().get(nameMatcher)
        left.forEach { t, lspace ->
            if (right.containsKey(t)) {
                val rightSpace = right[t]
                // 获取右边所有的dataid
                val rightDataMap = rightSpace?.configs?.stream()?.collect(Collectors.toMap({ it.dataId }, { it }))
                lspace.configs?.forEach {
                    // 匹配右边最佳匹配的dataid
                    val (_, rightDataId) = fileNameMatcher.match(it.dataId, rightDataMap?.keys?.toList())
                    if (CharSequenceUtil.isBlank(rightDataId)) {
                        logger().info("未找到匹配的dataid,left：${it.dataId}")
                        return@forEach
                    }
                    logger().info("待比对的dataid,left：${it.dataId}，right:${rightDataId}")
                    if (CharSequenceUtil.isNotBlank(rightDataId)) {
                        val rightConfig = rightDataMap!![rightDataId]
                        diffContent(it.content, rightConfig!!.content)
                    }
                }

            }

        }
    }


    fun diffContent(left: String, right: String) {
        val generator = DiffRowGenerator.create()
            .showInlineDiffs(true)
            .inlineDiffByWord(true)
            .oldTag { f: Boolean? -> "~" }
            .newTag { f: Boolean? -> "**" }
            .build()
        val rows: List<DiffRow> = generator.generateDiffRows(
            listOf(left),
            listOf(right)
        )

        /* println("|original|new|")
         println("|--------|---|")
         for (row in rows) {
             println("|" + row.oldLine + "|" + row.newLine + "|")
         }*/
    }

}