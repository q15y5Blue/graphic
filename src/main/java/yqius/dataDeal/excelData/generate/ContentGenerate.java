package yqius.dataDeal.excelData.generate;


import org.jsoup.nodes.Element;
import yqius.dataDeal.entity.Tables;

/**
 * body and table contains
 */
public class ContentGenerate {

    public Element contentElement=new Element("body");
    public ContentGenerate(Tables tables){
        contentElement.appendChild(this.getTableElement(tables));
    }
    public ContentGenerate(){}

    /**
     * getTableTag
     * @param tables
     * @return
     */
    public Element getTableElement(Tables tables){
        if(tables.rows.size()!=0){
            return tables.table;
        }
        else {
            return null;
        }
    }

}
