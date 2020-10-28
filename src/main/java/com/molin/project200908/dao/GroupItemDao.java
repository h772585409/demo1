package com.molin.project200908.dao;

import com.molin.project200908.pojo.GroupItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupItemDao {
    @Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id=#{id}")
    public int[] findCheckItemIdsByCheckGroupId(int id);

    @Insert("insert into t_checkgroup_checkitem (checkgroup_id,checkitem_id) values(#{checkgroupId},#{checkitemId})")
    public int add(GroupItem groupItem);

    @Delete("delete from t_checkgroup_checkitem where checkgroup_id=#{id}")
    public int delete(int id);

}
