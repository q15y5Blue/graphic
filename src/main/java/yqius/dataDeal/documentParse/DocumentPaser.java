package yqius.dataDeal.documentParse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;

public class DocumentPaser {
    public static String PATH ="./datas/test.html";

    public Document readLocalHTMLAsDocument(){
        File inFile = new File(PATH);
        Document document =null;
        try {
            document = Jsoup.parse(inFile,"UTF-8","");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 写文档
     * @param document
     */
    public void writeHTML(Document document){
        FileWriter fileWriter = null;
        try {
            File file = new File(PATH);
            fileWriter = new FileWriter(file);
            fileWriter.write(document.html());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args){
        DocumentPaser documentPaser = new DocumentPaser();
        Document document = documentPaser.readLocalHTMLAsDocument();
        Element tableEle = document.getElementById("table");
        Element newEle = new Element("tr");
        Element tdEle = new Element("td");
        tdEle.append("xxxxxsd");
        newEle.appendChild(tdEle);
        tableEle.appendChild(newEle);
        System.out.println(document);
        documentPaser.writeHTML(document);
    }
}
