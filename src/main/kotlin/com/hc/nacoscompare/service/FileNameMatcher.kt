package com.hc.nacoscompare.service

interface FileNameMatcher {
    open fun matcherName():String

    open fun match(name: String, matchingNames: List<String>?): Pair<String, String?>
}