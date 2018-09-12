package yqius.dataDeal.util.inter;

import yqius.dataDeal.util.BeanUtil;

import java.util.Collection;
import java.util.List;

public interface selectInterface {
    public Object selectObeject(String sql);
    public Collection selectArray(String sql);
}
