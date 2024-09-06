package com.hc.nacoscompare.service.merge

import cn.hutool.core.lang.Dict

/**
 * key删除器，用于删除dict指定的key
 */
interface KeyDeletor {
    fun delete(dict: Dict)
}