package yqius.dataDeal.excelData.entity;

import org.jsoup.nodes.Element;
import java.util.ArrayList;
import java.util.List;

public class Tables {
    private List<Row> rows = new ArrayList<Row>();
    private Element table;

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
        table.attr(s1,s);
    }

    /**
     * Tr
     */
    public static class Row{
        List<Cols> cols;
        Element tr ;

        public Row() {
            tr = new Element("tr");
            cols = new ArrayList<Cols>();
        }
        public void addCols(Cols col){
            tr.appendChild(col.td);
            cols.add(col);
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
            Element td;
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
        }
    }

    public void printTable(){
        System.out.println(table);
    }
    public static void main(String[] args) {
//        Row row =new Row();
//        Row.Cols col = new Row.Cols();
//        col.addContent("hahah");
//        row.addCols(col);
//        Tables ta = new Tables();
//
//        ta.addRows(row);
//        System.out.println(ta);
//        System.out.println();
    }

}
