package net.shengjian.frame.dao.dialect;

import net.shengjian.frame.util.Page;
import net.shengjian.frame.util.RegexValidateUtils;
import org.springframework.stereotype.Component;

@Component("clickhouseDialect")
public class ClickhouseDialect implements IDialect {

    @Override
    public String getPageSql(String sql, Page page) {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        StringBuilder sb = new StringBuilder(sql);
        /*
        if (StringUtils.isNotBlank(orderby)) {
            sb.append(" ").append(orderby);
        }
         */
        sb.append(" limit ").append(pageSize * (pageNo - 1)).append(",").append(pageSize);
        return sb.toString();
    }

    @Override
    public String getDataBaseType() {
        return "clickhouse";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

    @Override
    public boolean supportTransaction() {
        return false;
    }

    @Override
    public String reUpdateFinderSQL(String updatesql) {
        StringBuilder sql = new StringBuilder("ALTER TABLE ");
        String[] sqls = RegexValidateUtils.getUpdateTableName(updatesql);
        if (sqls != null && sqls.length >= 2) {//更新语句
            sql.append(sqls[1]).append(" UPDATE ");
        } else {//如果不是更新
            sqls = RegexValidateUtils.getDeleteTableName(updatesql);
            if (sqls == null || sqls.length < 2) {//也不是删除语句
                return updatesql;
            }
            sql.append(sqls[1]).append(" DELETE WHERE ");
        }

        //截取字符串
        String str = updatesql.substring(sqls[0].length());
        sql.append(str);
        return sql.toString();
    }


    @Override
    public String updateSQLByTableName(String tableName) {
        return "ALTER TABLE " + tableName + " UPDATE ";
    }

    @Override
    public String deleteSQLByTableName(String tableName) {
        return "ALTER TABLE " + tableName + " DELETE ";
    }


}
