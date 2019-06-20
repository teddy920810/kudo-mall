package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCustomized;
import org.linlinjava.litemall.db.service.LitemallCustomizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/customized")
@Validated
public class AdminCustomizedController {
    private final Log logger = LogFactory.getLog(AdminCustomizedController.class);

    @Autowired
    private LitemallCustomizedService customizedService;

    @RequiresPermissions("admin:customized:list")
    @RequiresPermissionsDesc(menu={"商场管理" , "定制管理"}, button="查询")
    @GetMapping("/list")
    public Object list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit) {
        List<LitemallCustomized> customizedList = customizedService.query( page, limit);
        return ResponseUtil.okList(customizedList);
    }

    private Object validate(LitemallCustomized customized) {
        String type = customized.getType();
        if (StringUtils.isEmpty(type)) {
            return ResponseUtil.badArgument();
        }
        String title = customized.getTitle();
        if (StringUtils.isEmpty(title)) {
            return ResponseUtil.badArgument();
        }
        String titleEn = customized.getTitleEn();
        if (StringUtils.isEmpty(titleEn)) {
            return ResponseUtil.badArgument();
        }

        String desc = customized.getDesc();
        if (StringUtils.isEmpty(desc)) {
            return ResponseUtil.badArgument();
        }

        String picUrl = customized.getPicUrl();
        if (StringUtils.isEmpty(picUrl)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:customized:create")
    @RequiresPermissionsDesc(menu={"商场管理" , "定制管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallCustomized customized) {
        Object error = validate(customized);
        if (error != null) {
            return error;
        }
        customizedService.add(customized);
        return ResponseUtil.ok(customized);
    }

    @RequiresPermissions("admin:customized:read")
    @RequiresPermissionsDesc(menu={"商场管理" , "定制管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallCustomized customized = customizedService.findById(id);
        return ResponseUtil.ok(customized);
    }

    @RequiresPermissions("admin:customized:update")
    @RequiresPermissionsDesc(menu={"商场管理" , "定制管理"}, button="编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallCustomized customized) {
        Object error = validate(customized);
        if (error != null) {
            return error;
        }
        if (customizedService.updateById(customized) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(customized);
    }

    @RequiresPermissions("admin:customized:delete")
    @RequiresPermissionsDesc(menu={"商场管理" , "定制管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallCustomized customized) {
        Integer id = customized.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        customizedService.deleteById(id);
        return ResponseUtil.ok();
    }

}
