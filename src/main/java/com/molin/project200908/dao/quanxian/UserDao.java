package com.molin.project200908.dao.quanxian;

import com.github.pagehelper.Page;
import com.molin.project200908.common.DongTaiSql;
import com.molin.project200908.pojo.quanxian.Role;
import com.molin.project200908.pojo.quanxian.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from t_user where username=#{username}")
    public User findByName(String username);

    @Select("select * from t_user")
    public List<User> findAll();

    @Select("select * from t_user where id=#{id}")
    @Results(id = "findById", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "roleArr", column = "id", many = @Many(select = "com.molin.project200907.dao.quanxian.UserRoleDao.findRoleIdsByuserId"))
    }
    )
    public User findById(int id);

    @SelectProvider(type = DongTaiSql.class, method = "userFindByString")
    public Page<User> findByPage(String table, String queryString);

    @Delete("delete from t_user where id=#{id}")
    public int delete(int id);

    @Insert("insert into t_user (birthday,gender,username,password,remark,station,telephone) values(" +
            "#{birthday},#{gender},#{username},#{password},#{remark},#{station},#{telephone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(User user);

    @Update("update t_user set birthday=#{birthday},gender=#{gender},username=#{username},password=#{password}" +
            ",remark=#{remark},station=#{station},telephone=#{telephone} where id=#{id}")
    public int update(User user);
}
