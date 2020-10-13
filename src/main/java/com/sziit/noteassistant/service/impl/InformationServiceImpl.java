package com.sziit.noteassistant.service.impl;


import com.sziit.noteassistant.mapper.AudioMapper;
import com.sziit.noteassistant.mapper.InformationMapper;
import com.sziit.noteassistant.pojo.entity.Audio;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.service.AudioService;
import com.sziit.noteassistant.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Service
public class InformationServiceImpl implements InformationService {

    @Autowired
    private InformationMapper informationMapper;
    @Override
    public Information addInform(Information information) {
        informationMapper.add_infor(information);
        Information information1 = new Information();
        information1.setUId(information.getUId());
        return informationMapper.findOne(information1);
    }

    @Override
    public Information findById(Integer iid) {
       return informationMapper.getById(iid);
    }

    @Override
    public Information findByUid(Integer uid) {
        return informationMapper.getByUid(uid);
    }

    @Override
    public boolean updateInform(Information information) {
        informationMapper.updateInfor(information);
        return true;
    }

    @Override
    public void deleteByUid(Integer uid) {
        informationMapper.deletByUid(uid);
    }
}
