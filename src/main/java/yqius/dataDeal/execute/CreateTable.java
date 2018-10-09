package yqius.dataDeal.execute;

import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.nodes.Element;
import yqius.dataDeal.excelData.FileMethod;
import yqius.dataDeal.excelData.documentParse.DocumentPaser;
import yqius.dataDeal.entity.Tables;
import yqius.dataDeal.excelData.generate.HtmlGenerateFactory;

/**
 * Main
 */
public class CreateTable {

    public static void main(String[] args) {
        FileMethod xssfMethod = new FileMethod();
        Workbook wb = xssfMethod.readExcel();
        Tables tables = DocumentPaser.getInstance().parseExcelXSSF(wb);
        Element content =HtmlGenerateFactory.getInstance().generateHTML(tables);
        System.out.println(content);
    }
}
