package com.xh.zl.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/12 14:07
 */
@Configuration
@EnableConfigurationProperties(ServiceProperties.class)
public class ServicePropertiesAutoConfig {

}
