package com.hc.nacoscompare.service.matcher

import com.hc.nacoscompare.common.logger
import com.hc.nacoscompare.service.FileNameMatcher
import me.xdrop.fuzzywuzzy.FuzzySearch

class BestSimilarNameMatcher : FileNameMatcher {
    override fun matcherName(): String {
        return "similar"
    }

    override fun match(name: String, matchingNames: List<String>?): Pair<String, String?> {
        var maxScore: Int = 0
        var maxScoreIndex = 0
        matchingNames?.forEachIndexed { index, s ->
            val curScore = FuzzySearch.ratio(name, s)
            if (curScore >= maxScore) {
                maxScore = curScore
                maxScoreIndex = index
            }
        }
        logger().info("比对结果：${name} 和 ${matchingNames?.get(maxScoreIndex)}, 相似度为：${maxScore}")
        return name to matchingNames?.get(maxScoreIndex)
    }
}