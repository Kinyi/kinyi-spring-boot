package com.dataw.rhino.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Excel操作工具类
 *
 * @author Kinyi_Chan
 * @since 2019-01-15
 */
@Slf4j
public class ExcelUtil {

    private ExcelUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <E> HSSFWorkbook writeXlsWithEntity(List<LinkedHashMap<String, Integer>> head, String[] title, List<E> entities, int checkNum, boolean highlight) throws Exception {
        List<Map<String, String>> list = new LinkedList<>();
        for (Object entity : entities) {
            Map<String, String> map = new HashMap<>(16);
            for (Field field : entity.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(entity) != null) {
                    map.put(field.getName(), field.get(entity).toString());
                }
            }
            list.add(map);
        }
        return writeXls(head, title, list, checkNum, highlight);
    }

    /**
     * 若文件已存在,则追加写数据, xls格式为旧版本的excel
     *
     * @param head      表头
     * @param title     列名
     * @param content   内容数据 List<Map<String, String>>
     * @param checkNum  需要检查合并的列数, 不需要则为0
     * @param highlight 是否需要高亮显示
     * @throws Exception
     */
    public static HSSFWorkbook writeXls(List<LinkedHashMap<String, Integer>> head, String[] title, List<Map<String, String>> content, int checkNum, boolean highlight) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        int headSize = 0;
        //设置表头
        if (head != null && !head.isEmpty()) {
            for (int i = 0; i < head.size(); i++) {
                LinkedHashMap<String, Integer> map = head.get(i);
                if (map.values().stream().reduce((a, b) -> a + b).orElse(1) != title.length) {
                    log.error(map.entrySet().toString());
                    throw new IllegalArgumentException("表头常数之和不等于列数");
                }
                HSSFRow row = sheet.createRow(i);
                int mergeIndex = 0;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    //需要合并单元格
                    if (value > 1) {
                        sheet.addMergedRegion(new CellRangeAddress(i, i, mergeIndex, mergeIndex + value - 1));
                    }
                    row.createCell(mergeIndex).setCellValue(key);
                    mergeIndex = mergeIndex + value;
                }
            }
            headSize = head.size();
        }
        //创建列名栏
        HSSFRow headRow = sheet.createRow(headSize);
        for (int m = 0; m <= title.length - 1; m++) {
            HSSFCell cell = headRow.createCell(m);
            cell.setCellType(CellType.STRING);
            sheet.setColumnWidth(m, 6000);
            if (highlight) {
                HSSFCellStyle style = workbook.createCellStyle();
                HSSFFont font = workbook.createFont();
                font.setBold(true);
                short color = HSSFColor.HSSFColorPredefined.RED.getIndex();
                font.setColor(color);
                style.setFont(font);
                cell.setCellStyle(style);
            }
            //填写数据
            cell.setCellValue(title[m]);
        }
        int index = sheet.getLastRowNum();
        List<List<String>> checkList = new LinkedList<>();
        //写入数据
        for (Map<String, String> line : content) {
            HSSFRow row = sheet.createRow(index + 1);
            writeRow(row, title, line, checkList, checkNum);
            index++;
        }
        //重复数据列合并
        if (!checkList.isEmpty()) {
            int columnIndex = 0;
            for (List<String> column : checkList) {
                //默认title占用一行
                int rowIndex = headSize + 1;
                int lastRow = rowIndex;
                String value = null;
                for (String cell : column) {
                    if (value == null) {
                        value = cell;
                    } else if (!value.equals(cell)) {
                        if (rowIndex - lastRow != 1) {
                            sheet.addMergedRegion(new CellRangeAddress(lastRow, rowIndex - 1, columnIndex, columnIndex));
                        }
                        lastRow = rowIndex;
                        value = cell;
                    }
                    rowIndex++;
                }
                if (rowIndex - lastRow != 1) {
                    sheet.addMergedRegion(new CellRangeAddress(lastRow, rowIndex - 1, columnIndex, columnIndex));
                }
                columnIndex++;
            }
        }
        return workbook;
    }

    private static void writeRow(Row row, String[] title, Map<String, String> line, List<List<String>> checkList, int checkNum) {
        try {
            for (int n = 0; n <= title.length - 1; n++) {
                row.createCell(n);
                if (line.get(title[n]) != null && line.get(title[n]).length() > 20000) {
                    log.info("单元格内容过长! -> " + title[n]);
                    continue;
                }
                row.getCell(n).setCellValue(line.get(title[n]));
                if (n < checkNum) {
                    if (checkList.size() < n + 1) {
                        checkList.add(new LinkedList<>());
                    }
                    checkList.get(n).add(line.get(title[n]));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}