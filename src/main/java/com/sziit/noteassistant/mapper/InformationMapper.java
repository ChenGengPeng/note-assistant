package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Information;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Mapper
public interface InformationMapper {

    Information findOne(Information information);
    int add_infor(Information information);
    int updateInfor(Information information);
    @Select("select * from information where information.i_id = #{iid}" )
    Information getById(Integer iid);
    @Select("select * from information where information.u_id = #{uid}" )
    Information getByUid(Integer uid);
}
