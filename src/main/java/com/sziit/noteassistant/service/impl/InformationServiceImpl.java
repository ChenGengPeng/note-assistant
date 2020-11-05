package com.sziit.noteassistant.service.impl;


import com.sziit.noteassistant.mapper.InformationMapper;
import com.sziit.noteassistant.pojo.entity.Information;
import com.sziit.noteassistant.service.InformationService;
import com.sziit.noteassistant.utils.TransactionalJug;
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
        TransactionalJug.JudgeTransaction(informationMapper.add_infor(information));
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
        TransactionalJug.JudgeTransaction(informationMapper.updateInfor(information));
        return true;
    }

    @Override
    public Information findInformByPhone(String phone) {
        Information information = new Information();
        information.setPhone(phone);
        return informationMapper.findOne(information);
    }

    @Override
    public void deleteByUid(Integer uid) {
        TransactionalJug.JudgeTransaction(informationMapper.deletByUid(uid));
    }
}
