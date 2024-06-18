package com.distance0.imusic.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: XiangJing
 * @date: 2024/6/17 上午8:30
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "music.wechat")
@Data
public class WeChatProperties {

    private String appid; //小程序的appid
    private String secret; //小程序的秘钥
    private String apiV3Key; //证书解密的密钥
    private String weChatPayCertFilePath; //平台证书
}
