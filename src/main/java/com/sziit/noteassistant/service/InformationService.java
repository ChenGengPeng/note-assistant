package com.sziit.noteassistant.service;


import com.sziit.noteassistant.pojo.entity.Information;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface InformationService {

    public Information addInform(Information information);
    public Information findById(Integer iid);
    public Information findByUid(Integer uid);
    public Information updateInform(Information information);
    public Information findInformByPhone(String phone);
    public void deleteByUid(Integer uid);
}
