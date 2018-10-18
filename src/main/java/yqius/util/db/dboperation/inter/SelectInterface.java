package yqius.util.db.dboperation.inter;

import java.util.Collection;

public interface SelectInterface {
    public Object selectObeject(String sql);
    public void insertData(String sql);
    /**
     *
     * @param sql
     * @param clazz --Entity
     * @return array of entity
     */
    public Collection selectArray(String sql,Class clazz);
}
