package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.Workbook;
import org.jsoup.nodes.Element;
import yqius.dataDeal.documentParse.ExcelParser;
import yqius.dataDeal.excelData.entity.Tables;

/**
 * test
 */
public class CreateTable {
    public Element createTableElement(){
        Element tableEle = new Element("table");
        return tableEle;
    }

    public Element addCols(Element element){
        Element newEle = new Element("td");
        element.getElementsByTag("tr");
        element.appendChild(newEle);
        return element;
    }

    public Element addRows(Element element){
        element.appendElement("tr");
        return element;
    }

    public static void main(String[] args) {
//        Tables newTable = new Tables();
//        newTable.setAttr("style","border-collapse:collapse;");
//        newTable.setAttr("width","100%");
//        Tables.Row row=new Tables.Row();
//        Tables.Row.Cols cols = new Tables.Row.Cols().createCols("asd ");
//        Tables.Row.Cols cols1 = new Tables.Row.Cols().createCols("123123");
//        row.addCols(cols);
//        row.addCols(cols1);
//        newTable.addRows(row);
//        newTable.printTable();
        FileMethod xssfMethod = new FileMethod("");
        Workbook wb = xssfMethod.readExcel();
        Tables tables = new ExcelParser().getTables(wb);
        System.out.println(tables.table);
    }
}
