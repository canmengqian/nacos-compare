package com.hc.nacoscompare.domain

import cn.hutool.core.text.CharSequenceUtil

class NacosInstanceClients {
    private var clients: MutableMap<String, NacosInstance> = mutableMapOf()

    fun getClientByName(name: String): NacosInstance? {
        return clients[name]
    }

    fun add(nacosInstance: NacosInstance) {
        if (CharSequenceUtil.isNotBlank(nacosInstance.name)) {
            clients[nacosInstance.name] = nacosInstance
        }
    }
}