package com.cxp.springcloudconfigclient.bootstrap;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.env.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义 Bootstrap 配置（实现 SpringCloud 的接口）
 *
 *  上面我们使用了实现 Spring 标准接口的方式自定义了 Bootstrap 配置项，接下来我们来实现 Spring Cloud 接口来实现该功能。
 *
 * @author 程
 * @date 2019/7/28 上午10:11
 */
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            propertySources.addFirst(createPropertySource());
        }
        return null;
    }

    private PropertySource createPropertySource() {
        Map<String, Object> source = new HashMap<>();
        source.put("spring.application.name", "AlanShelby-SpringCloud");

        PropertySource propertySource = new MapPropertySource("alan-property-source", source);

        return propertySource;
    }
}
