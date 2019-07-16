package org.linlinjava.litemall.admin.vo;

import java.util.List;

public class SeriesVo {
    private Integer id;
    private String name;
    private String iconUrl;
    private String level;
    private List<SeriesVo> children;

    public List<SeriesVo> getChildren() {
        return children;
    }

    public void setChildren(List<SeriesVo> children) {
        this.children = children;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
