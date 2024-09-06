package com.hc.nacoscompare.config

import org.springframework.http.HttpMethod

enum class NacosApis(val api: String, val method: HttpMethod) {

    LOGIN("http://%s/nacos/v1/auth/users/login", HttpMethod.POST),
    NAMESPACE_LIST("http://%s/nacos/v1/console/namespaces?accessToken=%s", HttpMethod.GET),
    CONFIG_LIST(
        "http://%s/nacos/v1/cs/configs?dataId=&group=&appName=&config_tags=&pageNo=1&pageSize=1000&tenant=%s&search=blur&accessToken=%s&username=%s",
        HttpMethod.GET
    )
}