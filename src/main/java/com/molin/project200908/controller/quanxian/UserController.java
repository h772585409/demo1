package com.molin.project200908.controller.quanxian;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.Util.Result;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.quanxian.User;
import com.molin.project200908.service.quanxian.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/qx/zh")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RequiresRoles(value = "ROLE_ADMIN")
public class UserController {
    @Autowired
    private IUserService service;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<User> all = service.findAll();
        if (all.size() >= 0) {
            return new Result(true, "成功", all);
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        User byId = service.findById(id);
        System.out.println(byId);
        if (byId != null) {
            return new Result(true, "成功", byId);
        }
        return new Result(false, "失败");
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody FindByPage findByPage) {
        PageResult pageResult = service.findPage(findByPage);
        return pageResult;
    }

    @GetMapping("/delete")
    public Result delete(int id) {
        System.out.println(id);
        int delete = service.delete(id);
        if (delete > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        int add = service.add(user);
        if (add > 0) {
            return new Result(true, "成功",user.getId());
        }
        return new Result(false, "失败");

    }

    @PostMapping("/edit")
    public Result update(@RequestBody User user) {
        System.out.println(user);
        int update = service.update(user);
        if (update > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

}
