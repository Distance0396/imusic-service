package com.distance0.imusic.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:26
 * @description:
 */

@Component
@Data
@ConfigurationProperties(prefix = "music.alioss")
public class AliOssProperties {
    private String endpoint;
    private String bucketName;
}
