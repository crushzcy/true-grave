package org.zhou.forever.truegrave.domain;

public class Role extends BaseDomain {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.role_id
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.name
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role.descr
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    private String descr;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.role_id
     *
     * @return the value of t_role.role_id
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.role_id
     *
     * @param roleId the value for t_role.role_id
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.name
     *
     * @return the value of t_role.name
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.name
     *
     * @param name the value for t_role.name
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role.descr
     *
     * @return the value of t_role.descr
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public String getDescr() {
        return descr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role.descr
     *
     * @param descr the value for t_role.descr
     *
     * @mbggenerated Mon Oct 21 01:24:44 CST 2019
     */
    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }
}