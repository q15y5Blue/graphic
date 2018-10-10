package yqius.dataDeal.execute;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.nodes.Element;
import yqius.dataDeal.excelData.Constant;
import yqius.dataDeal.excelData.FileMethod;
import yqius.dataDeal.excelData.documentParse.DocumentPaser;
import yqius.dataDeal.entity.Tables;
import yqius.dataDeal.excelData.generate.HtmlGenerateFactory;

/**
 * Main
 */
public class CreateTable {

    public void executeCreateTable(){
//        Logger logger = LogManager.getLogger("main");
        FileMethod xssfMethod = new FileMethod();
        Workbook wb = xssfMethod.readExcel();
        Tables tables = DocumentPaser.getInstance().parseExcelXSSF(wb);
        Element content =HtmlGenerateFactory.getInstance().generateHTML(tables);
        System.out.println(content.outerHtml());
        xssfMethod.writeSteam(Constant.OUTPATH,content.outerHtml());
    }

    public static void main(String[] args) {
        CreateTable ct = new CreateTable();
        ct.executeCreateTable();
    }
}
