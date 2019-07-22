package org.linlinjava.litemall.kudo.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.service.LitemallSeriesService;
import org.linlinjava.litemall.db.service.LitemallSiteService;
import org.linlinjava.litemall.db.service.LitemallSystemConfigService;
import org.linlinjava.litemall.kudo.service.KudoProductService;
import org.linlinjava.litemall.kudo.vo.FooterVo;
import org.linlinjava.litemall.kudo.vo.SeriesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 首页服务
 */
@RestController
@RequestMapping("/kudo")
@Validated
public class KudoHomeController {
    private final Log logger = LogFactory.getLog(KudoHomeController.class);

    @Autowired
    private LitemallSystemConfigService systemConfigService;
    @Autowired
    private LitemallSiteService siteService;
    @Autowired
    private KudoProductService kudoProductService;

    /**
     * 首页数据
     * @return 首页数据
     */
    @GetMapping("/home")
    public Object index() {
        Map<String, LitemallSite> siteMap = siteService.listIndex();
        List<SeriesVo> seriesList = kudoProductService.getAllSeries();
        Map<String, Object> data = new HashMap<>();
        data.put("site", siteMap);
        data.put("seriesList", seriesList);
        return ResponseUtil.ok(data);
    }

    /**
     * 品牌数据
     * @return 品牌数据
     */
    @GetMapping("/site/brand")
    public Object brand() {
        Map<String, LitemallSite> siteMap = siteService.listBrand();
        return ResponseUtil.ok(siteMap);
    }

    /**
     * 定制数据
     * @return 定制数据
     */
    @GetMapping("/site/customized")
    public Object customized() {
        Map<String, LitemallSite> siteMap = siteService.listCustomized();
        return ResponseUtil.ok(siteMap);
    }

    /**
     * 底部数据
     * @return 底部数据
     */
    @GetMapping("/footer")
    public Object footer() {
        Map<String, String> data = systemConfigService.listMail();
        return ResponseUtil.ok(new FooterVo(data));
    }

}
