package com.molin.project200908.service.quanxian.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.quanxian.RoleDao;
import com.molin.project200908.dao.quanxian.RolePermissionDao;
import com.molin.project200908.dao.quanxian.UserRoleDao;
import com.molin.project200908.pojo.Group;
import com.molin.project200908.pojo.GroupItem;
import com.molin.project200908.pojo.quanxian.Role;
import com.molin.project200908.pojo.quanxian.Rolepermission;
import com.molin.project200908.service.quanxian.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements IRoleService {
    @Autowired(required = false)
    private RoleDao roleDao;
    @Autowired(required = false)
    private RolePermissionDao rolePermissionDao;
    @Autowired(required = false)
    private UserRoleDao userRoleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    @Override
    public PageResult findPage(String table, FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<Role> page = roleDao.findByPage(table, findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        int userIdCountByRoleId = userRoleDao.findUserIdCountByRoleId(id);
        if(userIdCountByRoleId>0){
            return -1;
        }else {
            rolePermissionDao.delete(id);
            return roleDao.delete(id);
        }
    }

    @Override
    public int add(Role role) {
        int add = roleDao.add(role);
        if (add > 0) {
            addAll(role.getId(), role.getPrmissionArr());
        }
        return add;
    }

    @Override
    public int update(Role role) {
        rolePermissionDao.delete(role.getId());
        addAll(role.getId(), role.getPrmissionArr());
        return roleDao.update(role);
    }

    private void addAll(int id, int[] idArr) {
        for (int i = 0; i < idArr.length; i++) {
            int i1 = idArr[i];
            rolePermissionDao.add(new Rolepermission(id, i1));
        }
    }
}
