package net.shengjian.frame.dao.dialect;

import net.shengjian.frame.util.Page;
import org.springframework.stereotype.Component;

@Component("informixDialect")
public class InformixDialect implements IDialect {

    @Override
    public String getPageSql(String sql, Page page) {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();

        // 去掉无用的空格
        sql = sql.trim();
        // 去掉select
        if (sql.toLowerCase().startsWith("select")) {
            sql = sql.substring(6);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT SKIP ");
        sb.append(pageSize * (pageNo - 1)).append(" FIRST ").append(pageSize).append(" ");
        sb.append(sql);
        /*
        if (StringUtils.isNotBlank(orderby)) {
            sb.append(" ").append(orderby);
        }
         */

        return sb.toString();
    }

    @Override
    public String getDataBaseType() {
        return "informix";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

}
