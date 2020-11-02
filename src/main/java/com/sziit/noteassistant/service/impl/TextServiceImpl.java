package com.sziit.noteassistant.service.impl;

import com.sziit.noteassistant.pojo.entity.Picture;
import com.sziit.noteassistant.pojo.entity.Text;
import com.sziit.noteassistant.mapper.TextMapper;
import com.sziit.noteassistant.service.TextService;
import com.sziit.noteassistant.utils.TransactionalJug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
@Service
public class TextServiceImpl implements TextService{

    @Autowired
    private TextMapper textMapper;

    @Override
    public List<Text> selectTextByNid(Integer nId) {
        return textMapper.selectTextByNid(nId);
    }

    @Override
    public void addText(Text text) {
        TransactionalJug.JudgeTransaction(textMapper.addText(text));
    }

    @Override
    public Text updateText(Text text) {
        TransactionalJug.JudgeTransaction(textMapper.updateText(text));
        return textMapper.selectOne(text);
    }

    @Override
    public void deleteText(Integer tId) {
        TransactionalJug.JudgeTransaction(textMapper.deleteText(tId));
    }

    @Override
    public boolean deleteTexts(Integer[] tIds) {
        for (Integer tId: tIds) {
            deleteText(tId);
        }
        return true;
    }

    @Override
    public Text selectTextByTid(Integer tId) {
        return textMapper.selectTextByTid(tId);
    }

    @Override
    public Text selectOne(Text text) {
        return textMapper.selectOne(text);
    }
}
