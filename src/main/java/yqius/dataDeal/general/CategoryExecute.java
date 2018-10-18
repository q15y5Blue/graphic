package yqius.dataDeal.general;

import yqius.util.io.FileMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryExecute {
    public void execute(){
        FileMethod fm = new FileMethod();
        StringBuffer sb=fm.readFile("./datas/xxxx.txt");
        String[] arrStr = sb.toString().split("\n");
        String parr = "\\d{1,}";
        Pattern pattern = Pattern.compile(parr);
        for(int i=0;i<arrStr.length;i++){
            String str = arrStr[i];
            System.out.println(str);
            Matcher matcher = pattern.matcher(str);
            System.out.println(matcher);
        }

    }

    public static void main(String[] args) {
        CategoryExecute ce = new CategoryExecute();
        ce.execute();
    }
}
