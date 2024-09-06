package com.hc.nacoscompare.service.compare

import cn.hutool.core.text.CharSequenceUtil
import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.config.NacosResourceConfig
import com.hc.nacoscompare.domain.Config
import com.hc.nacoscompare.domain.NameSpace
import com.hc.nacoscompare.service.ConfigParser
import com.hc.nacoscompare.service.DataIdTypeJudge
import com.hc.nacoscompare.service.matcher.MatcherManager
import com.hc.nacoscompare.service.diff.DiffParam
import com.hc.nacoscompare.service.diff.DiffUtil
import java.util.stream.Collectors

class NacosAttrCompare(private val nacosResourceConfig: NacosResourceConfig) : NacosCompare<Map<String?, NameSpace>,Map<String?, NameSpace>> {
    override fun compare(left: Map<String?, NameSpace>, right: Map<String?, NameSpace>) {
        left.forEach { (key, space) ->
            // 判断右侧是否存在key
            if (right.containsKey(key)) {
                val rightSpace = right[key]
                // 获取右边所有的dataid
                val rightDataMap = rightSpace?.configs?.stream()?.collect(Collectors.toMap({ it.dataId }, { it }))
                space.configs?.forEach {
                    compareSingleConfig(it, rightDataMap)
                }
            }
        }
    }

    private fun compareSingleConfig(it: Config, rightConfigMap: Map<String, Config>?) {
        val nameMatcher = nacosResourceConfig.compare.nameMatcher
        val fileNameMatcher = MatcherManager().get(nameMatcher)
        // 匹配右边最佳匹配的dataid
        val (_, rightDataId) = fileNameMatcher.match(it.dataId, rightConfigMap?.keys?.toList())
        if (CharSequenceUtil.isBlank(rightDataId)) {
            logger().info("未找到匹配的dataid,left：${it.dataId}")
            return;
        }
        logger().info("待比对的dataid,left：${it.dataId}，right:${rightDataId}")
        var rightConfig: Config? = null
        if (CharSequenceUtil.isNotBlank(rightDataId)) {
            rightConfig = rightConfigMap!![rightDataId]
        }
        val leftCanParse = ConfigParser.canParse(it)
        val rightCanParse = ConfigParser.canParse(rightConfig!!)
        if (leftCanParse && rightCanParse) {
            val leftContent = ConfigParser().parse(it)
            val rightContent = ConfigParser().parse(rightConfig)
            DiffUtil.diff(DiffParam(DataIdTypeJudge.getType(config = it), leftContent, rightContent))

        } else {
            logger().error("left:${it.content}，right:${rightConfig.content}")
        }
    }
}