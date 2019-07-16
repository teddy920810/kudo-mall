package org.linlinjava.litemall.kudo.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallMessage;
import org.linlinjava.litemall.db.service.LitemallMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 联系我们
 */
@RestController
@RequestMapping("/kudo/contact")
@Validated
public class KudoContactController {
    private final Log logger = LogFactory.getLog(KudoContactController.class);

    @Autowired
    private LitemallMessageService messageService;

    /**
     * 留言
     */
    @PostMapping("/")
    public Object message(@RequestBody LitemallMessage message) {
        messageService.add(message);
        return ResponseUtil.ok("留言成功");
    }

}
