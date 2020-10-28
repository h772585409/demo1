package com.molin.project200908.dao;

import com.molin.project200908.pojo.SetmealGroup;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SetmealGroupDao {

    @Select("select checkgroup_id from t_setmeal_checkgroup where setmeal_id=#{id}")
    public int[] findGroupIdsBySetmealId(int id);

    @Insert("insert into t_setmeal_checkgroup (setmeal_id,checkgroup_id) values(#{setmealId},#{checkgroupId})")
    public int add(SetmealGroup setmealGroup);

    @Delete("delete from t_setmeal_checkgroup where setmeal_id=#{id}")
    public int delete(int id);
}
