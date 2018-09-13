package yqius.dataDeal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrUtil {
    public static String trimStr(String str){
        return str==null?"":str.trim();
    }

//    public static void main(String[] args) {
//        String str ="【办公费】办公用品,【交通费】市内交通费,【邮电费】IP电话费,【邮电费】邮寄费";
//        List<String> list= Arrays.asList(str.split(","));
//        System.out.println(list);
//        for(String strs :list){
//            System.out.println(strs);
//        }
//
//    }

    /**
     * 输入字符串 和 spilit("regex") 返回list
     * @param arrs
     * @param regex
     * @return
     */
    public static List arrayToListSplit(String arrs,String regex){
        List<String> list = Arrays.asList(arrs.split(regex));//问题在这里
        return list;
    }
}
