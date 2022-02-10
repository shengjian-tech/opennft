package net.shengjian.frame.util;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private ExcelUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static Cell[] getExcleRow(File excelFile, int rowIndex) throws Exception {
        Workbook workbook = null;

        Cell[] row = null;
        try {
            workbook = Workbook.getWorkbook(excelFile);
            row = workbook.getSheet(0).getRow(rowIndex);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return row;

    }

    public static List<Cell[]> getExcle(File excelFile) throws Exception {
        Workbook workbook = null;
        int count = 0;
        List<Cell[]> list = new ArrayList<>();
        try {
            workbook = Workbook.getWorkbook(excelFile);
            Sheet sheet = workbook.getSheet(0);
            count = sheet.getRows();
            for (int i = 0; i < count; i++) {
                Cell[] row = sheet.getRow(i);
                list.add(row);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return list;

    }

    /**
     * 数据转excel
     *
     * @param data  数据List
     * @param title excel表头  [0]="userName"; [1]="用户名称";
     * @return
     * @throws Exception
     */
    public static File dataToExcel(List data, List<String[]> title, String path) throws Exception {

        int row = 0;
        int col = 0;
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Sheet 1");

        XSSFRow rowData0 = sheet.createRow(row++);
        for (String[] tit : title) {//表头
            String text = tit[1];
            sheet.setColumnWidth(col, 5000);
            rowData0.createCell(col).setCellValue(text);
            col++;
        }
        if (CollectionUtils.isNotEmpty(data)) {
            for (Object obj : data) {//数据
                XSSFRow rowData = sheet.createRow(row);
                col = 0;
                for (String[] tit : title) {
                    String properties = tit[0];
                    String[] split = properties.split("\\.");
                    List<String> list = new ArrayList<>(Arrays.asList(split));
                    Object value = getValue(list, obj);
                    value = Optional.ofNullable(value).orElse("");
                    rowData.createCell(col).setCellValue(value.toString());
                    col++;
                }
                row++;
            }
        }
        String filePath = path + SecUtils.getTimeNO() + GlobalStatic.excelext;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        wb.write(fileOutputStream);
        return new File(filePath);
    }

    /**
     * 取出对象属性中的值，知道properties中没有属性为止
     *
     * @param properties 属性集合
     * @param obj
     * @return
     * @throws Exception
     */
    private static Object getValue(List<String> properties, Object obj) throws Exception {
        if (CollectionUtils.isEmpty(properties)) {
            return obj;
        }
        if (obj instanceof Collection) {//是否集合
            StringBuffer str = new StringBuffer();
            Collection collection = (Collection) obj;
            int i = 0;
            for (Object o : collection) {
                i++;
                List<String> objects = new ArrayList<>(properties);
                Object value = getValue(objects, o);//遍历取出集合中数据
                str.append(value);
                if (i < collection.size()) {
                    str.append(",");
                }
            }
            return str.toString();
        }
        String subProperties = properties.get(0);
        properties.remove(0);
        obj = ClassUtils.getPropertieValue(subProperties, obj);
        if (CollectionUtils.isEmpty(properties)) {
            return obj;
        }
        return getValue(properties, obj);
    }

    /**
     * excel 版本（xls,xlsx）
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static List<Row> getExcel(String path) throws Exception {
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        int count = 0;
        List<Row> list = new ArrayList<>();
        try {
            String fileType = path.substring(path.lastIndexOf(".") + 1);
            FileInputStream fileInputStream = new FileInputStream(path);
            if ("xls".equalsIgnoreCase(fileType)) {
                workbook = new HSSFWorkbook(fileInputStream);
            } else if ("xlsx".equalsIgnoreCase(fileType)) {
                workbook = new XSSFWorkbook(fileInputStream);
            } else {
                throw new RuntimeException("excel格式不正确！");
            }
            org.apache.poi.ss.usermodel.Sheet sheetAt = workbook.getSheetAt(0);
            count = sheetAt.getLastRowNum() + 1;
            for (int i = 0; i < count; i++) {
                Row row = sheetAt.getRow(i);
                if (row != null) {
                    list.add(row);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return list;
    }
}
