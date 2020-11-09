package com.sziit.noteassistant.mapper;

import com.sziit.noteassistant.pojo.entity.Share;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author CGP 1577992659@qq.com
 * @date 2020/11/9  23:14
 */
@Mapper
public interface ShareMapper {

    int insertShareKey(Share share);

    Share findOneByNid(Integer nId);

    Share findOneByShareId(String shareId);
}
