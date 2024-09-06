package com.hc.nacoscompare.service

import com.hc.nacoscompare.config.CompareStrategy
import com.hc.nacoscompare.config.NacosResourceConfig
import com.hc.nacoscompare.domain.NacosInstance
import com.hc.nacoscompare.domain.NacosInstanceClients
import com.hc.nacoscompare.service.compare.NacosAttrCompare
import com.hc.nacoscompare.service.compare.NacosTextCompare
import com.hc.nacoscompare.service.compare.NamespaceKeyCompare
import org.springframework.stereotype.Service

@Service
class NacosCompareService(private val nacosResourceConfig: NacosResourceConfig) {
    private var nacosInstanceClients: NacosInstanceClients = NacosInstanceClients()


    fun loadClients() {
        val nacosInstanceClients = NacosInstanceClients()
        nacosResourceConfig.clients.map { config ->
            val nacosInstance = NacosInstance(config)
            nacosInstanceClients.add(nacosInstance)
        }
        this.nacosInstanceClients = nacosInstanceClients
    }

    fun compare() {
        // 获取客户端，对象解构
        val compareConfig = nacosResourceConfig.compare
        val left = compareConfig.left
        val right = compareConfig.right
        val leftClient = left?.let { nacosInstanceClients.getClientByName(it) }
        val rightClient = right?.let { nacosInstanceClients.getClientByName(it) }
        // 比较namaspace
        val leftNameSpace = leftClient!!.namespaces2Map()
        val rightNameSpace = rightClient!!.namespaces2Map()
        // 比对配置项
        when (compareConfig.compareStrategy) {
            CompareStrategy.CONFIG_ATTR -> {
                NacosAttrCompare(nacosResourceConfig).compare(leftNameSpace, rightNameSpace)
            }

            CompareStrategy.CONFIG_TEXT_DIFF -> {
                NacosTextCompare(nacosResourceConfig).compare(leftNameSpace, rightNameSpace)
            }

            CompareStrategy.NAMESPACE_KEYS -> {
                NamespaceKeyCompare().compare(leftClient, rightClient)
            }

            CompareStrategy.ALL -> {
                NamespaceKeyCompare().compare(leftClient, rightClient)
                NacosTextCompare(nacosResourceConfig).compare(leftNameSpace, rightNameSpace)
                NacosAttrCompare(nacosResourceConfig).compare(leftNameSpace, rightNameSpace)
            }
        }
    }


}
