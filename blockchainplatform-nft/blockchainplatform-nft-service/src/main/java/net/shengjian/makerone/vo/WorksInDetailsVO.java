package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: nft上架计算详情
 * @author: YSK
 * @date: 2021/12/24 14:45
 * @version: 1.0
 */
public class WorksInDetailsVO extends ParmaDateVO implements Serializable {
    /**
     * 作品id
     */
    private String worksId;

    public String getWorksId() {
        return worksId;
    }

    public void setWorksId(String worksId) {
        this.worksId = worksId;
    }
}
