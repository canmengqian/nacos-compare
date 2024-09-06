package com.hc.nacoscompare;

import com.hc.nacoscompare.config.NacosResourceConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AppConfigTest {
    @Resource
    NacosResourceConfig nacosResourceConfig;

    @Test
    public void test() {
        System.out.printf(nacosResourceConfig.toString());
    }
}