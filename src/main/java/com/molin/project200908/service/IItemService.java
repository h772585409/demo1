package com.molin.project200908.service;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Item;

import java.util.List;

public interface IItemService {
    public List<Item> findAll();

    public Item findById(int id);

    public PageResult findPage(FindByPage findByPage);

    public int delete(int id);

    public int add(Item item);

    public int update(Item item);
}
