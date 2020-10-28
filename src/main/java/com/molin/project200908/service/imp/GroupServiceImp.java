package com.molin.project200908.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.GroupDao;
import com.molin.project200908.dao.GroupItemDao;
import com.molin.project200908.pojo.Group;
import com.molin.project200908.pojo.GroupItem;
import com.molin.project200908.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupServiceImp implements IGroupService {
    @Autowired(required = false)
    private GroupDao groupDao;
    @Autowired(required = false)
    private GroupItemDao groupItemDao;

    @Override
    public List<Group> findAll() {
        return groupDao.findAll();
    }

    @Override
    public Group findById(int id) {
        return groupDao.findById(id);
    }

    @Override
    public PageResult findPage(FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<Group> page = groupDao.findByPage(findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        deleteAll(id);
        return groupDao.delete(id);
    }

    @Override
    public int add(Group group) {
        int add = groupDao.add(group);
        if (add > 0) {
            addAll(group.getId(), group.getItemList());
        }
        return add;
    }

    @Override
    public int update(Group group) {
        int update = groupDao.update(group);
        if (update > 0) {
            deleteAll(group.getId());
            addAll(group.getId(), group.getItemList());
        }
        return update;
    }

    private void deleteAll(int id) {
        groupItemDao.delete(id);
    }

    private void addAll(int id, int[] idArr) {
        for (int i = 0; i < idArr.length; i++) {
            int i1 = idArr[i];
            groupItemDao.add(new GroupItem(id, i1));
        }
    }
}
