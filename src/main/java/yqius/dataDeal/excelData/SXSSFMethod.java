package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import yqius.dataDeal.excelData.entity.Tables;

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
//        Map<Integer,Map> locationMap = this.getCellRangeList(sheet.getMergedRegions());
        int indexOfMergedArea = 0;
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
                    if(this.isInMergedArea(cell)!=-1){
                        indexOfMergedArea = this.isInMergedArea(cell);
                        int right = sheet.getMergedRegion(indexOfMergedArea).getLastColumn();
                        int left = sheet.getMergedRegion(indexOfMergedArea).getFirstColumn();
                        int top = sheet.getMergedRegion(indexOfMergedArea).getFirstRow();
                        int bottom =sheet.getMergedRegion(indexOfMergedArea).getLastRow();
                        //第一个啊
                        if(cell.getRowIndex()==top&&cell.getColumnIndex()==left){
                            Tables.Row.Cols co = new Tables.Row.Cols(this.getCellValue(cell));
                            co.setAttr("colspan", String.valueOf(right-left+1));
                            co.setAttr("rowspan", String.valueOf(bottom-top+1));
                            rows.addCols(co);
                        }

                    }else {
                        Tables.Row.Cols co = new Tables.Row.Cols(this.getCellValue(cell));
                        rows.addCols(co);
                    }
                }
            }
            newTable.addRows(rows);
        }
        return newTable;
    }

    private Integer isInMergedArea(Cell cell) {
        int flag = -1;
        List<CellRangeAddress> list = cell.getSheet().getMergedRegions();
        for(CellRangeAddress cellAddr  :list){
            if(cellAddr.isInRange(cell)){
                flag = list.indexOf(cellAddr);
                return flag;
            }
        }
        return flag;
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
        return " ";
    }

    public static void main(String[] args) {
        SXSSFMethod sf = new SXSSFMethod();
        sf.readExcelToHtml(PATH);
    }
}
