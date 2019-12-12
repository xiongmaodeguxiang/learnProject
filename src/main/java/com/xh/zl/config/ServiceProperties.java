package com.xh.zl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author:zl
 * @Description
 * @Date: 2019/12/12 14:03
 */
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ServiceProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
