package yqius.dataDeal.general;

import yqius.dataDeal.entity.Category;
import yqius.dataDeal.util.StrUtil;
import yqius.util.db.ConnectionPool;
import yqius.util.db.ConnectionPoolManager;
import yqius.util.db.dboperation.SelectImpl;
import yqius.util.io.FileMethod;

import java.io.UnsupportedEncodingException;
import java.sql.SQLOutput;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryExecute {
    public void execute()  {
        FileMethod fm = new FileMethod();
        StringBuffer sb=fm.readFile("C:\\Users\\admin\\IdeaProjects\\graph\\datas\\xxxx.txt");
        String[] arrStr = sb.toString().split("\n");
        String parr = "^\\d{1,}";
        Pattern pattern = Pattern.compile(parr);
        ConnectionPool pool = ConnectionPoolManager.getPool("CMServer");//
        for(int i=0;i<arrStr.length;i++){
            String str = arrStr[i];
            //  System.out.println(new String(str.getBytes("GBK"),"GBK"));
//            System.out.println(str);
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
                String cateGoryName = StrUtil.trimStr(str.replace(matcher.group(0),""));
                if(cateGoryName.length()>15){
                   cate.setCategoryname("其他");
                }else {
                    cate.setCategoryname(cateGoryName);
                }
                cate.setShortname("");
                System.out.println(cate);
                this.insertCate(cate);
                //cate Get
                    System.out.println("成功插入了:"+i);
            }
        }
    }

    public void insertCate(Category cate){
        SelectImpl si = new SelectImpl();
        String sql = null;
            sql = String.format("insert into shp_category (categoryid,categoryname,levels,parents,shortname) values('%d','%s','%d','%d','%s')",
                    cate.getCategoryid(), StrUtil.toGBK(cate.getCategoryname()),cate.getLevels(),cate.getParents(),cate.getShortname());
        si.insertData(sql);
        System.out.println("insert success");
    }

    public void delteData(){
        SelectImpl si = new SelectImpl();
        String sql = "delete from shp_category";
        si.delete(sql);
        System.out.println("delete success");
    }


}
