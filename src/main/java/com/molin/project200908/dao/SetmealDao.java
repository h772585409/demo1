package com.molin.project200908.dao;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.Setmeal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetmealDao {
    @Select("select * from t_setmeal")
    public List<Setmeal> findAll();

    @Select("select * from t_setmeal where id=#{id}")
    @Results(id = "findById", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "groupIdArr", column = "id", many = @Many(select = "com.molin.project200907.dao.SetmealGroupDao.findGroupIdsBySetmealId"))
    }
    )
    public Setmeal findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "setmealFindByString")
    public Page<Setmeal> findByPage(String queryString);

    @Delete("delete from t_setmeal where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_setmeal (code,name,helpCode,sex,age,price,remark,attention,img) values(" +
            "#{code},#{name},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Setmeal setmeal);

    @Update("update t_setmeal set code=#{code},name=#{name},helpCode=#{helpCode},sex=#{sex},age=#{age},price=#{price},remark=#{remark}," +
            "attention=#{attention},img=#{img} where id=#{id}")
    public int update(Setmeal setmeal);


}
