package yqius.dataDeal.entity;

import yqius.dataDeal.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Child {
    private String xtype;
    private String b_name;
    private Integer count;
    private List<String> b_nameList = new ArrayList();

    public Child() {
    }

    public String getXtype() {
        return xtype;
    }

    public void setXtype(String xtype) {
        this.xtype = xtype;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        setB_nameList(StrUtil.arrayToListSplit(b_name,","));
        this.b_name = b_name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<String> getB_nameList() {
        return b_nameList;
    }

    public void setB_nameList(List<String> b_nameList) {
        this.b_nameList = b_nameList;
    }

    @Override
    public String toString() {
        return "Child:{" +
                "xtype:'" + xtype + '\'' +
                ", b_name:'" + b_name + '\'' +
                ", count:" + count +
                '}';
    }
}
