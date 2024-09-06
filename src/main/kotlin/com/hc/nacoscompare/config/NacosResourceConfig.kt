package com.hc.nacoscompare.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "sys.app.nacos")
class NacosResourceConfig {
    var compare: NacosCompareConfig = NacosCompareConfig()
    var clients: List<NacosClientConfig> = listOf()

}