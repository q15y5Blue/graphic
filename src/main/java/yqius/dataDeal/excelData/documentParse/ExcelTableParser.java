package yqius.dataDeal.excelData.documentParse;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import yqius.dataDeal.entity.Tables;

import java.util.List;

/**
 * inputExcel and generate
 */
public class ExcelTableParser {


    public Tables parseExcel(Workbook workbook) {
        Tables newTable = new Tables();
        Sheet sheet = workbook.getSheetAt(0);
        int indexOfMergedArea = 0;
        newTable.setAttr("style","border-collapse:collapse;");
        newTable.setAttr("width","100%");
        newTable.setAttr("addClass","inTable");
        //循环行
        for(int rowNumber =sheet.getFirstRowNum();rowNumber <= sheet.getLastRowNum();rowNumber++){
            Row row = sheet.getRow(rowNumber);//Excel row
            Tables.Row rows =null;//html row
            if(row == null){
                rows = new Tables.Row();
                rows.addCols(new Tables.Row.Cols(" "));
            }
            //这里会出现<tr></tr>连起来用的情况
            else {
                rows = new Tables.Row();//<tr> </tr>
//                循环某行 的列
                for(int colNumber = 0;colNumber<row.getLastCellNum();colNumber++){
                    Cell cell = row.getCell(colNumber);
                    Tables.Row.Cols col = new Tables.Row.Cols(" ");
                    if(cell == null){
                        rows.addCols(col);
                    }else {
                        //cell位于合并区域内
                        if(this.isInMergedArea(cell)!=-1){
                            indexOfMergedArea = this.isInMergedArea(cell);
                            int left = sheet.getMergedRegion(indexOfMergedArea).getFirstColumn();
                            int top = sheet.getMergedRegion(indexOfMergedArea).getFirstRow();
                            //第一个啊
                            if(cell.getRowIndex()==top&&cell.getColumnIndex()==left){
                                int bottom =sheet.getMergedRegion(indexOfMergedArea).getLastRow();
                                int right = sheet.getMergedRegion(indexOfMergedArea).getLastColumn();
                                Tables.Row.Cols co = new Tables.Row.Cols(this.getCellValue(cell));
                                int colspan = right-left+1;
                                int rowspan = bottom-top+1;
                                if(colspan!=1)    co.setAttr("colspan", String.valueOf(colspan));
                                if(rowspan!=1)    co.setAttr("rowspan", String.valueOf(rowspan));
                                rows.addCols(co);
                            }
                        }else {
                            String cellValue = this.getCellValue(cell);
                            Tables.Row.Cols co = new Tables.Row.Cols(cellValue);
                            rows.addCols(co);
                        }
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
        for(CellRangeAddress cellAddr:list){
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
                return " ";
            case BLANK:
                return "&nbsp;";
        }
        return "";
    }
}
