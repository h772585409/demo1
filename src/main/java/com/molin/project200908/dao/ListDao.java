package com.molin.project200908.dao;

import com.molin.project200908.pojo.ListGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ListDao {
    @Select("SELECT  MONTH(orderDate) month,SUM(reservations) sumRes,SUM(number) sumNum,SUM(reservations)/SUM(number) rate " +
            "from t_ordersetting WHERE YEAR(orderDate)=#{year} GROUP BY MONTH(orderDate)")
    public List<ListGroup> findOrderGroupByMonth(String year);

    @Select("SELECT SUM(reservations) sumRes,SUM(number) sumNum FROM  t_ordersetting WHERE YEARWEEK( date_format(  orderDate,'%Y%m%d' ) ) = YEARWEEK( now() )")
    public ListGroup findOrderByWeek();
}
