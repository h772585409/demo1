package com.molin.project200908.service.imp;

import com.molin.project200908.dao.ListDao;
import com.molin.project200908.pojo.ListGroup;
import com.molin.project200908.service.IListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceImp implements IListService {
    @Autowired(required = false)
    private ListDao listDao;

    @Override
    public List<ListGroup> findOrderGroupByMonth(String year) {

        return listDao.findOrderGroupByMonth(year);
    }

    @Override
    public ListGroup findOrderByWeek() {
        return listDao.findOrderByWeek();
    }
}
