package com.sziit.noteassistant.service;

import com.sziit.noteassistant.pojo.entity.Text;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CGP
 * @since 2020-10-04
 */
public interface TextService{

    public List<Text> selectTextByNid(Integer nId);

    public void addText(Text text);

    public void updateText(Text text);

    public void deleteText(Integer tId);

    public boolean deleteTexts(Integer[] tIds);

}
