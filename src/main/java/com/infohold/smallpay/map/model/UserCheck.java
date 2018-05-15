package com.infohold.smallpay.map.model;

import java.util.Date;

public class UserCheck {
    /**
     * null
     */
    private Integer id;

    /**
     * 类型
     */
    private String type;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 开始时间
     */
    private Date startdate;

    /**
     * 结束时间
     */
    private Date enddate;

    /**
     * 责任心
     */
    private Integer responsible;

    /**
     * 纪律性
     */
    private Integer discipline;

    /**
     * 执行力
     */
    private Integer execution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getResponsible() {
        return responsible;
    }

    public void setResponsible(Integer responsible) {
        this.responsible = responsible;
    }

    public Integer getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Integer discipline) {
        this.discipline = discipline;
    }

    public Integer getExecution() {
        return execution;
    }

    public void setExecution(Integer execution) {
        this.execution = execution;
    }
}