package net.shengjian.frame.dao.dialect;

import net.shengjian.frame.util.Page;
import org.springframework.stereotype.Component;

@Component("mssqlDialect")
public class MssqlDialect implements IDialect {

    @Override
    public String getPageSql(String sql, Page page) {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageNo();
        // 去掉无用的空格
        sql = sql.trim();

        /*
        // 去掉select
        if (sql.toLowerCase().startsWith("select")) {
            sql = sql.substring(6);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT TOP ");
        sb.append(pageSize);
        sb.append(" * from (SELECT ROW_NUMBER() OVER (");
        sb.append(orderby);
        sb.append(")  frame_page_sql_row_number,");
        sb.append(sql);
        sb.append("  ) frame_sql_temp_table WHERE frame_page_sql_row_number > ");
        sb.append(pageSize * (pageNo - 1));
        sb.append(" order by frame_page_sql_row_number ");
        return sb.toString();
         */
        StringBuilder sb = new StringBuilder();
        sb.append(sql).append(" OFFSET ").append(pageSize * (pageNo - 1)).append(" ROWS FETCH NEXT ").append(pageSize).append(" ROWS ONLY ");
        return sb.toString();

    }

    @Override
    public String getDataBaseType() {
        return "mssql";
    }

    @Override
    public boolean isRowNumber() {
        return true;
    }

}
