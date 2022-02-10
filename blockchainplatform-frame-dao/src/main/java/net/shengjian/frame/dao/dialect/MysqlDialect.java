package net.shengjian.frame.dao.dialect;

import net.shengjian.frame.util.Page;
import org.springframework.stereotype.Component;

@Component("mysqlDialect")
public class MysqlDialect implements IDialect {

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
        return "mysql";
    }

    @Override
    public boolean isRowNumber() {
        return false;
    }

}
