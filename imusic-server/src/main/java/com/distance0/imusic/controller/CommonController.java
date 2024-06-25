package com.distance0.imusic.controller;

import com.distance0.imusic.constant.MessageConstant;
import com.distance0.imusic.entity.Album;
import com.distance0.imusic.entity.Music;
import com.distance0.imusic.mapper.AlbumMapper;
import com.distance0.imusic.mapper.MusicFormMapper;
import com.distance0.imusic.mapper.MusicMapper;
import com.distance0.imusic.mapper.SingerMapper;
import com.distance0.imusic.result.R;
import com.distance0.imusic.utils.AliOssUtil;
import com.distance0.imusic.vo.MusicImageVo;
import com.distance0.imusic.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author: XiangJing
 * @date: 2024/6/20 下午8:32
 * @description:
 */
@RestController
@RequestMapping("/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {

    @Autowired
    private MusicFormMapper musicFormMapper;

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private MusicMapper musicMapper;

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
            String upload = aliOssUtil.upload(file.getBytes(), "music/audio/"+fileName);
            log.info(upload);
            return R.success(upload);
        } catch (Exception e) {
            log.error("文件上传失败：{}", e.getMessage());
        }

        return R.error(MessageConstant.UPLOAD_FAILED);
    }


    /**
     * 搜索
     * @param keyword
     * @return
     */
    @GetMapping("/search")
    public R<SearchVo> search(@RequestParam("keyword") String keyword){
        log.info("关键字搜索：{}",keyword);

        List<Music> musicList = musicMapper.dimMusicByKeyword(keyword);
        /*
          通过音乐中的专辑id查询封面
         */
        List<MusicImageVo> musicImageVos = musicList.stream().map(x -> {
            MusicImageVo musicImageVo = new MusicImageVo();
            BeanUtils.copyProperties(x, musicImageVo);

            if (x.getAlbumId() == null) musicImageVo.setImage(singerMapper.getImageBySingerId(x.getSingerId()));
            else musicImageVo.setImage(albumMapper.select(Album.builder().id(x.getAlbumId()).build()).getImage());
//            Album albumByAlbum = albumMapper.getAlbumByAlbum(Album.builder().id(x.getAlbumId()).build());
//            musicImageVo.setImage(albumByAlbum.getImage());
            return musicImageVo;
        }).collect(Collectors.toList());

        SearchVo build = SearchVo.builder()
                .singerList(singerMapper.dimSingerByKeyword(keyword))
                .albumList(albumMapper.dimAlbumByKeyword(keyword))
                .musicList(musicImageVos)
                .build();

        return R.success(build);
    }
}
