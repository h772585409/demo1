package com.molin.project200908.service;

import com.molin.project200908.pojo.ListGroup;

import java.util.List;

public interface IListService {
    public List<ListGroup> findOrderGroupByMonth(String year);

    public ListGroup findOrderByWeek();
}
