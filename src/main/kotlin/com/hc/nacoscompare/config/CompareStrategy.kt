package com.hc.nacoscompare.config

enum class CompareStrategy {
   // 配置属性比较
    CONFIG_ATTR,
   // 文本比较
    CONFIG_TEXT_DIFF,
    NAMESPACE_KEYS,
    ALL,

}