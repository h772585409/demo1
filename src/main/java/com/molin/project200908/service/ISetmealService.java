package com.molin.project200908.service;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Setmeal;

import java.util.List;

public interface ISetmealService {

    public List<Setmeal> findAll();

    public Setmeal findById(int id);

    public PageResult findPage(FindByPage findByPage);

    public int delete(int id);

    public int add(Setmeal setmeal);

    public int update(Setmeal setmeal);
}
