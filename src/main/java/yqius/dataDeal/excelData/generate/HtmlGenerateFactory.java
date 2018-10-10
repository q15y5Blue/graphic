package yqius.dataDeal.excelData.generate;

import org.jsoup.nodes.Element;
import yqius.dataDeal.excelData.Constant;
import yqius.dataDeal.excelData.documentParse.DocumentPaser;
import yqius.dataDeal.entity.Tables;

/**
 * Html生成
 */
public class HtmlGenerateFactory {
    private HtmlGenerateFactory(){};
    public static volatile HtmlGenerateFactory instance = null;
    public static HtmlGenerateFactory getInstance() {
        if (instance == null) {
            synchronized (DocumentPaser.class) {
                if (instance == null) {
                    instance = new HtmlGenerateFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 生成html
     * @param tables
     * @return
     */
    public Element generateHTML(Tables tables){
        Element html = new Element("html");
        html.appendChild(this.getHead(Constant.STYLEPAHT,Constant.SCRIPT1,Constant.SCRIPT2));
        html.appendChild(this.getContent(tables));
        return html;
    }

    /**
     *
     * @param tables
     * @return tableElement
     */
    public Element getContent(Tables tables){
        ContentGenerate tableGenerate = new ContentGenerate(tables);
        return tableGenerate.contentElement;
    }

    /**
     * getHead
     * @return headElement
     */
    public Element getHead(String styleUrl,String ...scripts){
        HeadGenerate headGenerate = new HeadGenerate(styleUrl,scripts);
        return headGenerate.element;
    }



}
