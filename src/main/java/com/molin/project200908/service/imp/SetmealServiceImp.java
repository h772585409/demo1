package com.molin.project200908.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.SetmealDao;
import com.molin.project200908.dao.SetmealGroupDao;
import com.molin.project200908.pojo.Setmeal;
import com.molin.project200908.pojo.SetmealGroup;
import com.molin.project200908.service.ISetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SetmealServiceImp implements ISetmealService {
    @Autowired(required = false)
    public SetmealDao setmealDao;
    @Autowired(required = false)
    public SetmealGroupDao setmealGroupDao;

    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);
    }

    @Override
    public PageResult findPage(FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<Setmeal> page = setmealDao.findByPage(findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        deleteAll(id);
        return setmealDao.delete(id);
    }

    @Override
    public int add(Setmeal setmeal) {
        int add = setmealDao.add(setmeal);
        if (add > 0) {
            addAll(setmeal.getId(), setmeal.getGroupIdArr());
        }
        return add;
    }

    @Override
    public int update(Setmeal setmeal) {
        int update = setmealDao.update(setmeal);
        if (update > 0) {
            deleteAll(setmeal.getId());
            addAll(setmeal.getId(), setmeal.getGroupIdArr());
        }
        return update;
    }

    private void deleteAll(int id) {
        setmealGroupDao.delete(id);
    }

    private void addAll(int id, int[] idArr) {
        for (int i = 0; i < idArr.length; i++) {
            int i1 = idArr[i];
            setmealGroupDao.add(new SetmealGroup(id, i1));
        }
    }
}
