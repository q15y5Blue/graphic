package yqius.dataDeal.entity;

import java.util.ArrayList;

public class Type {
    public String typeName;
    public String content;
    public Integer count;
    public ArrayList<String> list;

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
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
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Type() { }

    public static void main(String[] args) {
        System.out.println("xxxxx");
    }
}
