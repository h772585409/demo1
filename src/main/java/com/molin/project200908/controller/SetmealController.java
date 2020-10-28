package com.molin.project200908.controller;

import com.molin.project200908.Util.*;
import com.molin.project200908.common.FindByPage;
import com.molin.project200908.pojo.Setmeal;
import com.molin.project200908.service.ISetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SetmealController {

    @Autowired
    private ISetmealService iSetmealService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/findAll")
    public Result findAll() {
        List<Setmeal> all = iSetmealService.findAll();
        if (all.size() >= 0) {
            return new Result(true, "成功", all);
        }
        return new Result(false, "失败");
    }

    @RequestMapping("/findById")
    public Result findById(int id) {
        Setmeal byId = iSetmealService.findById(id);
        if (byId != null) {
            return new Result(true, "成功", byId);
        }
        return new Result(false, "失败");
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody FindByPage findByPage) {
        PageResult pageResult = iSetmealService.findPage(findByPage);
        return pageResult;
    }

    @GetMapping("/delete")
    public Result delete(int id) {
        int delete = iSetmealService.delete(id);
        if (delete > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

    @PostMapping("/add")
    public Result add(Setmeal setmeal) {
        try {
            if (setmeal.getPicture() != null) {
                String newFileName = FileInsert.add(setmeal.getPicture(), "imgs");
                if ("非法文件上传".equals(newFileName)) {
                    return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
                }
                redisUtil.sSet("localImgs",newFileName);
                System.out.println(newFileName);
                setmeal.setImg(newFileName);
            }
            int add = iSetmealService.add(setmeal);
            if (add > 0) {
                int id = setmeal.getId();
                redisUtil.sSet("dataBaseImgs",setmeal.getImg());
                return new Result(true, "成功", id);
            }
            return new Result(false, "失败1");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "失败2   ");
        }
    }

    @PostMapping("/addJpg")
    public Result add2(MultipartFile file) {
        try {
            if (file != null) {
                String newFileName = FileInsert.add(file, "imgs");
                System.out.println(newFileName);
                if ("非法文件上传".equals(newFileName)) {
                    return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
                }
                //将newFileName保存Redis集合
                redisUtil.sSet("localImgs",newFileName);
                return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "失败2");
        }
        return null;
    }

    @PostMapping("/edit")
    public Result update(@RequestBody Setmeal setmeal) {
        int update = iSetmealService.update(setmeal);
        if (update > 0) {
            return new Result(true, "成功");
        }
        return new Result(false, "失败");
    }

}
