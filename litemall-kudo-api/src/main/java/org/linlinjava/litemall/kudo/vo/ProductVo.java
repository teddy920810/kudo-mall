package org.linlinjava.litemall.kudo.vo;

import org.linlinjava.litemall.db.domain.LitemallProduct;

public class ProductVo {

    private String name;

    private SeriesVo series;

    private String[] gallery;

    private String[] keywords;

    private String brief;

    private String buyLink;

    private String detail;

    public ProductVo(String name, SeriesVo series, String[] gallery, String[] keywords, String brief, String buyLink, String detail) {
        this.name = name;
        this.series= series;
        this.gallery = gallery;
        this.keywords = keywords;
        this.brief = brief;
        this.buyLink = buyLink;
        this.detail = detail;
    }

    public ProductVo(LitemallProduct product, SeriesVo series) {
        this.name = product.getName();
        this.series = series;
        this.gallery = product.getGallery();
        this.keywords = product.getKeywords().split(",");
        this.brief = product.getBrief();
        this.buyLink = product.getBuyLink();
        this.detail = product.getDetail();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SeriesVo getSeries() {
        return series;
    }

    public void setSeries(SeriesVo series) {
        this.series = series;
    }

    public String[] getGallery() {
        return gallery;
    }

    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
