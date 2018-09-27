package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import yqius.dataDeal.excelData.entity.Tables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.Map;

/**
 * HSSF biff8
 * XSSF 2007以后
 */
public class SXSSFMethod {

    public static String PATH = "./datas/workbook.xlsx";

    public synchronized String readExcelToHtml(String path) {
        try {
            InputStream input = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(input);
            Tables tb = this.readWorkbook(wb);//read Table
            tb.printTable();
            OutputStream outputStream = new FileOutputStream(path);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @param workbook
     */
    private Tables readWorkbook(Workbook workbook) {
        Tables newTable = new Tables();
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer,Map> locationMap = this.getCellRangeList(sheet.getMergedRegions());
        int index = 0;
        newTable.setAttr("style","border-collapse:collapse;");
        newTable.setAttr("width","100%");
        //循环行
        for(int rowNumber =sheet.getFirstRowNum();rowNumber <= sheet.getLastRowNum();rowNumber++){
            Row row = sheet.getRow(rowNumber);//Excel row
            Tables.Row rows = new Tables.Row();//html row
            if(row == null){ ;
                newTable.addRows(rows.createRow("  "));
                continue;
            }
            //循环某行 的列
            for(int colNumber = 0;colNumber<row.getLastCellNum();colNumber++){
                Cell cell = row.getCell(colNumber);
                if(cell == null){
                    Tables.Row.Cols col = new Tables.Row.Cols(" ");
                    rows.addCols(col);
                    continue;
                }else {

//                    Tables.Row.Cols co = new Tables.Row.Cols(this.getCellValue(cell));
//                    rows.addCols(co);

                }
            }
            newTable.addRows(rows);
        }
        return newTable;
    }


    /**
     *
     * @param list
     * @return list 存每合并区域的长宽和首位坐标
     */
    private Map<Integer,Map> getCellRangeList(List<CellRangeAddress> list) {
        Map<Integer,Map> locationMap = new HashMap<Integer, Map>();
        int index = 0;
        for(CellRangeAddress cellLi:list){
            Map<String,Integer> map =new HashMap<String,Integer>();
            int rowspan =cellLi.getLastRow()-cellLi.getFirstRow()+1;//
            int colspan = cellLi.getLastColumn()-cellLi.getFirstColumn()+1;
            map.put("row",cellLi.getFirstRow());
            map.put("col",cellLi.getFirstColumn());
            map.put("rowspan",rowspan);
            map.put("colspan",colspan);
            locationMap.put(index,map);
            index ++;
        }
        return locationMap;
    }

    /**
     * 未完成
     * @param cell
     * @return
     */
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()){
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
        }
        return "";
    }

    public static void main(String[] args) {
        SXSSFMethod sf = new SXSSFMethod();
        sf.readExcelToHtml(PATH);
    }
}
