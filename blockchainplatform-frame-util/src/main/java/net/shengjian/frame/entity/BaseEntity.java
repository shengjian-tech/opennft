package net.shengjian.frame.entity;

import javax.persistence.Transient;

/**
 * Entity基类,所有的entity必须继承此类
 *
 * @author springrain<9 iuorg @ gmail.com>
 * @version 2013-03-19 11:08:15
 * @copyright {@link jiagou.com}
 * @see BaseEntity
 */
public class BaseEntity implements IBaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表的别名,用于处理复杂的where 条件拼接
     */
    private String frameTableAlias = null;

    @Transient
    public static long isSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(long l) {
        // return serialVersionUID;
    }

    @Transient
    public String getFrameTableAlias() {
        return frameTableAlias;
    }

    public void setFrameTableAlias(String frameTableAlias) {
        this.frameTableAlias = frameTableAlias;
    }

    @Transient
    public String isFrameTableAlias() {
        return frameTableAlias;
    }

}
