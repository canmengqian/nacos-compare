package com.hc.nacoscompare.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;


@SpringBootTest
class NacosResourceConfigTest {
    @Resource
    NacosResourceConfig nacosResourceConfig;
    @Test
    public void test_1() {
        System.out.println(nacosResourceConfig);
    }
}

