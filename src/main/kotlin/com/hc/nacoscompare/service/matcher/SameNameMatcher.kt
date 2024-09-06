package com.hc.nacoscompare.service.matcher

import cn.hutool.core.collection.CollUtil
import cn.hutool.core.text.CharSequenceUtil
import com.hc.nacoscompare.service.FileNameMatcher

class SameNameMatcher : FileNameMatcher {
    override fun matcherName(): String {
        return "samename"
    }

    override fun match(name: String, matchingNames: List<String>?): Pair<String, String?> {
        assert(CharSequenceUtil.isNotBlank(name))
        if (CollUtil.isNotEmpty(matchingNames)) {
            if (equals(matchingNames?.contains(name))) {
                return name to name
            }
        }
        return name to null
    }
}