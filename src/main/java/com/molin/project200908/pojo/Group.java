package com.molin.project200908.pojo;

import java.util.List;

public class Group {
    private int id;
    private String code;
    private String name;
    private String helpCode;
    private char sex;
    private String remark;
    private String attention;
    private int[] itemList;

    public Group(int id, String code, String name, String helpCode, char sex, String remark, String attention, int[] itemList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.helpCode = helpCode;
        this.sex = sex;
        this.remark = remark;
        this.attention = attention;
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", helpCode='" + helpCode + '\'' +
                ", sex=" + sex +
                ", remark='" + remark + '\'' +
                ", attention='" + attention + '\'' +
                ", itemList=" + itemList +
                '}';
    }

    public int[] getItemList() {
        return itemList;
    }

    public void setItemList(int[] itemList) {
        this.itemList = itemList;
    }

    public Group() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHelpCode() {
        return helpCode;
    }

    public void setHelpCode(String helpCode) {
        this.helpCode = helpCode;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

}
