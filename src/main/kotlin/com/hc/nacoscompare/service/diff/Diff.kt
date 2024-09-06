package com.hc.nacoscompare.service.diff

interface Diff<T,R> {
    fun diff(left: T, right: R)
}