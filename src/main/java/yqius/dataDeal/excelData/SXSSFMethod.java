package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import yqius.dataDeal.documentParse.DocumentPaser;
import yqius.dataDeal.excelData.entity.Tables;

import java.util.List;
import java.io.*;
import java.util.HashMap;
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
        List<CellRangeAddress> list = sheet.getMergedRegions();
        for(CellRangeAddress cellAddresses:list){
            System.out.println(cellAddresses.getNumberOfCells());
        }
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
                    Tables.Row.Cols co = new Tables.Row.Cols(this.getCellValue(cell));
                    rows.addCols(co);
                }
            }
            newTable.addRows(rows);
        }
        return newTable;
    }

    /**
     * 设置td格式
     * getMergeMaps
     * @param row
     * @param cell
     */
    private Tables.Row setColsType(Tables.Row row, Cell cell) {
        Tables.Row.Cols col = new Tables.Row.Cols();
        String stringValue = this.getCellValue(cell);
        col.addContent(stringValue);
        List<CellRangeAddress> list = cell.getSheet().getMergedRegions();
        for(CellRangeAddress cellAdd:list){
            if(cellAdd.isInRange(cell)){
                col.setAttr("rowspan",String.valueOf(cellAdd.getLastRow()-cellAdd.getFirstRow()+1));
                col.setAttr("colspan",String.valueOf(cellAdd.getLastColumn()-cellAdd.getFirstColumn()+1));
                row.addCols(col);
                return row;
            }
        }
        return  row;
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
        }
        return  maps;
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
