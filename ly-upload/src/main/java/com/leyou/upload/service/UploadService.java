package com.leyou.upload.service;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@EnableConfigurationProperties(UploadProperties.class)
public class UploadService {
    @Autowired
    private UploadProperties uploadProperties;//配置文件，baseUrl等

    @Autowired
    private FastFileStorageClient storageClient;

    //public static final List<String> ALLOW_TYPE = Arrays.asList("image/jpg", "image/png"); //有了uploadProperties就不需要这个了

    public String upload(MultipartFile file) {
        String contentType = file.getContentType();
        if (!uploadProperties.getAllowTypes().contains(contentType)) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //获取文件后缀（效率低）
            //String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            //使用StringUtils下的截取工具，在最后一个的后边截取，所以不用+1
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            //上传图片不带缩略图
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            //返回路径
            String url = uploadProperties.getBaseUrl() + storePath.getFullPath();
            return url;
        } catch (IOException e) {
            log.error("[文件上传]：上传失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_ERROR);
        }
    }
}
