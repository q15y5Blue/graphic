package yqius.dataDeal.excelData.documentParse;

import org.apache.poi.ss.usermodel.Workbook;
import yqius.dataDeal.entity.Tables;

/**
 * parser
 * 解析html或者Excel文件的主要类
 */
public class DocumentPaser {
//    public static String PATH ="./datas/test.html";
    public static volatile DocumentPaser instance = null;
    public static DocumentPaser getInstance() {
        if (instance == null) {
            synchronized (DocumentPaser.class) {
                if (instance == null) {
                    instance = new DocumentPaser();
                }
            }
        }
        return instance;
    }

    ExcelTableParser excelTableParser = new ExcelTableParser();
    public Tables parseExcelXSSF(Workbook workbook){
        return excelTableParser.parseExcel(workbook);
    }
}
