package net.shengjian.makerone.dto;

/**
 * @descriptions: 用户的合集信息列表
 * @author: YSK
 * @date: 2021/12/31 15:40
 * @version: 1.0
 */
public class UserCollectionDTO {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 封面
     */
    private String coverPath;
    /**
     * logo路径
     */
    private String logoPath;
    /**
     * 是否平台认证
     */
    private Integer isCert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public Integer getIsCert() {
        return isCert;
    }

    public void setIsCert(Integer isCert) {
        this.isCert = isCert;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
