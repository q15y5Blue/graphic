package yqius.dataDeal.documentParse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import yqius.dataDeal.excelData.entity.Tables;
import yqius.dataDeal.excelData.generate.TableGenerate;

import java.util.List;

/**
 * inputExcel and generate
 */
public class ExcelParser {
    public Tables initialTable;
    public ExcelParser(){}
    public Tables getTables(Workbook workbook){
        return new TableGenerate().readWorkbook(workbook);
    }
}
