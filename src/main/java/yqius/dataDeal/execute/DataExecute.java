package yqius.dataDeal.execute;

import yqius.dataDeal.entity.Child;
import yqius.dataDeal.util.ConnectionPoolManager;
import yqius.dataDeal.util.StrUtil;

import java.util.*;

public class DataExecute {

    /**
     * 数据来源于数据库
     * xtype b_name count 三个字段,统计数据
     * @return
     */
    public List<Child> selectList(){
        String sql = "select xtype,b_name,count(*) as count from invoice2018 A, YB_BNAMES B " +
                "where A.xtype is not null AND A.req_no=B.SERIAL_NO group by xtype,b_name  order by xtype,b_name ";
        ConnectionPoolManager cpm= new ConnectionPoolManager();
        List list = cpm.selectArray(sql,Child.class);
        return list;
    }

    public void dealWithData(List<Child> dataList){
        List list = new ArrayList();
        HashMap<String,List<Child>> hashMap = new HashMap<String,List<Child>>();
        for(Child li:dataList){
            if(!hashMap.containsKey(li.getXtype())){
                List childList =new ArrayList<Child>();
                childList.add(li);
                hashMap.put(li.getXtype(),childList);
            }else{
                List existList =hashMap.get(li.getXtype());
                existList.add(li);
                hashMap.put(li.getXtype(),existList);
            }
        }
        this.countType(hashMap);
//        System.out.println(hashMap);
//        return hashMap;
    }

    //大类HashMap
    public HashMap countType(HashMap<String,List<Child>> hashMap){
        HashMap<String,List> newHash = new HashMap<String,List>();
        for(Map.Entry<String,List<Child>> entry:hashMap.entrySet()){
            System.out.println("________________________________");
            System.out.println("类别"+entry.getKey());
            newHash.put(entry.getKey(),this.countList(entry.getValue()));
        }
//        System.out.println("new Hash");
//        System.out.println(newHash);
        return newHash;
    }

    public List<HashMap> countList(List<Child> li){
        List<HashMap> list =  new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        for(Child child:li){
           for(String str:child.getB_nameList()){
                if(!hashMap.containsKey(str)){
                    hashMap.put(str,child.getCount());
                }else{
                    Integer tempCount = hashMap.get(str);
                    hashMap.put(str,tempCount+child.getCount());
                }
           }
        }
        list =this.sortHashByValue(hashMap);
        System.out.println(list);
        //需要加一层hash排序
        return list;
    }

    /**
     * 排序map
     * @param hashMap
     * @return
     */
    public List sortHashByValue(HashMap hashMap){
        List<Map.Entry<String,Integer>> list=new ArrayList<>();
        list.addAll(hashMap.entrySet());
        DataExecute.ValueCompare vs = new ValueCompare();
        Collections.sort(list,vs);
        return list;
    }

    private static class ValueCompare implements Comparator<Map.Entry<String,Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue()-o1.getValue();
        }
    }

    public static void main(String[] args) {
        DataExecute de = new DataExecute();
        List<Child> dataList = de.selectList();
        de.dealWithData(dataList);

    }



}
