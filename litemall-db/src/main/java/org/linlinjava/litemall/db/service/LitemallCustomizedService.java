package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCustomizedMapper;
import org.linlinjava.litemall.db.domain.LitemallCustomized;
import org.linlinjava.litemall.db.domain.LitemallCustomizedExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallCustomizedService {

    @Resource
    private LitemallCustomizedMapper customizedMapper;

    public List<LitemallCustomized> query(int offset, int limit) {
        LitemallCustomizedExample example = new LitemallCustomizedExample();
        example.or().andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return customizedMapper.selectByExample(example);
    }

    public List<LitemallCustomized> queryByType(String type) {
        LitemallCustomizedExample example = new LitemallCustomizedExample();
        example.or().andTypeEqualTo(type).andDeletedEqualTo(false);
        return customizedMapper.selectByExample(example);
    }

    public void add(LitemallCustomized customized) {
        customized.setAddTime(LocalDateTime.now());
        customized.setUpdateTime(LocalDateTime.now());
        customizedMapper.insertSelective(customized);
    }

    public LitemallCustomized findById(Integer id) {
        return customizedMapper.selectByPrimaryKey(id);
    }

    public int updateById(LitemallCustomized customized) {
        customized.setUpdateTime(LocalDateTime.now());
        return customizedMapper.updateByPrimaryKeySelective(customized);
    }

    public void deleteById(Integer id) {
        customizedMapper.logicalDeleteByPrimaryKey(id);
    }
}
