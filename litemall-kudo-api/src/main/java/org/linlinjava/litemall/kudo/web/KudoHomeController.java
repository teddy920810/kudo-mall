package org.linlinjava.litemall.kudo.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.service.LitemallSystemConfigService;
import org.linlinjava.litemall.kudo.vo.FooterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 首页数据
     * @return 首页数据
     */
    @GetMapping("/home")
    public Object index() {
        return ResponseUtil.ok("");
    }

    /**
     * 首页数据
     * @return 首页数据
     */
    @GetMapping("/footer")
    public Object footer() {
        Map<String, String> data = systemConfigService.listMail();
        return ResponseUtil.ok(new FooterVo(data));
    }

}
