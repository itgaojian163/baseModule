package com.tengshi.basemodule.utils;

/**
 * 用户登录后SP工具类
 */
public class UserInfoSPUtils {


    public static String getToken() {
        return SPUtils.getInstance().getString("token", "");
    }

    public static void setToken(String token) {
        SPUtils.getInstance().put("token", token);
    }

    public static void setUserNameCache(String userName) {
        SPUtils.getInstance().put("userName", userName);
    }

    public static String getUserNameCache() {
        return SPUtils.getInstance().getString("userName");
    }

    /**
     * 监区ID
     *
     * @return
     */
    public static String getFieldPrisonId() {
        return SPUtils.getInstance().getString("field_prison_id", "");
    }

    public static void setFieldPrisonId(String fieldPrisonId) {
        SPUtils.getInstance().put("field_perison_id", fieldPrisonId);
    }

    /**
     * 菜单权限
     */
    public static void setMenuConfig(String menuConfig) {
        SPUtils.getInstance().put("menuConfig", menuConfig);
    }

    /**
     * 菜单权限ID
     *
     * @param menuId
     */
    public static void setMenuConfigId(String menuId) {
        SPUtils.getInstance().put("menuConfigId", menuId);
    }

    /**
     * 菜单权限ID
     */
    public static String getMenuConfigId() {
        return SPUtils.getInstance().getString("menuConfigId");
    }

    /**
     * 菜单权限
     */
    public static String getMenuConfig() {
        return SPUtils.getInstance().getString("menuConfig");
    }

    /**
     * 工具权限
     */
    public static void setToolsLevel(String level) {
        SPUtils.getInstance().put("toolLevel", level);
    }

    public static String getToolsLevel() {
        return SPUtils.getInstance().getString("toolLevel");
    }

    /**
     * 铺/岗位调整权限
     *
     * @param levl
     */
    public static void setPostAdjustLevel(String levl) {
        SPUtils.getInstance().put("adjustLevel", levl);
    }

    public static String getPostAdjustLevel() {
        return SPUtils.getInstance().getString("adjustLevel");
    }

    /**
     * 点名权限
     *
     * @param callLevel
     */
    public static void setCallLevel(String callLevel) {
        SPUtils.getInstance().put("callLevel", callLevel);
    }

    public static String getCallLevel() {
        return SPUtils.getInstance().getString("callLevel");
    }

    /**
     * 人员信息调整
     *
     * @param change
     */
    public static void setCriminalChange(String change) {
        SPUtils.getInstance().put("criminalChange", change);
    }

    public static String getCriminalChange() {
        return SPUtils.getInstance().getString("criminalChange");
    }

    /**
     * 监区名称
     *
     * @return
     */
    public static String getFieldPrisonName() {
        return SPUtils.getInstance().getString("field_prison_name", "");
    }

    public static void setFieldPrisonName(String fieldPrisonId) {
        SPUtils.getInstance().put("field_prison_name", fieldPrisonId);
    }

    /**
     * 用户ID
     *
     * @return
     */
    public static String getID() {
        return SPUtils.getInstance().getString("id");
    }

    public static void setID(String id) {
        SPUtils.getInstance().put("id", id);
    }

    /**
     * 用户ID
     *
     * @return
     */
    public static String getUserId() {
        return SPUtils.getInstance().getString("userId");
    }

    public static void seterId(String userId) {
        SPUtils.getInstance().put("userId", userId);
    }

    /**
     * 用户名称
     *
     * @return
     */
    public static String getUsername() {
        return SPUtils.getInstance().getString("userName");
    }

    public static void setUsername(String userName) {
        SPUtils.getInstance().put("userName", userName);
    }

    /**
     * 部门
     *
     * @return
     */
    public static String getDeptname() {
        return SPUtils.getInstance().getString("deptname");
    }

    public static void setDeptname(String token) {
        SPUtils.getInstance().put("deptname", token);
    }

    /**
     * 岗位
     *
     * @return
     */
    public static String getPosname() {
        return SPUtils.getInstance().getString("posname");
    }

    public static void setPosname(String token) {
        SPUtils.getInstance().put("posname", token);
    }

    /**
     * 岗位
     *
     * @return
     */
    public static String getFieldId() {
        return SPUtils.getInstance().getString("field_id");
    }

    public static void setFieldId(String token) {
        SPUtils.getInstance().put("field_id", token);
    }

    public static void setFieldList(String fieldList) {
        SPUtils.getInstance().put("fieldList", fieldList);
    }

    public static String getFieldList() {
        return SPUtils.getInstance().getString("fieldList");
    }

    /**
     * 警号
     *
     * @return
     */
    public static String getNumber() {
        return SPUtils.getInstance().getString("number");
    }

    public static void setNumber(String token) {
        SPUtils.getInstance().put("number", token);
    }

    /**
     * 权限
     *
     * @param level
     */
    public static void setRoleLevel(String level) {
        SPUtils.getInstance().put("roleLevel", level);
    }

    public static String getRoleLevel() {
        return SPUtils.getInstance().getString("roleLevel");
    }

    /**
     * 编号
     *
     * @param level
     */
    public static void setBianMa(String level) {
        SPUtils.getInstance().put("bianma", level);
    }

    public static String getBianMa() {
        return SPUtils.getInstance().getString("bianma");
    }

    /**
     * 编号
     *
     * @param level
     */
    public static void setName(String level) {
        SPUtils.getInstance().put("name", level);
    }

    public static String getName() {
        return SPUtils.getInstance().getString("name");
    }

    public static void setAreaList(String areaList) {
        SPUtils.getInstance().put("areaList", areaList);
    }

    public static String getAreaList() {
        return SPUtils.getInstance().getString("areaList");
    }

    /**
     * 密码
     *
     * @param level
     */
    public static void setPwd(String level) {
        SPUtils.getInstance().put("password", level);
    }

    public static String getPwd() {
        return SPUtils.getInstance().getString("password");
    }

    public static void setLoginName(String name) {
        SPUtils.getInstance().put("loginUserName", name);
    }

    public static String getLoginName() {
        return SPUtils.getInstance().getString("loginUserName");
    }

    public static void setAppList(String appList) {
        SPUtils.getInstance().put("appList", appList);
    }

    public static String getAppList() {
        return SPUtils.getInstance().getString("appList");
    }
}
