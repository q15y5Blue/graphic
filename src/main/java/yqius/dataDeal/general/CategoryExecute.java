package yqius.dataDeal.general;

import yqius.dataDeal.entity.Category;
import yqius.dataDeal.util.StrUtil;
import yqius.util.io.FileMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryExecute {
    public void execute(){
        FileMethod fm = new FileMethod();
        StringBuffer sb=fm.readFile("./datas/xxxx.txt");
        String[] arrStr = sb.toString().split("\n");
        String parr = "^\\d{1,}";
        Pattern pattern = Pattern.compile(parr);
        for(int i=0;i<arrStr.length;i++){
            String str = arrStr[i];
            System.out.println(str);
            Matcher matcher = pattern.matcher(str);
            if(matcher.find()){
                Category cate = new Category();

                Integer cateId = Integer.parseInt(StrUtil.trimStr(matcher.group(0)));
                if(cateId>9999&&cateId<9999999){
                    cate.setLevels(3);
                    cate.setParents(cateId/100);
                }else if(cateId>=9999999){
                    cate.setLevels(4);
                    cate.setParents(cateId/100);
                }else {
                    cate.setLevels(2);
                    cate.setParents(cateId/100);
                }
                cate.setCategoryid(cateId);

                str = str.replace(matcher.group(0),"");
//                String cateGoryName = cate.setCategoryname();
//                if(cateGoryName.length()>20){
//                    cate.setCategoryname();
//                }
//                cate.setCategoryname();

                cate.setShortname("");
//                System.out.println(cate);
            }

        }

    }

    public static void main(String[] args) {
        CategoryExecute ce = new CategoryExecute();
        ce.execute();
        System.out.println(22040104/100);
    }
}
