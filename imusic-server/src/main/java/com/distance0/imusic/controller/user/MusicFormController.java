package com.distance0.imusic.controller.user;

import com.distance0.imusic.context.BaseContext;
import com.distance0.imusic.dto.MusicFormDto;
import com.distance0.imusic.entity.MusicForm;
import com.distance0.imusic.entity.MusicFormMusic;
import com.distance0.imusic.result.R;
import com.distance0.imusic.service.MusicFormService;
import com.distance0.imusic.vo.MusicFormVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: XiangJing
 * @date: 2024/6/2 下午3:41
 * @description:
 */
@Slf4j
@Api(tags = "歌单接口")
@RestController
@RequestMapping("/user/music-form")
public class MusicFormController {

    @Autowired
    private MusicFormService musicFormService;

    /**
     * 根据用户id查询收藏歌单
     * @return
     */
//    @ApiOperation("根据用户id查询收藏歌单")
//    @GetMapping("/user")
//    public R<List<MusicFormVo>> getMusicFormByUserId(){
//        log.info("根据用户id查询收藏歌单: {}", BaseContext.getContextId());
//        List<MusicFormVo> musicFormVoList = musicFormService.getMusicFormByUserId();
//        return R.success(musicFormVoList);
//    }

    /**
     * 根据歌单id查询歌单详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("根据歌单id查询歌单")
    public R<MusicFormVo> getMusicFormById(@PathVariable Long id){
        log.info("根据歌单id查询歌单:{}",id);
        MusicFormVo musicForm = musicFormService.getMusicFormById(id);
        return R.success(musicForm);
    }

    /**
     * 新增歌单
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("新增歌单")
    public R addMusicForm(){
        log.info("新增歌单: {}", BaseContext.getContextId());
        musicFormService.addMusicForm(BaseContext.getContextId());
        return R.success();
    }

    /**
     * 添加歌曲至歌单
     * @param musicFormMusic
     * @return
     */
    @PostMapping
    @ApiOperation("添加歌曲至歌单")
    public R appendMusicForm(@RequestBody MusicFormMusic musicFormMusic){
        log.info("添加歌曲至歌单:{}",musicFormMusic);
        musicFormService.appendMusicForm(musicFormMusic);
        return R.success();
    }

    /**
     * 删除歌单歌曲
     * @param musicFormMusic
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除歌单歌曲")
    public R deleteMusic(@RequestBody MusicFormMusic musicFormMusic) {
        log.info("删除歌单歌曲:{}",musicFormMusic);
        musicFormService.deleteMusic(musicFormMusic);
        return R.success();
    }

    /**
     * 修改歌单
     * @param dto
     * @return
     */
    @PutMapping
    @ApiOperation("修改歌单")
    public R updateMusicForm(@RequestBody MusicFormDto dto){
        log.info("修改歌单：{}", dto);
        musicFormService.update(dto);
        return R.success();
    }
}
