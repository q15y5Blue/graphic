package yqius.dataDeal.entity;

import yqius.dataDeal.util.StrUtil;

import java.util.List;

public class Cate {
    public String typeName;
    public String content;
    public Integer count;
    public List contentList;

    public List getContentList() {
        return contentList;
    }

    public void setContentList(List contentList) {
        this.contentList = contentList;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        this.setContentList(StrUtil.arrayToListSplit(content,","));
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Cate() { }

    @Override
    public String toString() {
        return "Cate:{" +
                "\"typeName\":\"" + typeName + '\"' +
                ", \"count\":" + count +
                ",\"list\":\""+contentList+
                "}";
    }
}
