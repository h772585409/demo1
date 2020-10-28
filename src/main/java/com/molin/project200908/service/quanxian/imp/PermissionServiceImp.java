package com.molin.project200908.service.quanxian.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.quanxian.PermissionDao;
import com.molin.project200908.dao.quanxian.RolePermissionDao;
import com.molin.project200908.pojo.quanxian.Permission;
import com.molin.project200908.service.quanxian.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImp implements IPermissionService {
    @Autowired(required = false)
    private PermissionDao permissionDao;
    @Autowired(required = false)
    private RolePermissionDao rolePermissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public Permission findById(int id) {
        return permissionDao.findById(id);
    }

    @Override
    public PageResult findPage(FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<Permission> page = permissionDao.findByPage("t_permission",findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        int permissionIdCountByRoleId = rolePermissionDao.findRoleIdCountByPermissionId(id);
        if(permissionIdCountByRoleId>0){
            return -1;
        }else {
            return permissionDao.delete(id);
        }
    }

    @Override
    public int add(Permission permission) {
        return permissionDao.add(permission);
    }

    @Override
    public int update(Permission permission) {
        return permissionDao.update(permission);
    }
}
