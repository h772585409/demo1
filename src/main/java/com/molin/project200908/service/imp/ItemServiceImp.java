package com.molin.project200908.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.dao.ItemDao;
import com.molin.project200908.pojo.Item;
import com.molin.project200908.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImp implements IItemService {
    @Autowired(required = false)
    private ItemDao itemDao;

    @Override
    public List<Item> findAll() {
        return itemDao.findAll();
    }

    @Override
    public Item findById(int id) {
        return itemDao.findById(id);
    }

    @Override
    public PageResult findPage(FindByPage findByPage) {
        PageHelper.startPage(findByPage.getCurrentPage(), findByPage.getPageSize());
        Page<Item> page = itemDao.findByPage(findByPage.getQueryString());
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public int delete(int id) {
        return itemDao.delete(id);
    }

    @Override
    public int add(Item item) {
        return itemDao.add(item);
    }

    @Override
    public int update(Item item) {
        return itemDao.update(item);
    }
}
