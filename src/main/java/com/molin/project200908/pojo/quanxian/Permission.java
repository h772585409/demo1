package com.molin.project200908.pojo.quanxian;

public class Permission {
    private int id;
    private String name;
    private String keyword;
    private String description;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
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

    public Permission() {
    }

    public Permission(int id, String name, String keyword, String description) {
        this.id = id;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
    }
}
