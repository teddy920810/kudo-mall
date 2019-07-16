package org.linlinjava.litemall.kudo.service;

import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallSeries;
import org.linlinjava.litemall.db.domain.LitemallProduct;
import org.linlinjava.litemall.db.service.LitemallSeriesService;
import org.linlinjava.litemall.db.service.LitemallProductService;
import org.linlinjava.litemall.kudo.vo.SeriesVo;
import org.linlinjava.litemall.kudo.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KudoProductService {

    @Autowired
    private LitemallProductService productService;
    @Autowired
    private LitemallSeriesService seriesService;

    public Object detail(Integer id){
        LitemallProduct product = productService.findById(id);
        SeriesVo seriesVo = null;
        LitemallSeries series = seriesService.findById(product.getSeriesId());
        if (series != null){
            LitemallSeries parentSeries = seriesService.findById(series.getPid());
            seriesVo = new SeriesVo(series, parentSeries);
        }
        return  ResponseUtil.ok(new ProductVo(product, seriesVo));
    }

    public List<SeriesVo> getAllSeries(){
        List<LitemallSeries> seriesListL1 = seriesService.queryL1();
        List<SeriesVo> seriesVos = new ArrayList<>();
        seriesListL1.forEach(item ->{
            List<LitemallSeries> seriesListL2 = seriesService.queryByPid(item.getId());
            SeriesVo seriesVo = new SeriesVo(item, seriesListL2);
            seriesVos.add(seriesVo);
        });
        return seriesVos;
    }

    public SeriesVo getCurrentSeries(Integer id){
        LitemallSeries series = seriesService.findById(id);
        if ("L1".equals(series.getLevel())){
            return new SeriesVo(series);
        }else {
            return new SeriesVo(seriesService.findById(series.getPid()));
        }
    }
}
