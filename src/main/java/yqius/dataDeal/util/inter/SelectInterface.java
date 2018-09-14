package yqius.dataDeal.util.inter;

import java.util.Collection;
import java.util.List;

public interface SelectInterface {
    public Object selectObeject(String sql);
    public Collection selectArray(String sql,Class clazz);
}
