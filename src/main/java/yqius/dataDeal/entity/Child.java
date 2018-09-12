package yqius.dataDeal.entity;

import java.util.List;
import java.util.Objects;

public class Child {
    String CateName;
    String childName;
    Integer count;

    public String getCateName() {
        return CateName;
    }

    public void setCateName(String cateName) {
        CateName = cateName;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Child() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(CateName, child.CateName) &&
                Objects.equals(childName, child.childName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CateName, childName);
    }
}
