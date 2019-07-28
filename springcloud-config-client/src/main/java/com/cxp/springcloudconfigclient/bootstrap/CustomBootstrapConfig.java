package com.cxp.springcloudconfigclient.bootstrap;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 Bootstrap 配置（实现 Spring 标准接口 ）
 *
 * 自定义配置，首先我们实现 Spring 的标准接口来完成自定义Bootstrap 配置
 *
 * https://zhuanlan.zhihu.com/p/63416148
 *
 * @author 程
 * @date 2019/7/28 上午8:39
 */
@Configuration
public class CustomBootstrapConfig implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        // 获取 PropertySource
        MutablePropertySources propertySources = environment.getPropertySources();
        // 定义一个新的 PropertySource 放在首位
        propertySources.addFirst(createPropertySource());
    }

    private PropertySource<?> createPropertySource() {
        Map<String, Object> source = new HashMap<>();
        source.put("custom.name", "bootstrap-custom");

        PropertySource propertySource = new MapPropertySource("my-property-source", source);

        return propertySource;
    }
}
