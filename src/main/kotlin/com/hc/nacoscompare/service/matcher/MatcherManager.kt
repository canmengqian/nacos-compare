package com.hc.nacoscompare.service.matcher

import com.hc.nacoscompare.service.FileNameMatcher

class MatcherManager {
    companion object InnerMatcherManager {

        private var hasRegist = false

        private var matchers: MutableMap<String, FileNameMatcher> = mutableMapOf()

        init {
            defaultRegister()
        }

        fun defaultRegister() {
            if (!hasRegist) {
                val defaultNameMatcher = DefaultNameMatcher()
                matchers[defaultNameMatcher.matcherName()] = defaultNameMatcher
                val sameNameMatcher = SameNameMatcher()
                matchers[sameNameMatcher.matcherName()] = sameNameMatcher
                val bestSimilarNameMatcher = BestSimilarNameMatcher()
                matchers[bestSimilarNameMatcher.matcherName()] = bestSimilarNameMatcher
            }
        }
    }


   fun get(name:String):FileNameMatcher{
      return  matchers[name]?:SameNameMatcher()
    }
}