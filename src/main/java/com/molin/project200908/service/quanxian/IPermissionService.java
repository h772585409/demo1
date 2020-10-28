package com.molin.project200908.service.quanxian;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Item;
import com.molin.project200908.pojo.quanxian.Permission;

import java.util.List;

public interface IPermissionService {
    public List<Permission> findAll();

    public Permission findById(int id);

    public PageResult findPage(FindByPage findByPage);

    public int delete(int id);

    public int add(Permission permission);

    public int update(Permission permission);
}
