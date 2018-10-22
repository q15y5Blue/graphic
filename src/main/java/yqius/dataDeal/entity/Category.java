package yqius.dataDeal.entity;

import java.io.UnsupportedEncodingException;

/**
 * 分类数据处理
 * shp_category表
 */
public class Category {
    private Integer categoryid;
    private String categoryname;
    private Integer levels;
    private Integer parents;
    private String shortname;

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
            this.categoryname = categoryname;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Integer getParents() {
        return parents;
    }

    public void setParents(Integer parents) {
        this.parents = parents;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryid=" + categoryid +
                ", categoryname='" + categoryname + '\'' +
                ", levels=" + levels +
                ", parents=" + parents +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}
