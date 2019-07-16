package org.linlinjava.litemall.kudo.web;

import com.github.pagehelper.PageInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallGoods;
import org.linlinjava.litemall.db.domain.LitemallMessage;
import org.linlinjava.litemall.db.domain.LitemallProduct;
import org.linlinjava.litemall.db.service.LitemallMessageService;
import org.linlinjava.litemall.db.service.LitemallProductService;
import org.linlinjava.litemall.kudo.service.KudoProductService;
import org.linlinjava.litemall.kudo.vo.SeriesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 联系我们
 */
@RestController
@RequestMapping("/kudo/product")
@Validated
public class KudoProductController {
    private final Log logger = LogFactory.getLog(KudoProductController.class);

    @Autowired
    private KudoProductService kudoProductService;
    @Autowired
    private LitemallProductService productService;

    /**
     * 产品列表
     */
    @GetMapping("/")
    public Object list(Integer seriesId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallProduct> productList = productService.queryBySeries(seriesId,page,limit);
        PageInfo<LitemallProduct> pagedList = PageInfo.of(productList);

        List<SeriesVo> seriesList = kudoProductService.getAllSeries();

        if (seriesId==null){
            seriesId = seriesList.get(0).getId();
        }
        SeriesVo currentSeries = kudoProductService.getCurrentSeries(seriesId);
        Map<String, Object> data = new HashMap<>();
        data.put("productList", productList);
        data.put("seriesList", seriesList);
        data.put("currentSeries", currentSeries);
        data.put("total", pagedList.getTotal());
        data.put("page", pagedList.getPageNum());
        data.put("limit", pagedList.getPageSize());
        data.put("pages", pagedList.getPages());
        return ResponseUtil.ok(data);
    }

    /**
     * 产品详情
     */
    @GetMapping("/{id}")
    public Object detail(@PathVariable Integer id) {
        return kudoProductService.detail(id);
    }

}
