package org.linlinjava.litemall.admin.vo;

import java.util.List;

public class NavVo {
    private Integer id;
    private String name;
    private String level;
    private List<NavVo> children;

    public List<NavVo> getChildren() {
        return children;
    }

    public void setChildren(List<NavVo> children) {
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


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
