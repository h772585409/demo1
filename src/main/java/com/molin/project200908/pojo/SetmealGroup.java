package com.molin.project200908.pojo;

public class SetmealGroup {
    private int setmealId;
    private int checkgroupId;

    public int getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(int setmealId) {
        this.setmealId = setmealId;
    }

    public int getCheckgroupId() {
        return checkgroupId;
    }

    public void setCheckgroupId(int checkgroupId) {
        this.checkgroupId = checkgroupId;
    }

    @Override
    public String toString() {
        return "SetmealGroup{" +
                "setmealId=" + setmealId +
                ", checkgroupId=" + checkgroupId +
                '}';
    }

    public SetmealGroup() {
    }

    public SetmealGroup(int setmealId, int checkgroupId) {
        this.setmealId = setmealId;
        this.checkgroupId = checkgroupId;
    }
}
