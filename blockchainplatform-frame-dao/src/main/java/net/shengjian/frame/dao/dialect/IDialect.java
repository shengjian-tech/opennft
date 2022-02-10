/**
 * 数据库方言接口
 */

package net.shengjian.frame.dao.dialect;

import net.shengjian.frame.util.Page;

public interface IDialect {
    /**
     * 得到分页语句
     *
     * @param sql  正常的select 语句,没有order by
     * @param page 分页对象
     * @return
     */
    String getPageSql(String sql, Page page);

    /**
     * 获取数据库类型
     *
     * @return
     */
    String getDataBaseType();

    /**
     * 是否包含 rownum 列
     *
     * @return
     */
    boolean isRowNumber();

    /**
     * 数据库是否支持事务
     *
     * @return
     */
    default boolean supportTransaction() {
        return true;
    }

    /**
     * 根据数据类型更新 手动编写的 UpdateFinder的语句,用于处理数据库兼容,例如 clickhouse的 UPDATE 和 DELETE
     *
     * @param updateSql
     * @return
     */
    default String reUpdateFinderSQL(String updateSql) {
        return updateSql;
    }

    /**
     * 根据表名生成Update语句
     *
     * @param tableName
     * @return
     */
    default String updateSQLByTableName(String tableName) {
        return "UPDATE " + tableName + " SET ";
    }

    /**
     * 根据表名生成DELETE语句
     *
     * @param tableName
     * @return
     */
    default String deleteSQLByTableName(String tableName) {
        return "DELETE FROM  " + tableName;
    }
}
