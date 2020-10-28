package com.molin.project200908.controller;

import com.molin.project200908.Util.PageResult;
import com.molin.project200908.Util.Result;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Item;
import com.molin.project200908.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
@CrossOrigin(origins = "*", maxAge = 3600,allowCredentials = "true")
public class ItmeController {
    @Autowired
    private IItemService iItemService;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Item> all = iItemService.findAll();
        if (all.size() >= 0) {
            return new Result(true, "成功", all);
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        Item byId = iItemService.findById(id);
        if (byId != null) {
            return new Result(true, "成功", byId);
        }
        return new Result(false, "失败");
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody FindByPage findByPage) {
        PageResult pageResult = iItemService.findPage(findByPage);
        return pageResult;
    }

    @GetMapping("/delete")
    public Result delete(int id) {
        int delete = iItemService.delete(id);
        if (delete > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody Item item) {
        System.out.println(item);
        int add = iItemService.add(item);
        if (add > 0) {
            return new Result(true, "成功",item.getId());
        }
        return new Result(false, "失败");

    }

    @PostMapping("/edit")
    public Result update(@RequestBody Item item) {
        int update = iItemService.update(item);
        if (update > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }


}
