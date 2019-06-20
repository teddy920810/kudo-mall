package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallMessage;
import org.linlinjava.litemall.db.service.LitemallMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/message")
@Validated
public class AdminMessageController {
    private final Log logger = LogFactory.getLog(AdminMessageController.class);

    @Autowired
    private LitemallMessageService messageService;

    @RequiresPermissions("admin:message:list")
    @RequiresPermissionsDesc(menu={"商场管理" , "留言管理"}, button="查询")
    @GetMapping("/list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallMessage> messageList = messageService.query( page, limit);
        return ResponseUtil.okList(messageList);
    }

}
