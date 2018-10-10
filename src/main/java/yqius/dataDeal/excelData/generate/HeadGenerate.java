package yqius.dataDeal.excelData.generate;

import org.jsoup.nodes.Element;

public class HeadGenerate {
    public Element element = new Element("head");
    public HeadGenerate(String styleUrl,String... script){
        this.generateHead(styleUrl,script);
    }

    public void generateHead(String stylrUrl,String... script){
        this.element.appendChild(this.creatMeta());
        this.createScript(script);
        this.addStyleSrc(stylrUrl);
    }

    public Element creatMeta(){
        Element elementMeta = new Element("meta");
        elementMeta.attr("http-equiv","Content-Type");
        elementMeta.attr("content","text/html; charset=utf-8");
        return elementMeta;
    }

    public void createScript(String... url){
        for(int i=0;i < url.length;i++) {
            Element script = new Element("script");
            script.attr("scr", url[i]);
            this.element.appendChild(script);
        }
    }

    public void addStyleSrc(String url){
            Element style = new Element("link");
            style.attr("rel","stylesheet");
            style.attr("type","text/css");
            style.attr("href",url);
            this.element.appendChild(style);
    }

}
