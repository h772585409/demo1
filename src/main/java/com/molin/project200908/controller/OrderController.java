package com.molin.project200908.controller;

import ch.qos.logback.core.util.FileUtil;
import com.molin.project200908.Util.FileInsert;
import com.molin.project200908.Util.Filedownload;
import com.molin.project200908.Util.MessageConstant;
import com.molin.project200908.Util.Result;
import com.molin.project200908.pojo.Ordersetting;
import com.molin.project200908.pojo.Riqi;
import com.molin.project200908.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/findByMonth")
    public Result findByMonth(String date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM");
        try {
            Date date1 = (Date) formatter.parse(date);
            boolean equals = date.equals(formatter.format(date1));
            if (!equals) {
                return new Result(false, "日期格式错误，正确为YYYY-MM。");
            }
        } catch (Exception e) {
            return new Result(false, "日期格式错误，正确为YYYY-MM。");

        }
        String[] split = date.split("-");
        Ordersetting[] byMonth = orderService.findByMonth(new Riqi(split[0], split[1]));
        return new Result(true, "成功", byMonth);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Ordersetting ordersetting) {
        System.out.println(ordersetting);
        int update = orderService.update(ordersetting);
        if (update > 0) {
            return new Result(true, "成功");
        } else {
            return new Result(false, "失败");

        }
    }

    @RequestMapping("/file/download")
    public void testDownload() throws IOException {
        String filename = "ordersetting_template.xlsx";
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filename);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type", type);
        // 设置编码
        String hehe = new String(filename.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        Filedownload.download(filename, response);
    }

    @PostMapping("/file/upload")
    public Result fileUpload(MultipartFile file) {
        try {
            List addxlsx = FileInsert.addxlsx(file);
            boolean add = orderService.add(addxlsx);
            if (add) {
                return new Result(true, "上传成功");
            }
            return new Result(false, "上传失败");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "格式错误");
        }
    }

}
