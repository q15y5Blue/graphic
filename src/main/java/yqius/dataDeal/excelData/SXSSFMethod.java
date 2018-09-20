package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * HSSF biff8
 * XSSF 2007以后
 */
public class SXSSFMethod {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String path = "workbook.xlsx";
        SXSSFMethod sxssfMethod = new SXSSFMethod();
        sxssfMethod.readExcelToHtml(path);
    }

    public synchronized String readExcelToHtml(String path) {
        try {
            InputStream input = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(input);
            this.readWorkbook(wb);
            OutputStream outputStream = new FileOutputStream(path);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    private void readWorkbook(Workbook workbook) {
        StringBuffer strBuffer = new StringBuffer();
        Sheet sheet = workbook.getSheetAt(0);
        List<CellRangeAddress> list = sheet.getMergedRegions();
        strBuffer.append("<table style='border-collapse:collapse;' width='100%'>");
        //循环行
        for(int rowNumber =sheet.getFirstRowNum();rowNumber<=sheet.getLastRowNum();rowNumber++){
            Row row = sheet.getRow(rowNumber);
            if(row == null){
                strBuffer.append("<tr><td>&nbsp;&nbsp;</td></tr>");
                continue;
            }
            strBuffer.append("<tr>");
            //循环某行 的列
            for(int colNumber = 0;colNumber<row.getLastCellNum();colNumber++){
                Cell cell = row.getCell(colNumber);
                if(cell == null){
                    strBuffer.append("<td> </td>");
                    continue;
                }
//                String stringValue = this.getCellValue();
            }
            strBuffer.append("</tr>");
        }
//        Map<String,String> map[]=this.getMergeMaps(sheet);---------------------------------------待定
    }

    private Map<String,String>[] getMergeMaps(Sheet sheet) {
        Map [] maps = new Map[sheet.getLastRowNum()];
        int mergeNumber = sheet.getNumMergedRegions();

        for(int i=0;i<mergeNumber;i++){
            CellRangeAddress range = sheet.getMergedRegion(i);
            int topRow = range.getFirstRow();
            int topCol = range.getFirstColumn();
            int bottomRow = range.getLastRow();
            int bottomCol = range.getLastColumn();
//            System.out.println(topRow+","+topCol+","+bottomRow+","+bottomCol);
        }
        return  maps;
    }
    private String getCellValue(Cell cell) {
        switch (cell.getCellType()){
//            case Cell.CELL_TYPE_NUMERIC:
        }
        return "";
    }


}
