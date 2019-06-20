package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminGoodsService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallProduct;
import org.linlinjava.litemall.db.service.LitemallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/product")
@Validated
public class AdminProductController {
    private final Log logger = LogFactory.getLog(AdminProductController.class);

    @Autowired
    private LitemallProductService productService;

    @Autowired
    private AdminGoodsService adminGoodsService;

    /**
     * 查询产品
     *
     * @param name
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("admin:product:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "产品管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        return productService.querySelective(name, page, limit, sort, order);
    }

    /**
     * 编辑产品
     *
     * @param product
     * @return
     */
    @RequiresPermissions("admin:product:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "产品管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallProduct product) {
        return productService.updateById(product);
    }

    /**
     * 删除商品
     *
     * @param product
     * @return
     */
    @RequiresPermissions("admin:product:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "产品管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallProduct product) {
        Integer id = product.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        productService.deleteById(id);
        return ResponseUtil.ok();
    }

    /**
     * 添加产品
     *
     * @param product
     * @return
     */
    @RequiresPermissions("admin:product:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "产品管理"}, button = "上架")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallProduct product) {
        Object error = validate(product);
        if (error != null) {
            return error;
        }
        productService.add(product);
        return ResponseUtil.ok(product);
    }


    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:product:read")
    @RequiresPermissionsDesc(menu = {"商品管理", "产品管理"}, button = "详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        LitemallProduct product = productService.findById(id);
        return ResponseUtil.ok(product);
    }

    private Object validate(LitemallProduct product) {
        String name = product.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String keywords = product.getKeywords();
        if (StringUtils.isEmpty(keywords)) {
            return ResponseUtil.badArgument();
        }
        String brief = product.getBrief();
        if (StringUtils.isEmpty(brief)) {
            return ResponseUtil.badArgument();
        }

        String detail = product.getDetail();
        if (StringUtils.isEmpty(detail)) {
            return ResponseUtil.badArgument();
        }

        String picUrl = product.getPicUrl();
        if (StringUtils.isEmpty(picUrl)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @GetMapping("/cat")
    public Object list2() {
        return adminGoodsService.list2();
    }

}
