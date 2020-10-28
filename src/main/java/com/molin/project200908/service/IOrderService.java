package com.molin.project200908.service;

import com.molin.project200908.pojo.Ordersetting;
import com.molin.project200908.pojo.Riqi;

import java.util.List;

public interface IOrderService {
    public Ordersetting[] findByMonth(Riqi riqi);

    public Ordersetting[] findAll();

    public int update(Ordersetting ordersetting);

    public boolean add(List<Ordersetting> list);

}
