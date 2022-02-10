package net.shengjian.system.api.vo;

import net.shengjian.system.entity.Menu;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * @descriptions: 菜单
 * @author: YSK
 * @date: 2021/5/7 10:02
 * @version: 1.0
 */
public class MenuVO implements Serializable {
    /**
     * 需要唯一    是
     * 主键，新增不用填写
     *
     * @required
     */
    private String key;
    /**
     * 用于关联父级   否
     */
    private String parentKey;
    /**
     * 菜单对应的路由地址   是
     *
     * @required
     */
    private String path;
    /**
     * 菜单接口
     *
     * @required
     */
    private String pageUrl;
    /**
     * 菜单标题  是
     *
     * @required
     */
    private String text;
    /**
     * 菜单图标配置    否
     */
    private String icon;
    /**
     * 菜单对应会打开url对应的iframe页面，如果配置了url，path将无效   否
     */
    private String url;
    /**
     * 配合url使用，菜单将为a标签 <a href={url} target={target}>{text}</a>   否
     */
    private String target;
    /**
     * 菜单排序，数值越大越靠前显示   否
     */
    private Integer order;
    /**
     * 如果菜单数据中携带功能权限配置，type==='1' 为菜单，type==='0'为功能  否
     */
    private Integer type;
    /**
     * 权限显示key,功能，用于按钮显示判断,禁止修改
     *
     * @required
     */
    private String code;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否有效(0否,1是)
     */
    private Integer active;
    /**
     * 从根菜单的记录
     */
    private String[] comcode;
    /**
     * 子菜单
     */
    private List<MenuVO> children;

    public MenuVO() {
    }

    public MenuVO(Menu menu) {
        this.key = menu.getId();
        this.parentKey = menu.getPid();
        this.path = menu.getPath();
        this.pageUrl = menu.getPageurl();
        this.text = menu.getTitle();
        this.icon = menu.getIcon();
        this.url = menu.getUrl();
        this.target = menu.getTarget();
        this.order = menu.getSortno();
        this.type = menu.getMenuType();
        this.code = menu.getCode();
        this.createTime = menu.getCreateTime();
        this.active = menu.getActive();
        String comcode = menu.getComcode();
        if (StringUtils.isBlank(comcode)) {
            comcode = ",";
        }
        comcode = comcode.substring(1);
        this.comcode = comcode.split(",");
    }

    public static List<Menu> menvVOListConvertMentList(List<MenuVO> menuVOList) {
        List<Menu> menuList = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuVOList)) {
            return menuList;
        }
        for (MenuVO menuVO : menuVOList) {
            Menu menu = menuVO.menuVoConvertMenu();
            menuList.add(menu);
        }
        return menuList;
    }

    public Menu menuVoConvertMenu() {
        Menu menu = new Menu();
        menu.setId(this.key);
        menu.setPid(this.parentKey == null ? "" : this.parentKey);
        menu.setName(this.text);
        menu.setPath(this.path);
        menu.setTitle(this.text);
        menu.setIcon(this.icon);
        menu.setPageurl(this.pageUrl);
        menu.setUrl(this.url);
        menu.setTarget(this.target);
        menu.setSortno(this.order);
        menu.setMenuType(this.type);
        menu.setCode(this.code);
        menu.setActive(this.active);
        return menu;
    }

    public static List<MenuVO> menuConvertMenuVO(List<Menu> menuList) {
        List<MenuVO> menuVOList = new ArrayList<>();
        if(CollectionUtils.isEmpty(menuList)){
            return menuVOList;
        }
        for (Menu menu : menuList) {
            MenuVO menuVO = new MenuVO(menu);
            menuVOList.add(menuVO);
        }
        return menuVOList;
    }

    public static List<MenuVO> menuTreeConvertMenuVOTree(List<Menu> menuList) {
        List<MenuVO> menuVOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuList)) {
            return menuVOList;
        }
        List<Menu> list = new ArrayList<>();
        list.addAll(menuList);
        if (CollectionUtils.isEmpty(list)) {
            return menuVOList;
        }
        for (Menu menu : list) {
            MenuVO menuVO = new MenuVO(menu);
            List<Menu> children = menu.getChildren();
            if (CollectionUtils.isNotEmpty(children)) {
                List<MenuVO> childrenMenuVO = menuTreeConvertMenuVOTree(children);
                menuVO.setChildren(childrenMenuVO);
            }
            menuVOList.add(menuVO);
        }
        return menuVOList;
    }

    public static List<MenuVO> menuVOListConvertMenuVOTree(List<MenuVO> menuVOList) {
        if (CollectionUtils.isEmpty(menuVOList)) {
            return null;
        }
        Map<String, MenuVO> map = new HashMap<>();
        for (MenuVO menuVO : menuVOList) {
            map.put(menuVO.getKey(), menuVO);
        }
        List<MenuVO> menuVOTree = new ArrayList<>();
        for (MenuVO menuVO : menuVOList) {
            String parentKey = menuVO.getParentKey();
            MenuVO parent = map.get(parentKey);
            if (parent == null) {
                menuVOTree.add(menuVO);
                continue;
            }
            List<MenuVO> children = parent.getChildren();
            if (CollectionUtils.isEmpty(children)) {
                children = new ArrayList<>();
                parent.setChildren(children);
            }
            children.add(menuVO);
        }
        if (CollectionUtils.isEmpty(menuVOTree)) {
            return null;
        }
        return menuVOTree;
    }

    public String[] getComcode() {
        return comcode;
    }

    public void setComcode(String[] comcode) {
        this.comcode = comcode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<MenuVO> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVO> children) {
        this.children = children;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
