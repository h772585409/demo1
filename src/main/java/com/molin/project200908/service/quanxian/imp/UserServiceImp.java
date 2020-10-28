package com.molin.project200908.service.quanxian.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.quanxian.PermissionDao;
import com.molin.project200908.dao.quanxian.RolePermissionDao;
import com.molin.project200908.dao.quanxian.UserDao;
import com.molin.project200908.dao.quanxian.UserRoleDao;
import com.molin.project200908.pojo.quanxian.User;
import com.molin.project200908.pojo.quanxian.UserRole;
import com.molin.project200908.service.quanxian.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements IUserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired(required = false)
    private UserRoleDao userRoleDao;


    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public PageResult findPage(FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<User> page = userDao.findByPage("t_user", findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        userRoleDao.delete(id);
        return userDao.delete(id);
    }

    @Override
    public int add(User user) {
        int add = userDao.add(user);
        if (add > 0 && user.getRoleArr() != null) {
            addAll(user.getId(), user.getRoleArr());
        }
        return add;
    }

    @Override
    public int update(User user) {
        userRoleDao.delete(user.getId());
        addAll(user.getId(), user.getRoleArr());
        return userDao.update(user);
    }

    private void addAll(int id, int[] idArr) {
        for (int i = 0; i < idArr.length; i++) {
            int i1 = idArr[i];
            userRoleDao.add(new UserRole(id, i1));
        }
    }

    @Override
    public Set<String> findPermissionsByUserId(int id) {
        return userRoleDao.findPermissionByUserId(id);
    }

    @Override
    public Set<String> findrolessByUserId(int id) {
        return userRoleDao.findrolessByUserId(id);
    }
}
