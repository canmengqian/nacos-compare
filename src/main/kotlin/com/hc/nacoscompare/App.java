package com.hc.nacoscompare;

import com.hc.nacoscompare.config.NacosResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zhengzz
 * @version 1.0.0
 * @className App
 * @description TODO
 * @date 2024/8/29
 */
@ConfigurationPropertiesScan(basePackages = {"com.hc"})
@EnableConfigurationProperties(value = {NacosResourceConfig.class})
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
