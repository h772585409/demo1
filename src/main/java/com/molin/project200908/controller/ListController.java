package com.molin.project200908.controller;

import com.molin.project200908.Util.Filedownload;
import com.molin.project200908.Util.Result;
import com.molin.project200908.pojo.ListGroup;
import com.molin.project200908.service.IListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/list")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ListController {
    @Autowired
    private IListService iListService;

    @RequestMapping("/groupbymonth")
    public Result findOrderGroupByMonth() {
        try {
            Date date = new Date();
            String i = Calendar.getInstance().get(Calendar.YEAR)+"";
            List<ListGroup> orderGroupByMonth = iListService.findOrderGroupByMonth(i);
            return new Result(true, "成功", orderGroupByMonth);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    @RequestMapping("/groupbyweek")
    public Result findOrderByWeek() {
        try {
            ListGroup orderByWeek = iListService.findOrderByWeek();
            return new Result(true, "成功", orderByWeek);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "失败");
        }
    }

    @RequestMapping("/file/download")
    public void testDownload(String date, String today1, String today2, String week1, String week2, String month1, String month2) throws IOException {
        List list = new ArrayList();
        list.add(date);
        list.add(today1);
        list.add(today2);
        list.add(week1);
        list.add(week2);
        list.add(month1);
        list.add(month2);
        String filename = "report_template.xlsx";
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
        Filedownload.download2(filename, response,list);
    }
}
