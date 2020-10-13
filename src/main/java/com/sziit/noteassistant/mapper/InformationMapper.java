package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.Information;
import org.apache.ibatis.annotations.Delete;
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

    Information getById(Integer iId);

    Information getByUid(Integer uId);
    @Delete("delete from information where information.u_id = #{uId}")
    void deletByUid(Integer uId);
}
