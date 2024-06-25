package com.distance0.imusic.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午7:22
 * @description:
 */
@Configuration
@Slf4j
public class DruidConfiguration {
    @PostConstruct
    public void setProperties(){
        System.setProperty("druid.mysql.usePingMethod","false");
    }
}
