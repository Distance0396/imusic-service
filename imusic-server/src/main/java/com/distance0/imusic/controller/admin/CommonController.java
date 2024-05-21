package com.distance0.imusic.controller.admin;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.result.R;
import com.distance0.imusic.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: XiangJing
 * @date: 2024/5/19 下午9:54
 * @description:
 */
@RestController
@RequestMapping("/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public R<String> upload(@RequestPart("file") MultipartFile file){
        log.info("文件上传：{}",file);

        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + substring;
            String upload = aliOssUtil.upload(file.getBytes(), "music/"+fileName);
            log.info(upload);
            return R.success(upload);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.getMessage());
        }

        return R.error(MessageConstant.UPLOAD_FAILED);
    }

    @PostMapping("/upload/audio")
    @ApiOperation("音频上传")
    public R<String> uploadAudio(@RequestPart("file") MultipartFile file){
        log.info("文件上传：{}",file);

        try {
            String originalFilename = file.getOriginalFilename();
            String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID().toString() + substring;
            String upload = aliOssUtil.upload(file.getBytes(), "music/audio"+fileName);
            log.info(upload);
            return R.success(upload);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.getMessage());
        }

        return R.error(MessageConstant.UPLOAD_FAILED);
    }
}
