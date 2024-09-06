package com.hc.nacoscompare.config

class NacosCompareConfig {
    var left: String? = null
    var right: String? = null
    var compareStrategy: CompareStrategy = CompareStrategy.CONFIG_TEXT_DIFF
    var nameMatcher: String = "default"
}