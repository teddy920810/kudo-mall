package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.vo.CategoryVo;
import org.linlinjava.litemall.admin.vo.NavVo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallCategory;
import org.linlinjava.litemall.db.domain.LitemallNav;
import org.linlinjava.litemall.db.service.LitemallCategoryService;
import org.linlinjava.litemall.db.service.LitemallNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/nav")
@Validated
public class AdminNavController {
    private final Log logger = LogFactory.getLog(AdminNavController.class);

    @Autowired
    private LitemallNavService navService;

    @RequiresPermissions("admin:nav:list")
    @RequiresPermissionsDesc(menu={"商场管理" , "导航管理"}, button="查询")
    @GetMapping("/list")
    public Object list() {
        List<NavVo> navVoList = new ArrayList<>();

        List<LitemallNav> navList = navService.queryByPid(0);
        for(LitemallNav nav : navList){
            NavVo navVo = new NavVo();
            navVo.setId(nav.getId());
            navVo.setName(nav.getName());
            navVo.setLevel(nav.getLevel());

            List<NavVo> children = new ArrayList<>();
            List<LitemallNav> subNavList = navService.queryByPid(nav.getId());
            for(LitemallNav subNav : subNavList){
                NavVo subNavVo = new NavVo();
                subNavVo.setId(subNav.getId());
                subNavVo.setName(subNav.getName());
                subNavVo.setLevel(subNav.getLevel());

                children.add(subNavVo);
            }

            navVo.setChildren(children);
            navVoList.add(navVo);
        }

        return ResponseUtil.okList(navVoList);
    }

    private Object validate(LitemallNav nav) {
        String name = nav.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        String level = nav.getLevel();
        if (StringUtils.isEmpty(level)) {
            return ResponseUtil.badArgument();
        }
        if (!level.equals("L1") && !level.equals("L2")) {
            return ResponseUtil.badArgumentValue();
        }

        Integer pid = nav.getPid();
        if (level.equals("L2") && (pid == null)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @RequiresPermissions("admin:nav:create")
    @RequiresPermissionsDesc(menu={"商场管理" , "导航管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallNav nav) {
        Object error = validate(nav);
        if (error != null) {
            return error;
        }
        navService.add(nav);
        return ResponseUtil.ok(nav);
    }

    @RequiresPermissions("admin:nav:read")
    @RequiresPermissionsDesc(menu={"商场管理" , "导航管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallNav nav = navService.findById(id);
        return ResponseUtil.ok(nav);
    }

    @RequiresPermissions("admin:nav:update")
    @RequiresPermissionsDesc(menu={"商场管理" , "导航管理"}, button="编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallNav nav) {
        Object error = validate(nav);
        if (error != null) {
            return error;
        }

        if (navService.updateById(nav) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    //@RequiresPermissions("admin:nav:delete")
    @RequiresPermissionsDesc(menu={"商场管理" , "导航管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallNav nav) {
        Integer id = nav.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        navService.deleteById(id);
        return ResponseUtil.ok();
    }

    //@RequiresPermissions("admin:nav:list")
    @GetMapping("/l1")
    public Object catL1() {
        // 所有一级分类目录
        List<LitemallNav> l1NavList = navService.queryL1();
        List<Map<String, Object>> data = new ArrayList<>(l1NavList.size());
        for (LitemallNav nav : l1NavList) {
            Map<String, Object> d = new HashMap<>(2);
            d.put("value", nav.getId());
            d.put("label", nav.getName());
            data.add(d);
        }
        return ResponseUtil.okList(data);
    }
}
