package com.molin.project200908.dao;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.Item;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemDao {
    @Select("select * from t_checkitem")
    public List<Item> findAll();

    @Select("select * from t_checkitem where id=#{id}")
    public Item findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "itemFindByString")
    public Page<Item> findByPage(String queryString);

    @Delete("delete from t_checkitem where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_checkitem (code,name,sex,age,price,type,attention,remark) values(" +
            "#{code},#{name},#{sex},#{age},#{price},#{type},#{attention},#{remark})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Item item);

    @Update("update t_checkitem set code=#{code},name=#{name},sex=#{sex},age=#{age},price=#{price}," +
            "type=#{type},attention=#{attention},remark=#{remark} where id=#{id}")
    public int update(Item item);


}
