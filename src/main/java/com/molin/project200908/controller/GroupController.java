package com.molin.project200908.controller;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.Util.Result;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Group;
import com.molin.project200908.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GroupController {
    @Autowired
    private IGroupService iGroupService;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Group> all = iGroupService.findAll();
        if (all.size() >= 0) {
            return new Result(true, "成功", all);
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        Group byId = iGroupService.findById(id);
        System.out.println(byId);
        if (byId != null) {
            return new Result(true, "成功", byId);
        }
        return new Result(false, "失败");
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody FindByPage findByPage) {
        PageResult pageResult = iGroupService.findPage(findByPage);
        return pageResult;
    }

    @GetMapping("/delete")
    public Result delete(int id) {
        System.out.println(id);
        int delete = iGroupService.delete(id);
        if (delete > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Group group) {
        int add = iGroupService.add(group);
        if (add > 0) {
            return new Result(true, "成功",group.getId());
        }
        return new Result(false, "失败");

    }

    @PostMapping("/edit")
    public Result update(@RequestBody Group group) {
        System.out.println(group);
        int update = iGroupService.update(group);
        if (update > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

}
