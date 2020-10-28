package com.molin.project200908.dao.quanxian;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.Group;
import com.molin.project200908.pojo.quanxian.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDao {

    @Select("select * from t_role")
    public List<Role> findAll();

    @Select("select * from t_role where id=#{id}")
    @Results(id = "findById", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "prmissionArr", column = "id", many = @Many(select = "com.molin.project200907.dao.quanxian.RolePermissionDao.findPermissionIdsByRoleId"))
    }
    )
    public Role findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "permissionFindByString")
    public Page<Role> findByPage(String table, String queryString);

    @Delete("delete from t_role where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_role (name,keyword,description) values(" +
            "#{name},#{keyword},#{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Role role);

    @Update("update t_role set name=#{name},keyword=#{keyword},description=#{description}" +
            " where id=#{id}")
    public int update(Role role);
}
