package com.molin.project200908.service;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Group;

import java.util.List;

public interface IGroupService {
    public List<Group> findAll();

    public Group findById(int id);

    public PageResult findPage(FindByPage findByPage);

    public int delete(int id);

    public int add(Group group);

    public int update(Group group);

}
