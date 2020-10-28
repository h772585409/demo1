package com.molin.project200908.dao.quanxian;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.quanxian.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PermissionDao {
    @Select("select * from t_permission")
    public List<Permission> findAll();

    @Select("select * from t_permission where id=#{id}")
    public Permission findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "permissionFindByString")
    public Page<Permission> findByPage(String table, String queryString);

    @Delete("delete from t_permission where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_permission (name,keyword,description) values(" +
            "#{name},#{keyword},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Permission permission);

    @Update("update t_permission set name=#{name},keyword=#{keyword},description=#{description}" +
            " where id=#{id}")
    public int update(Permission permission);

}
