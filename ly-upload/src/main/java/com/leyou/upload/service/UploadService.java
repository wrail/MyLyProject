package com.leyou.upload.service;


import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import lombok.extern.slf4j.Slf4j;
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
public class UploadService {

    public static final List<String> ALLOW_TYPE = Arrays.asList("image/jpg", "image/png");

    public String upload(MultipartFile file) {
        String contentType = file.getContentType();
        if (!ALLOW_TYPE.contains(contentType)) {
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //传入目标文件
            File dest = new File("E:\\tempPath\\", file.getOriginalFilename());
            //保存文件到本地,transferTo
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("上传失败！", e);
            throw new LyException(ExceptionEnum.UPLOAD_ERROR);
        }
        //返回路径
        String url = "cdddddd";
        return url;
    }
}
