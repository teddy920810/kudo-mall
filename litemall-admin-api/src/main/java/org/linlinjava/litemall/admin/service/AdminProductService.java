package org.linlinjava.litemall.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.vo.SerVo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.LitemallSeriesService;
import org.linlinjava.litemall.db.service.LitemallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminProductService {

    private final Log logger = LogFactory.getLog(AdminGoodsService.class);

    @Autowired
    private LitemallProductService productService;
    @Autowired
    private LitemallSeriesService seriesService;

    public Object detail(Integer id) {
        LitemallProduct product = productService.findById(id);

        Integer seriesId = product.getSeriesId();
        LitemallSeries series = seriesService.findById(seriesId);
        Integer[] seriesIds = new Integer[]{};
        if (series != null) {
            Integer parentCategoryId = series.getPid();
            seriesIds = new Integer[]{parentCategoryId, seriesId};
        }

        Map<String, Object> data = new HashMap<>();
        data.put("product", product);
        data.put("seriesIds", seriesIds);

        return ResponseUtil.ok(data);
    }

    public Object list2() {
        // http://element-cn.eleme.io/#/zh-CN/component/cascader
        // 管理员设置“所属分类”
        List<LitemallSeries> l1SerList = seriesService.queryL1();
        List<SerVo> seriesList = new ArrayList<>(l1SerList.size());

        for (LitemallSeries l1 : l1SerList) {
            SerVo l1SerVo = new SerVo();
            l1SerVo.setValue(l1.getId());
            l1SerVo.setLabel(l1.getName());

            List<LitemallSeries> l2SerList = seriesService.queryByPid(l1.getId());
            List<SerVo> children = new ArrayList<>(l2SerList.size());
            for (LitemallSeries l2 : l2SerList) {
                SerVo l2SerVo = new SerVo();
                l2SerVo.setValue(l2.getId());
                l2SerVo.setLabel(l2.getName());
                children.add(l2SerVo);
            }
            l1SerVo.setChildren(children);

            seriesList.add(l1SerVo);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("seriesList", seriesList);
        return ResponseUtil.ok(data);
    }
}
