package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Text;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface TextMapper{

    public List<Text> selectTextByNid(Integer nId);

    public void addText(Text text);


    public Text selectOne(Text text);
    @Delete("delete from text where t_id = #{tId}")
    int deleteText(Integer tId);

    void updateText(Text text);
}
