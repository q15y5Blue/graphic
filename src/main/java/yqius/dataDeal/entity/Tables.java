package yqius.dataDeal.entity;

import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

/**
 * 对Table类进行再封装
 * 其中包括了基本数据结构
 * 和jsoup中的Element
 */
public class Tables {
    public List<Row> rows = new ArrayList<Row>();
    public Element table;

    public Tables() {
        table = new Element("table");
    }

    public Tables(List<Row> rows) {
        this.rows = rows;
        Row row =new Row();
        Row.Cols tr = new Row.Cols();
        row.addCols(tr);
    }

    public void addRows(Row rows){
        table.appendChild(rows.tr);
        this.rows.add(rows);
    }

    public void setAttr(String s1,String s) {
        switch (s1){
            case "addClass":
                table.addClass(s);
                break;
        default:
            table.attr(s1,s);
            break;
        }
    }

    /**
     * Tr
     */
    public static class Row{
        public List<Cols> cols;
        public Element tr ;

        public Row() {
            tr = new Element("tr");
            cols = new ArrayList<Cols>();
        }
        public void addCols(Cols col){
            tr.appendChild(col.td);
            cols.add(col);
        }
        public void setAttr(String s1,String s2){
            tr.attr(s1,s2);
        }

        /**
         * 创建新的Row
         * @param content
         * @return
         */
        public Row createRow(String content){
            Row row = new Row();
            Row.Cols cols = new Row.Cols(content);
            row.addCols(cols);
            return row;
        }

        /**
         * td
         */
        public static class Cols{
            public Element td;
            public Cols(){
                td = new Element("td");
            }
            public Cols(String content){
                td = new Element("td");
                this.addContent(content);
            }
            public void addContent(String content){
                td.append(content);
            }

            public Cols createCols(String content){
                Cols cols = new Cols();
                cols.addContent(content);
                return cols;
            }
            public void setAttr(String s1,String s2){
                td.attr(s1,s2);
            }
        }
    }

    public void printTable(){
        System.out.println(table);
    }


}
