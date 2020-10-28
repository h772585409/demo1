package com.molin.project200908.pojo.quanxian;

public class Rolepermission {
    private int roleId;
    private int permissionId;

    @Override
    public String toString() {
        return "Rolepermission{" +
                "roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public Rolepermission() {
    }

    public Rolepermission(int roleId, int permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
