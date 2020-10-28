package com.molin.project200908.Util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Filedownload {

    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\86134\\IdeaProjects\\project200907\\src\\main\\resources\\static\\files\\" + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

    public static void download2(String filename, HttpServletResponse res, List list) throws IOException {
        BufferedInputStream bis = null;
        bis = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\86134\\IdeaProjects\\project200907\\src\\main\\resources\\static\\files\\" + filename)));
        XSSFWorkbook workbook = new XSSFWorkbook(bis);
        XSSFSheet sheetAt = workbook.getSheetAt(0);

        XSSFCell cell = sheetAt.getRow(2).getCell(5);
        cell.setCellValue((String) list.get(0));

        cell = sheetAt.getRow(4).getCell(5);
        cell.setCellValue((String) list.get(1));
        cell = sheetAt.getRow(4).getCell(7);
        cell.setCellValue((String) list.get(2));
        cell = sheetAt.getRow(5).getCell(5);
        cell.setCellValue((String) list.get(3));
        cell = sheetAt.getRow(5).getCell(7);
        cell.setCellValue((String) list.get(4));
        cell = sheetAt.getRow(6).getCell(5);
        cell.setCellValue((String) list.get(5));
        cell = sheetAt.getRow(6).getCell(7);
        cell.setCellValue((String) list.get(6));

        OutputStream outputStream = res.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();

    }
}
