package yqius.dataDeal.excelData.generate;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import yqius.dataDeal.documentParse.ExcelParser;
import yqius.dataDeal.excelData.entity.Tables;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TableGenerate {
    public  Tables readWorkbook(Workbook workbook) {
        Tables newTable = new Tables();
        Sheet sheet = workbook.getSheetAt(0);
        int indexOfMergedArea = 0;
        newTable.setAttr("style","border-collapse:collapse;");
        newTable.setAttr("width","100%");
        newTable.setAttr("addClass","inTable");
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
                            int colspan = right-left+1;
                            int rowspan = bottom-top+1;
                            if (colspan!=1) co.setAttr("colspan", String.valueOf(colspan));
                            if (rowspan!=1) co.setAttr("rowspan", String.valueOf(rowspan));
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

    /**
     * get合并区域index
     * @param cell
     * @return 合并区域的index
     */
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
            case _NONE:
                return "   ";
            case BLANK:
                return " ";
        }
        return "";
    }
}
