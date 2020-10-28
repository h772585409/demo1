package com.molin.project200908.pojo.quanxian;

import java.util.Arrays;

public class Role {
    private int id;
    private String name;
    private String keyword;
    private String description;
    private int[] prmissionArr;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
                ", prmissionArr=" + Arrays.toString(prmissionArr) +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getPrmissionArr() {
        return prmissionArr;
    }

    public void setPrmissionArr(int[] prmissionArr) {
        this.prmissionArr = prmissionArr;
    }

    public Role() {
    }

    public Role(int id, String name, String keyword, String description, int[] prmissionArr) {
        this.id = id;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.prmissionArr = prmissionArr;
    }
}
