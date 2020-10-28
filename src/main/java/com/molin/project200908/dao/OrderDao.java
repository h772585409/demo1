package com.molin.project200908.dao;

import com.molin.project200908.pojo.Ordersetting;
import com.molin.project200908.pojo.Riqi;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Select("SELECT * FROM t_ordersetting WHERE month(orderDate)=#{month} AND year(orderDate)=#{year}")
    public Ordersetting[] findByMonth(Riqi riqi);

    @Select("SELECT count(*) FROM t_ordersetting WHERE month(orderDate)=#{month} AND year(orderDate)=#{year} and day(orderDate)=#{day}")
    public int findByDay(Riqi riqi);

    @Delete("delete FROM t_ordersetting WHERE month(orderDate)=#{month} AND year(orderDate)=#{year} and day(orderDate)=#{day}")
    public int deleteByday(Riqi riqi);

    @Select("SELECT * FROM t_ordersetting")
    public Ordersetting[] findAll();

    @Update("update t_ordersetting set number=#{number} where id=#{id}")
    public int update(Ordersetting ordersetting);

    @Insert("insert into t_ordersetting (orderDate,number) values(#{orderDate},#{number})")
    public int add(Ordersetting ordersetting);

}
