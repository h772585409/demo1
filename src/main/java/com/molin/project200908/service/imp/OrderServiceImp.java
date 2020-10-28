package com.molin.project200908.service.imp;

import com.molin.project200908.dao.OrderDao;
import com.molin.project200908.pojo.Ordersetting;
import com.molin.project200908.pojo.Riqi;
import com.molin.project200908.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired(required = false)
    private OrderDao orderDao;

    @Override
    public Ordersetting[] findByMonth(Riqi riqi) {
        return orderDao.findByMonth(riqi);
    }

    @Override
    public Ordersetting[] findAll() {
        return orderDao.findAll();
    }

    @Override
    public int update(Ordersetting ordersetting) {
        return orderDao.update(ordersetting);
    }

    @Override
    public boolean add(List<Ordersetting> list) {
        try {
            for (int i = 0; i < list.size(); i++) {
                Ordersetting ordersetting = list.get(i);
                Date orderDate = ordersetting.getOrderDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = dateFormat.format(orderDate);
                String[] split = format.split("-");
                int byDay = orderDao.deleteByday(new Riqi(split[0], split[1], split[2]));
                orderDao.add(ordersetting);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
