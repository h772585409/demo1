package com.molin.project200908.controller;

import com.molin.project200908.Util.Result;
import com.molin.project200908.pojo.quanxian.User;
import com.molin.project200908.service.quanxian.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoginController {


    @Autowired
    private IUserService service;

    //http://localhost:8080/insertUser?name=admin1&password=123456
    @RequestMapping("/insertUser")
    public Result insertUser(@RequestBody User user) {
        //密码加密
        String hashAlgorithName = "MD5";
        String password = user.getPassword();
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        Object obj = new SimpleHash(hashAlgorithName, password, credentialsSalt, hashIterations);
        user.setPassword(obj.toString());
        int add = service.add(user);
        return Result.succeed(user);
    }



    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public Result login(@RequestBody User user) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到test.html
            User byName = service.findByName(user.getUsername());
            return Result.succeed(byName);
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            return Result.fail("登录失败:用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            return Result.fail("登录失败:密码错误");

        }
    }
}
