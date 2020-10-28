package com.molin.project200908.service.quanxian;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.quanxian.Role;
import com.molin.project200908.pojo.quanxian.User;

import java.util.List;
import java.util.Set;

public interface IUserService {
    public User findByName(String username);

    public List<User> findAll();

    public User findById(int id);

    public PageResult findPage(FindByPage findByPage);

    public int delete(int id);

    public int add(User user);

    public int update(User user);

    public Set<String> findPermissionsByUserId(int id);

    public Set<String> findrolessByUserId(int id);
}
