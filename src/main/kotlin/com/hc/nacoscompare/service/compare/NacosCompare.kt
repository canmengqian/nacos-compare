package com.hc.nacoscompare.service.compare

interface NacosCompare<T,R> {
    fun compare(left: T, right: R)
}