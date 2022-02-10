package net.shengjian.system.api.dto;

import java.util.List;

/**
 * 菜单dto
 */
public class MenuDto {

    /**
     * 菜单名
     */
    private String name;
    /**
     * 路径
     */
    private String path;
    /**
     * 是否隐藏
     */
    private boolean hidden;
    /**
     * 始终显示
     */
    private boolean alwaysShow;
    /**
     * 重定向
     */
    private String redirect;
    /**
     * 组件
     */
    private String component;
    /**
     * 菜单信息
     */
    private MetaDto meta;

    /**
     * 菜单那子集
     */
    private List<MenuDto> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public MetaDto getMeta() {
        return meta;
    }

    public void setMeta(MetaDto meta) {
        this.meta = meta;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }


}
