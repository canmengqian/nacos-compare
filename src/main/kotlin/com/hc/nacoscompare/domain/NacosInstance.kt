package com.hc.nacoscompare.domain

import cn.hutool.core.text.CharSequenceUtil
import cn.hutool.http.HttpRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hc.nacoscompare.common.objectMapper
import com.hc.nacoscompare.config.NacosApis
import com.hc.nacoscompare.config.NacosClientConfig
import java.util.*

class NacosInstance(conf: NacosClientConfig) {
    var name: String = ""
    var token: String = ""
    var namespaces: List<NameSpace> = emptyList()
    var config: NacosClientConfig = conf

    init {
        // check 配置
        assert(Objects.nonNull(conf))
        assert(CharSequenceUtil.isNotBlank(conf.addr), { "地址不能为空" })
        assert(CharSequenceUtil.isNotBlank(conf.username), { "用户名不能为空" })
        assert(CharSequenceUtil.isNotBlank(conf.password), { "密码不能为空" })
        name = conf.name!!
        doLogin()
        namespaceList()
        configWithNameSpace()
    }

    /**
     * 命名空间转map
     */
    fun namespaces2Map(): Map<String?, NameSpace> {
        val map = LinkedHashMap<String?, NameSpace>()
        if (namespaces.isNotEmpty()) {
            namespaces.forEachIndexed { _, it ->
                val key = it.namespace ?: it.namespaceShowName
                map[key] = it
            }
        }
        return map
    }

    private fun doLogin() {
        val param = mapOf("username" to config.username, "password" to config.password)
        val response = HttpRequest.post(NacosApis.LOGIN.api.format(config.addr)).form(param).execute()
        if (response.isOk) {
            val body = response.body()
            val resp = body.objectMapper().readValue(body, Map::class.java)
            this.token = resp["accessToken"].toString()
        }
    }

    /**
     * 获取namespace
     */
    private fun namespaceList() {

        val response = HttpRequest.get(NacosApis.NAMESPACE_LIST.api.format(config.addr, this.token)).execute()
        // 解析响应
        if (response.isOk) {
            val body = response.body()
            val json: NameSpaceListResponse = jacksonObjectMapper().readValue(body, NameSpaceListResponse::class.java)
            this.namespaces = json.data!!
        }
    }

    /**
     * 获取每个namespace下的config
     */
    private fun configWithNameSpace() {
        this.namespaces.forEach {
            val name = CharSequenceUtil.emptyToDefault(it.namespace, it.namespaceShowName)
            if (CharSequenceUtil.isNotBlank(name)) {
                val api: String = NacosApis.CONFIG_LIST.api.format(config.addr, name, token, config.username)
                val response = HttpRequest.get(api).execute()
                // 解析响应
                if (response.isOk) {
                    val json = response.objectMapper().readValue(response.body(), ConfigResponse::class.java)
                    it.configs = json.pageItems
                }
            }
        }
    }

}