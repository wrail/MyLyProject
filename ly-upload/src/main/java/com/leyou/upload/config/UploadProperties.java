package com.leyou.upload.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "ly.upload")//对应EnableConfi使用
public class UploadProperties {
    private String baseUrl;
    private List allowTypes;
}
