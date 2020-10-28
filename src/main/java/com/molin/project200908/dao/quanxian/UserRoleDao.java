package com.molin.project200908.dao.quanxian;

import com.molin.project200908.pojo.quanxian.Rolepermission;
import com.molin.project200908.pojo.quanxian.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface UserRoleDao {
    @Select("select role_id from t_user_role where user_id=#{id}")
    public int[] findRoleIdsByuserId(int id);

    @Insert("insert into t_user_role (user_id,role_id) values(#{userId},#{roleId})")
    public int add(UserRole userRole);

    @Delete("delete from t_user_role where user_id=#{id}")
    public int delete(int id);

    @Select("SELECT COUNT(user_id) from t_user_role WHERE role_id = #{id}")
    public int findUserIdCountByRoleId(int id);

    @Select("SELECT keyword from t_permission c INNER JOIN\n" +
            "(SELECT permission_id from t_role_permission a INNER JOIN\n" +
            "(SELECT role_id from t_user_role where user_id=#{id}) b\n" +
            "ON a.role_id = b.role_id ) d\n" +
            "ON c.id = d.permission_id")
    public Set<String> findPermissionByUserId(int id);

    @Select("SELECT keyword from t_role a INNER JOIN\n" +
            "(SELECT role_id from t_user_role where user_id=#{id}) b\n" +
            "ON a.id = b.role_id")
    public Set<String> findrolessByUserId(int id);
}
