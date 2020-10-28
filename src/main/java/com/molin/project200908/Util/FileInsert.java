package com.molin.project200908.Util;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.molin.project200908.pojo.Ordersetting;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileInsert {
    public static String add(MultipartFile file, String filePath) throws IOException {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!(".jpg".equals(suffix) || ".png".equals(suffix) || ".jfif".equals(suffix))) {
            System.out.println("非法文件上传");
            return "非法文件上传";
        }
        String path = "/C:/Users/86134/IdeaProjects/project200907/src/main/resources/static/" + filePath;
//        String path = "/D:/" + filePath;
        String UUID = System.currentTimeMillis() + "-";
        // String id = UUID.randomUUID().toString();
        String newFileName = UUID + fileName;
        //File.separator 表示:/
        File destFile = new File(path + File.separator + newFileName);
        //判断该文件下的上级文件夹是否存在 不存在创建
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        //上传文件
        file.transferTo(destFile);

        return newFileName;
    }

//    public static void main(String[] args) throws IOException {
//        BufferedInputStream bis = null;
//        String filename = "ordersetting_template.xlsx";
//        bis = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\86134\\IdeaProjects\\project200907\\src\\main\\resources\\static\\files\\" + filename)));
//        XSSFWorkbook workbook = new XSSFWorkbook(bis);
//        XSSFCell cell = workbook.getSheetAt(0).getRow(1).getCell(0);
//        System.out.println(cell);
//        int cellType = cell.getCellType();
////        System.out.println(cellType);
//        Date dateCellValue = cell.getDateCellValue();
////        System.out.println(dateCellValue);
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String format = dateFormat.format(dateCellValue);
//        System.out.println(format);
//        String[] split = format.split("-");
//        for (int i = 0; i < split.length; i++) {
//            String s = split[i];
//            System.out.println(s);
//        }
//
////        System.out.println("======================");
////        XSSFCell cell2 = workbook.getSheetAt(0).getRow(1).getCell(1);
////        System.out.println(cell2);
////        int cellType2 = cell2.getCellType();
////        System.out.println(cellType2);
////        int numericCellValue = (int)cell2.getNumericCellValue();
////        System.out.println(numericCellValue);
//
//    }

    public static List addxlsx(MultipartFile file) throws IOException {
        List<Ordersetting> list = new ArrayList();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            Ordersetting order = new Ordersetting();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                if (j == 0) {
                    Date dateCellValue = cell.getDateCellValue();
                    order.setOrderDate(dateCellValue);
                } else if (j == 1) {
                    int numericCellValue = (int)cell.getNumericCellValue();
                    order.setNumber(numericCellValue);
                }
            }
            list.add(order);
        }
        workbook.close();
        return list;
    }

    private static Log log = LogFactory.getLog(FileInsert.class);

    /**
     * @param path 文件的路径
     * @return 是：true；不是：false
     * @Title: validExxel
     * @Description: 验证文件是不是 Excel 文件
     */
    private static Boolean validExxel(String path) {
        String xls = "^.+\\.(?i)(xls)$";    // 正则表达式判断是不是以 .xls 结尾的文件
        String xlsx = "^.+\\.(?i)(xlsx)$";    // 正则表达式判断是不是以 .xlsx 结尾的文件
        if (path == null || !(path.matches(xls) || path.matches(xlsx))) {
            log.error("@!--------------------不是Excel文件！");
            return false;
        }
        return true;
    }

    /**
     * @param path         文件的路径
     * @param isSheetIndex 是否指定工作表（Sheet)
     * @param sheetIndex   指定工作表在工作簿中的位置
     *                     1、如果 isSheetIndex 为 true 时，sheetIndex 为大于0的整数
     *                     1.1、如果 sheetIndex 小于等于 0，默认读取工作簿中的第一张工作表；
     *                     1.2、如果 sheetIndex 大于工作簿中工作表的总数，默认读取工作簿中最后一张工作表；
     *                     2、如果 isSheetIndex 为 false 时，sheetIndex 可以是任何整数，此时读取工作簿中所有工作表的数据
     * @return Excel Excel 文件的数据集
     * @Title: readExcel
     * @Description: 读取 Excel 文件的数据
     */
    public static List<Object> readExcel(String path, Boolean isSheetIndex, Integer sheetIndex) {
        // 指定工作表位置小于1时，默认读取第一张工作表的数据
        if (sheetIndex < 1) {
            sheetIndex = 1;
        }
        List<Object> dataList = new ArrayList<>();
        InputStream is = null;
        // 通过指定的文件路径创建文件对象
        File file = new File(path);
        if (file == null || !file.exists()) {
            log.error("@!--------------------文件不存在！");
            return null;
        }
        if (!validExxel(path)) {
            log.error("@!--------------------此文件不是 Excel 文件！");
            return null;
        }
        try {
            is = new FileInputStream(file);    // 获取文件的输入流
            dataList = readFile(is, isSheetIndex, sheetIndex - 1);
        } catch (FileNotFoundException e) {
            log.error("@!--------------------找不到该文件！");
        } finally {
            if (is != null) {
                try {
                    is.close();    // 关闭文件输入流
                } catch (IOException e) {
                    log.error("@!-----------------------------文件输入流关闭失败！");
                }
            }
        }
        return dataList;
    }

    /**
     * @param is           文件输入流
     * @param isXls        是不是 xls 格式的文件
     * @param isSheetIndex 是不是指定的工作表
     * @param sheetIndex   工作表在工作簿中的位置
     * @return 工作簿的数据
     * @Title: readFile
     * @Description: 读取输入流中的数据
     */
    public static List<Object> readFile(InputStream is, Boolean isSheetIndex, Integer sheetIndex) {
        List<Object> dataList = new ArrayList<>();
        Workbook wb = null;
        try {
            // 创建工作簿（HSSFWorkbook或XSSFWorkbook），根据文件输入流自动检测创建对象
            wb = WorkbookFactory.create(is);
            Integer sheetCount = wb.getNumberOfSheets();    // 工作簿中工作表的总数
            List<Object> sheetList = new ArrayList<>();
            if (isSheetIndex) {    // 读取指定工作表
                // 判断指定的位置是否在工作簿中有对应的工作表
                if (sheetIndex < sheetCount) {
                    sheetList = readWorkBook(wb, sheetIndex);
                } else {
                    // 没有找到对应的工作表时，读取工作簿的最后一张工作表
                    sheetList = readWorkBook(wb, sheetCount - 1);
                }
                dataList.add(sheetList);    // 添加工作表的数据到工作簿数据集中
            } else {
                // 读取工作簿的所有工作表
                for (int i = 0; i < sheetCount; i++) {
                    sheetList = readWorkBook(wb, i);
                    dataList.add(sheetList);    // 将工作簿中的每一张不为空的工作表添加到工作簿的数据集中
                }
            }
        } catch (IOException | InvalidFormatException e) {
            log.error("@!------------------------------创建工作簿失败！");
        }
        return dataList;
    }

    /**
     * @param wb    工作簿
     * @param index 工作表在工作簿中的位置
     * @return 工作表的数据
     * @Title: readWorkBook
     * @Description: 读取工作表中的数据
     */
    private static List<Object> readWorkBook(Workbook wb, Integer index) {
        List<Object> sheetList = new ArrayList<>();
        Integer totalRows = 0;
        Integer totalCells = 0;
        Sheet sheet = wb.getSheetAt(index);    // 获取工作表对象
        sheetList.add(sheet.getSheetName());    // 添加工作表的名称到工作表中
        totalRows = sheet.getPhysicalNumberOfRows();    // 获取工作表的总行数
        if (totalRows > 0 && sheet.getRow(0) != null) {
            // 获取工作表的总列数，第一行或第二行的列数必须是整张工作表的总列数
            if (sheet.getRow(1) != null && sheet.getRow(0).getPhysicalNumberOfCells() < sheet.getRow(1).getPhysicalNumberOfCells()) {
                totalCells = sheet.getRow(1).getPhysicalNumberOfCells();
            } else {
                totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
            }
        }
        List<Object> rowList = new ArrayList<>();
        for (int i = 0; i < totalRows; i++) {
            Row row = sheet.getRow(i);    // 获取行对象
            // 去除空行
            if (row == null) {
                continue;
            }
            List<Object> cellList = new ArrayList<Object>();
            for (int j = 0; j < totalCells; j++) {
                Cell cell = row.getCell(j);    // 获取单元格对象
                cellList.add(cell);    // 将单元格中的数据添加到单元格数据集中
            }
            rowList.add(cellList);    // 将单元格数据集天津爱到行数据集中
        }
        sheetList.add(rowList);    // 将行数据集添加到工作表中
        return sheetList;
    }

//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) {
////		List<Object> dataList = readExcel ("D:\\Desktop\\test.xlsx", false, 0);
//        List<Object> dataList = readExcel("D:\\Desktop\\test.xlsx", true, 1);
//        if (dataList != null) {
//            for (int i = 0; i < dataList.size(); i++) {
//                // 获取工作表
//                List<Object> sheetList = (List<Object>) dataList.get(i);
//                String sheetName = (String) sheetList.get(0);
//                System.out.println("工作表的名称：" + sheetName);
//                System.out.println("工作表的数据：");
//                for (int j = 1; j < sheetList.size(); j++) {
//                    // 获取工作表的每一行
//                    List<Object> rowList = (List<Object>) sheetList.get(j);
//                    for (int k = 0; k < rowList.size(); k++) {
//                        // 获取工作表的每一列
//                        List<Object> cellList = (List<Object>) rowList.get(k);
//                        for (Object object : cellList) {
//                            // 获取每一个单元格的数据
//                            System.out.print("　　" + object);
//                        }
//                        System.out.println();
//                    }
//                }
//            }
//        }
//    }

}
