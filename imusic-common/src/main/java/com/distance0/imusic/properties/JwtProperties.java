package com.distance0.imusic.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: XiangJing
 * @date: 2024/5/18 下午3:14
 * @description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "music.jwt")
public class JwtProperties {
    /**
     * 用户端用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

    /**
     * 管理端生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

}
