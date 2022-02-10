package net.shengjian.system.api.dto;

/**
 * 菜单信息
 */
public class MetaDto {

    /**
     * 标题
     */
    private String title;
    /**
     * 图标
     */
    private String icon;

    /**
     * 是否缓存
     */
    private boolean noCache;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public void setNoCache(boolean noCache) {
        this.noCache = noCache;
    }


}
