package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallCategoryMapper;
import org.linlinjava.litemall.db.dao.LitemallNavMapper;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallCategoryExample;
import org.linlinjava.litemall.db.domain.LitemallNav;
import org.linlinjava.litemall.db.domain.LitemallNavExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallNavService {
    @Resource
    private LitemallNavMapper navMapper;
    private LitemallNav.Column[] CHANNEL = {LitemallNav.Column.id, LitemallNav.Column.name};


    public List<LitemallNav> queryL1(int offset, int limit) {
        LitemallNavExample example = new LitemallNavExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return navMapper.selectByExample(example);
    }

    public List<LitemallNav> queryL1() {
        LitemallNavExample example = new LitemallNavExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return navMapper.selectByExample(example);
    }

    public List<LitemallNav> queryByPid(Integer pid) {
        LitemallNavExample example = new LitemallNavExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return navMapper.selectByExample(example);
    }

    public List<LitemallNav> queryL2ByIds(List<Integer> ids) {
        LitemallNavExample example = new LitemallNavExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return navMapper.selectByExample(example);
    }

    public LitemallNav findById(Integer id) {
        return navMapper.selectByPrimaryKey(id);
    }

    public List<LitemallNav> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallNavExample example = new LitemallNavExample();
        LitemallNavExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return navMapper.selectByExample(example);
    }

    public int updateById(LitemallNav nav) {
        nav.setUpdateTime(LocalDateTime.now());
        return navMapper.updateByPrimaryKeySelective(nav);
    }

    public void deleteById(Integer id) {
        navMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallNav nav) {
        nav.setAddTime(LocalDateTime.now());
        nav.setUpdateTime(LocalDateTime.now());
        navMapper.insertSelective(nav);
    }

    public List<LitemallNav> queryChannel() {
        LitemallNavExample example = new LitemallNavExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return navMapper.selectByExampleSelective(example, CHANNEL);
    }
}
