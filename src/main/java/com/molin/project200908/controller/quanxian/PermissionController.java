package com.molin.project200908.controller.quanxian;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.Util.Result;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.quanxian.Permission;
import com.molin.project200908.service.quanxian.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home/qx/qx")
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "true")
@RequiresRoles(value = "ROLE_ADMIN")
public class PermissionController {
    @Autowired
    private IPermissionService service;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Permission> all = service.findAll();
        if (all.size() >= 0) {
            return new Result(true, "成功", all);
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        Permission byId = service.findById(id);
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
        int delete = service.delete(id);
        if (delete > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Permission permission) {
        int add = service.add(permission);
        if (add > 0) {
            return new Result(true, "成功",permission.getId());
        }
        return new Result(false, "失败");

    }

    @PostMapping("/edit")
    public Result update(@RequestBody Permission permission) {
        int update = service.update(permission);
        if (update > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

}
