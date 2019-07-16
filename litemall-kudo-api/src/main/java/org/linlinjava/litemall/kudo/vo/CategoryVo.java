package org.linlinjava.litemall.kudo.vo;

import org.linlinjava.litemall.db.domain.LitemallCategory;

public class CategoryVo {

    private Integer id;

    private String name;

    private CategoryVo parent;



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

    public CategoryVo getParent() {
        return parent;
    }

    public void setParent(CategoryVo parent) {
        this.parent = parent;
    }

    public CategoryVo(LitemallCategory category,LitemallCategory parent) {
        this.id = category.getId();
        this.name = category.getName();
        this.parent = new CategoryVo(parent.getId(),parent.getName());
    }

    public CategoryVo(Integer id, String name, CategoryVo parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public CategoryVo() {
    }

    public CategoryVo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
