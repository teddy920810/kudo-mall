package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.service.LitemallSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin/site")
@Validated
public class AdminSiteController {
    private final Log logger = LogFactory.getLog(AdminSiteController.class);

    @Autowired
    private LitemallSiteService siteService;

    @RequiresPermissions("admin:site:list")
    @RequiresPermissionsDesc(menu={"商场管理" , "网站图文设置"}, button="详情")
    @GetMapping()
    public Object listMall() {
        Map<String, LitemallSite> data = siteService.queryAll();
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:site:update")
    @RequiresPermissionsDesc(menu={"商场管理" , "网站图文设置"}, button="编辑")
    @PostMapping("")
    public Object updateMall(@RequestBody Map<String,LitemallSite> data ) {
        siteService.updateSite(data);
        return ResponseUtil.ok();
    }
}
