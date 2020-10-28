package com.molin.project200908.pojo;

public class GroupItem {
    private int checkgroupId;
    private int checkitemId;

    public GroupItem() {
    }

    public GroupItem(int checkgroupId, int checkitemId) {
        this.checkgroupId = checkgroupId;
        this.checkitemId = checkitemId;
    }

    @Override
    public String toString() {
        return "GroupItem{" +
                "checkgroupId=" + checkgroupId +
                ", checkitemId=" + checkitemId +
                '}';
    }

    public int getCheckgroupId() {
        return checkgroupId;
    }

    public void setCheckgroupId(int checkgroupId) {
        this.checkgroupId = checkgroupId;
    }

    public int getCheckitemId() {
        return checkitemId;
    }

    public void setCheckitemId(int checkitemId) {
        this.checkitemId = checkitemId;
    }
}
