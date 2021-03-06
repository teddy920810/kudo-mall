package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallProductMapper;
import org.linlinjava.litemall.db.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class LitemallProductService {
    LitemallProduct.Column[] columns = new LitemallProduct.Column[]{LitemallProduct.Column.id, LitemallProduct.Column.name, LitemallProduct.Column.brief, LitemallProduct.Column.picUrl};
    @Resource
    private LitemallProductMapper productMapper;
    @Resource
    private LitemallSeriesService seriesService;

    /**
     * 获取分类下的商品
     *
     * @param seriesId
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallProduct> queryBySeries(Integer seriesId, int offset, int limit) {
        LitemallProductExample example = new LitemallProductExample();
        LitemallSeries series = seriesService.findById(seriesId);
        if("L1".equals(series.getLevel())){
            List<LitemallSeries> seriesList = seriesService.queryByPid(seriesId);
            if(seriesList.size() > 0){
                example.or().andSeriesIdIn(seriesList.stream().map(LitemallSeries::getId).collect(toList())).andDeletedEqualTo(false);
            }else {
                example.or().andSeriesIdEqualTo(seriesId).andDeletedEqualTo(false);
            }
        }else {
            example.or().andSeriesIdEqualTo(seriesId).andDeletedEqualTo(false);
        }

        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return productMapper.selectByExampleSelective(example);
    }

    public List<LitemallProduct> querySelective(String name, Integer page, Integer size, String sort, String order) {
        LitemallProductExample example = new LitemallProductExample();
        LitemallProductExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return productMapper.selectByExampleWithBLOBs(example);
    }

    public int updateById(LitemallProduct product) {
        product.setUpdateTime(LocalDateTime.now());
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public void deleteById(Integer id) {
        productMapper.logicalDeleteByPrimaryKey(id);
    }

    public LitemallProduct findById(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    public void add(LitemallProduct product) {
        product.setAddTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        productMapper.insertSelective(product);
    }
}
