package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.JacksonUtil;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallSite;
import org.linlinjava.litemall.db.service.LitemallSiteService;
import org.linlinjava.litemall.db.service.LitemallSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/site")
@Validated
public class AdminLitemallSiteController {
    private final Log logger = LogFactory.getLog(AdminLitemallSiteController.class);

    @Autowired
    private LitemallSiteService siteService;

    @RequiresPermissions("admin:site:list")
    @RequiresPermissionsDesc(menu={"配置管理" , "网站图文设置"}, button="详情")
    @GetMapping()
    public Object list() {
        List<LitemallSite> data = siteService.queryAll();
        return ResponseUtil.ok(data);
    }

    @RequiresPermissions("admin:site:update")
    @RequiresPermissionsDesc(menu={"配置管理" , "网站图文设置"}, button="编辑")
    @PutMapping()
    public Object updateMall(@RequestBody String body ) {
        /*List<LitemallSite> data = JacksonUtil.parseObject(body,LitemallSite.class);
        siteService.updateSite(data);*/
        return ResponseUtil.ok();
    }
}
