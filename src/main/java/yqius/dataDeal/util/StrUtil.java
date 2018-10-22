package yqius.dataDeal.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StrUtil {

    public static String trimStr(String str){
        return str==null?"":str.trim();
    }

    /**
     * 输入字符串 和 split("regex") 返回list
     * Arrays.asList 返回Arrays的匿名内部类ArrayList 不能进行添加删除功能
     * @param arrs
     * @param regex
     * @return
     */
    public static List arrayToListSplit(String arrs,String regex){
        ArrayList<String> arrLi = new ArrayList<>();
        for(String s:arrs.split(regex)){
            arrLi.add(s);
        }
        return arrLi;
    }

    public static String toGBK(String s){
        try {
            return new String(s.getBytes("GBK"), "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
