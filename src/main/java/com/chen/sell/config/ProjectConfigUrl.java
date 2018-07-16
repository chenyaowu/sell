package com.chen.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "projectconfigurl")
@Component
public class ProjectConfigUrl {

    /**
     * 微信公众平台授权url
     */
    public String wechatMpAuthrize;

    /**
     * 微信开放平台授权url
     */
    private String wechatOpenAuthorize;


    /**
     * 点餐系统
     */
    private String sell;
}
