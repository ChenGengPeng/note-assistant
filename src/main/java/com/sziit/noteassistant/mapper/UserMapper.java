package com.sziit.noteassistant.mapper;


import com.sziit.noteassistant.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Mapper
public interface UserMapper{

    int add(User user);

    User findOne(User user);
    @Select("select * from user where user.u_id = #{uId}")
    User getById(Integer uId);

    User updateUser(User user);

    @Update("update user set user.password = #{password} where user.u_id = #{id}")
    int changePassword(@Param("id")Integer id, @Param("password") String password);

}
