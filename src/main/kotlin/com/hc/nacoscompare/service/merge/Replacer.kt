package com.hc.nacoscompare.service.merge

/**
 * 替换器，用于将左右两个配置合并后，对合并后的配置进行替换
 */
interface Replacer {
    fun replace(key: String,value:Any): Any

    fun exstingKey(key: String): Boolean
}