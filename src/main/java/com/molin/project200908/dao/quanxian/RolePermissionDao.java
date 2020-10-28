package com.molin.project200908.dao.quanxian;

import com.molin.project200908.pojo.GroupItem;
import com.molin.project200908.pojo.quanxian.Rolepermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RolePermissionDao {
    @Select("select permission_id from t_role_permission where role_id=#{id}")
    public int[] findPermissionIdsByRoleId(int id);

    @Insert("insert into t_role_permission (role_id,permission_id) values(#{roleId},#{permissionId})")
    public int add(Rolepermission rolepermission);

    @Delete("delete from t_role_permission where role_id=#{id}")
    public int delete(int id);

    @Select("SELECT COUNT(role_id) from t_role_permission WHERE permission_id = #{id}")
    public int findRoleIdCountByPermissionId(int id);
}
