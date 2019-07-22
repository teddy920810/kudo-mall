package org.linlinjava.litemall.kudo.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCustomized;
import org.linlinjava.litemall.db.domain.LitemallMessage;
import org.linlinjava.litemall.db.service.LitemallCustomizedService;
import org.linlinjava.litemall.db.service.LitemallMessageService;
import org.linlinjava.litemall.kudo.vo.FooterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 联系我们
 */
@RestController
@RequestMapping("/kudo/customized")
@Validated
public class KudoCustomizedController {
    private final Log logger = LogFactory.getLog(KudoCustomizedController.class);

    @Autowired
    private LitemallCustomizedService customizedService;

    @GetMapping("/{type}")
    public Object list(@PathVariable String type) {
        List<LitemallCustomized> data = customizedService.queryByType(type);
        return ResponseUtil.ok(data);
    }

}
