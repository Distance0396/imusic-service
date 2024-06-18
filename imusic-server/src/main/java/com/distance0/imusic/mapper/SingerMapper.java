package com.distance0.imusic.mapper;

import com.distance0.imusic.dto.SingerPageDto;
import com.distance0.imusic.entity.Singer;
import com.distance0.imusic.vo.SingerVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author: XiangJing
 * @date: 2024/5/20 下午1:23
 * @description:
 */
@Mapper
public interface SingerMapper {
    /**
     * 添加歌手
     * @param singer
     */
    void insert(Singer singer);

    /**
     * 获取歌手
     * @param singer
     * @return
     */
    Singer getSinger(Singer singer);

    /**
     * 根据歌手id查询姓名
     * @param id
     * @return
     */
    @Select("select name from singer where id = #{id}")
    String getSingerName(Long id);

    /**
     * 歌手分页查询
     * @param dto
     * @return
     */
    Page<SingerVo> pageQuery(SingerPageDto dto);

    /**
     * 修改歌手
     * @param singer
     */
    void update(Singer singer);

    /**
     * 查询所有歌手 前台
     * @return
     */
    @Select("select * from singer where status = 1")
    List<Singer> selectAll();

    /**
     * 随机获取歌手
     * @return
     */
    @Select("select * from singer where status = 1 order by RAND() LIMIT 20")
    List<Singer> getRandomSinger();

    /**
     * 根据id查询歌手头像
     * @param id
     * @return
     */
    @Select("select avatar from singer where id = #{id}")
    String getImageBySingerId(Long id);

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @Select("select * from singer where name like concat('%',#{keyword},'%')")
    List<Singer> dimSingerByKeyword(String keyword);


//    List<>

}
