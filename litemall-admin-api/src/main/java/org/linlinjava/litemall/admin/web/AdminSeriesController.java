package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.vo.SeriesVo;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallSeries;
import org.linlinjava.litemall.db.service.LitemallSeriesService;
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
@RequestMapping("/admin/series")
@Validated
public class AdminSeriesController {
    private final Log logger = LogFactory.getLog(AdminSeriesController.class);

    @Autowired
    private LitemallSeriesService seriesService;

    @RequiresPermissions("admin:series:list")
    @RequiresPermissionsDesc(menu={"产品管理" , "系列管理"}, button="查询")
    @GetMapping("/list")
    public Object list() {
        List<SeriesVo> seriesVoList = new ArrayList<>();

        List<LitemallSeries> seriesList = seriesService.queryByPid(0);
        for(LitemallSeries series : seriesList){
            SeriesVo seriesVO = new SeriesVo();
            seriesVO.setId(series.getId());
            seriesVO.setIconUrl(series.getIconUrl());
            seriesVO.setName(series.getName());
            seriesVO.setLevel(series.getLevel());

            List<SeriesVo> children = new ArrayList<>();
            List<LitemallSeries> subSeriesList = seriesService.queryByPid(series.getId());
            for(LitemallSeries subSeries : subSeriesList){
                SeriesVo subSeriesVo = new SeriesVo();
                subSeriesVo.setId(subSeries.getId());
                subSeriesVo.setIconUrl(subSeries.getIconUrl());
                subSeriesVo.setName(subSeries.getName());
                subSeriesVo.setLevel(subSeries.getLevel());

                children.add(subSeriesVo);
            }

            seriesVO.setChildren(children);
            seriesVoList.add(seriesVO);
        }

        return ResponseUtil.okList(seriesVoList);
    }

    private Object validate(LitemallSeries series) {
        String name = series.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        String level = series.getLevel();
        if (StringUtils.isEmpty(level)) {
            return ResponseUtil.badArgument();
        }
        if (!level.equals("L1") && !level.equals("L2")) {
            return ResponseUtil.badArgumentValue();
        }

        Integer pid = series.getPid();
        if (level.equals("L2") && (pid == null)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @RequiresPermissions("admin:series:create")
    @RequiresPermissionsDesc(menu={"产品管理" , "系列管理"}, button="添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallSeries series) {
        Object error = validate(series);
        if (error != null) {
            return error;
        }
        seriesService.add(series);
        return ResponseUtil.ok(series);
    }

    @RequiresPermissions("admin:series:read")
    @RequiresPermissionsDesc(menu={"产品管理" , "系列管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallSeries series = seriesService.findById(id);
        return ResponseUtil.ok(series);
    }

    @RequiresPermissions("admin:series:update")
    @RequiresPermissionsDesc(menu={"产品管理" , "系列管理"}, button="编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallSeries series) {
        Object error = validate(series);
        if (error != null) {
            return error;
        }

        if (seriesService.updateById(series) == 0) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:series:delete")
    @RequiresPermissionsDesc(menu={"产品管理" , "系列管理"}, button="删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallSeries series) {
        Integer id = series.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        seriesService.deleteById(id);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:series:list")
    @GetMapping("/l1")
    public Object catL1() {
        // 所有一级分类目录
        List<LitemallSeries> l1CatList = seriesService.queryL1();
        List<Map<String, Object>> data = new ArrayList<>(l1CatList.size());
        for (LitemallSeries series : l1CatList) {
            Map<String, Object> d = new HashMap<>(2);
            d.put("value", series.getId());
            d.put("label", series.getName());
            data.add(d);
        }
        return ResponseUtil.okList(data);
    }
}
