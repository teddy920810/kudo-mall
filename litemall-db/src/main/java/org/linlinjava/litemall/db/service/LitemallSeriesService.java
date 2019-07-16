package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallSeriesMapper;
import org.linlinjava.litemall.db.domain.LitemallSeries;
import org.linlinjava.litemall.db.domain.LitemallSeriesExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallSeriesService {
    @Resource
    private LitemallSeriesMapper seriesMapper;
    private LitemallSeries.Column[] CHANNEL = {LitemallSeries.Column.id, LitemallSeries.Column.name, LitemallSeries.Column.iconUrl};

    public List<LitemallSeries> queryL1WithoutRecommend(int offset, int limit) {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return seriesMapper.selectByExample(example);
    }

    public List<LitemallSeries> queryL1(int offset, int limit) {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return seriesMapper.selectByExample(example);
    }

    public List<LitemallSeries> queryL1() {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return seriesMapper.selectByExample(example);
    }

    public List<LitemallSeries> queryByPid(Integer pid) {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return seriesMapper.selectByExample(example);
    }

    public List<LitemallSeries> queryL2ByIds(List<Integer> ids) {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return seriesMapper.selectByExample(example);
    }

    public LitemallSeries findById(Integer id) {
        return seriesMapper.selectByPrimaryKey(id);
    }

    public List<LitemallSeries> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        LitemallSeriesExample example = new LitemallSeriesExample();
        LitemallSeriesExample.Criteria criteria = example.createCriteria();

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
        return seriesMapper.selectByExample(example);
    }

    public int updateById(LitemallSeries series) {
        series.setUpdateTime(LocalDateTime.now());
        return seriesMapper.updateByPrimaryKeySelective(series);
    }

    public void deleteById(Integer id) {
        seriesMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(LitemallSeries series) {
        series.setAddTime(LocalDateTime.now());
        series.setUpdateTime(LocalDateTime.now());
        seriesMapper.insertSelective(series);
    }

    public List<LitemallSeries> queryChannel() {
        LitemallSeriesExample example = new LitemallSeriesExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return seriesMapper.selectByExampleSelective(example, CHANNEL);
    }
}
