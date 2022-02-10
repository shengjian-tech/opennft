package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: 计算合集上架价格
 * @author: YSK
 * @date: 2021/12/24 15:40
 * @version: 1.0
 */
public class CollectionInDetailsVO extends ParmaDateVO implements Serializable {
    /**
     * 合集id
     */
    private String collectionId;

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }
}
