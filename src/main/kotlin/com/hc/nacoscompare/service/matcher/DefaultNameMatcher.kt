package com.hc.nacoscompare.service.matcher

import com.hc.nacoscompare.service.FileNameMatcher

class DefaultNameMatcher : FileNameMatcher {
    companion object {
        val SAME_NAME_MATCHER = SameNameMatcher()
    }

    override fun matcherName(): String {
        return "default"
    }

    override fun match(name: String, matchingNames: List<String>?): Pair<String, String?> {
        return SAME_NAME_MATCHER.match(name, matchingNames)
    }

}