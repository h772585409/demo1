package com.molin.project200908.service.quanxian;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Group;
import com.molin.project200908.pojo.quanxian.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll();

    public Role findById(int id);

    public PageResult findPage(String table, FindByPage findByPage);

    public int delete(int id);

    public int add(Role role);

    public int update(Role role);

}
