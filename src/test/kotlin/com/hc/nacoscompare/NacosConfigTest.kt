package com.hc.nacoscompare

import com.alibaba.nacos.api.NacosFactory
import com.alibaba.nacos.api.config.ConfigService
import org.junit.jupiter.api.Test
import org.yaml.snakeyaml.Yaml
import java.util.*


class NacosConfigTest {
    @Test
    fun test() {
        val serverAddr = "192.168.1.93:8848"
        val dataId = "test.yaml"
        val group = "hcdm"
        val properties = Properties()
        properties["serverAddr"] = serverAddr
        properties.put("username","nacos")
        properties.put("password","nacos")
        properties.put("namespace","hcdm")
        val configService: ConfigService = NacosFactory.createConfigService(properties)
        val content = configService.getConfig(dataId, group, 5000)
        println(content)
        val yaml = Yaml()
        val ymlrs=yaml.load<Map<String,Objects>>(content)
        println(ymlrs)
    }
}