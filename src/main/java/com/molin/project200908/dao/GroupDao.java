package com.molin.project200908.dao;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GroupDao {
    @Select("select * from t_checkgroup")
    public List<Group> findAll();

    @Select("select * from t_checkgroup where id=#{id}")
    @Results(id = "findById", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "itemList", column = "id", many = @Many(select = "com.molin.project200907.dao.GroupItemDao.findCheckItemIdsByCheckGroupId"))
    }
    )
    public Group findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "groupFindByString")
    public Page<Group> findByPage(String queryString);

    @Delete("delete from t_checkgroup where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_checkgroup (code,name,helpCode,sex,remark,attention) values(" +
            "#{code},#{name},#{helpCode},#{sex},#{remark},#{attention})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Group group);

    @Update("update t_checkgroup set code=#{code},name=#{name},helpCode=#{helpCode},sex=#{sex},remark=#{remark}," +
            "attention=#{attention} where id=#{id}")
    public int update(Group group);

}
