package yqius.dataDeal.execute;

import yqius.dataDeal.entity.Child;
import yqius.dataDeal.util.ConnectionPoolManager;
import yqius.dataDeal.util.StrUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataExecute {

    public List<Child> selectList(){
        String sql = "select xtype,b_name,count(*) as count from invoice2018 A, YB_BNAMES B where A.xtype is not null AND A.req_no=B.SERIAL_NO group by xtype,b_name  order by xtype,b_name ";
        ConnectionPoolManager cpm= new ConnectionPoolManager();
        List list = cpm.selectArray(sql,Child.class);
        System.out.println(list);
        return list;
    }

    public List dealWithData(List dataList){
        List list = new ArrayList();
        return list;
    }

    public static void main(String[] args) {
//       List<Child> dataList = new DataExecute().selectList();
//       HashMap<String,Child> hashMap = new HashMap<String,Child>();
//       for(Child li:dataList){
//            if(!hashMap.containsKey(li.getXtype())){
//                hashMap.put(li.getXtype(),li);
//            }else{
//                List tempList = li.getB_nameList();
//
//                Child hasli = hashMap.get(li.getXtype());
//                //这咋又个错啊？
//                System.out.println(tempList);
//                System.out.println(hasli.getB_nameList());
//                hasli.getB_nameList().addAll(1,tempList);
//                hashMap.put(li.getXtype(),hasli);
//            }
//       }
//        System.out.println(hashMap);
        new DataExecute().test();
    }

    public void test(){
    String str1 = "【交通费】市内交通费, 【专用材料购置费】实验材料购置, 【邮电费】邮寄";
    String str2 = "【交通费】社会车辆包车费, 【维修（护）费】电梯维修保养";
    List list1 = StrUtil.arrayToListSplit(str1,",");
    List list2 = StrUtil.arrayToListSplit(str2,",");

    list1.addAll(list2);

    }
}
