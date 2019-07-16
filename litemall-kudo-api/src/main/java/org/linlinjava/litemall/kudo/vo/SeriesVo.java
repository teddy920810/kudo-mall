package org.linlinjava.litemall.kudo.vo;

import org.linlinjava.litemall.db.domain.LitemallSeries;

import java.util.ArrayList;
import java.util.List;

public class SeriesVo {

    private Integer id;

    private String name;

    private String iconUrl;

    private List<SeriesVo> children;

    private SeriesVo parent;

    public SeriesVo(LitemallSeries series, List<LitemallSeries> children) {
        this.id = series.getId();
        this.name = series.getName();
        this.iconUrl = series.getIconUrl();
        List<SeriesVo> c = new ArrayList<>();
        children.forEach(item -> c.add(new SeriesVo(item)));
        this.children = c;
    }


    public SeriesVo(LitemallSeries series, LitemallSeries parent) {
        this.id = series.getId();
        this.name = series.getName();
        this.iconUrl = series.getIconUrl();
        this.parent = new SeriesVo(parent);
    }

    public SeriesVo(Integer id, String name, String iconUrl, SeriesVo parent) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.parent = parent;
    }

    public SeriesVo(Integer id, String name, String iconUrl) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
    }

    public SeriesVo(LitemallSeries series) {
        this.id = series.getId();
        this.name = series.getName();
        this.iconUrl = series.getIconUrl();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public SeriesVo getParent() {
        return parent;
    }

    public void setParent(SeriesVo parent) {
        this.parent = parent;
    }

    public List<SeriesVo> getChildren() {
        return children;
    }

    public void setChildren(List<SeriesVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SeriesVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", children=" + children +
                ", parent=" + parent +
                '}';
    }
}
