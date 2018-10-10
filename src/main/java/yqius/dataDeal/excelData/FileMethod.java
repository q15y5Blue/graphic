package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import java.io.*;

/**
 * 输入xlsx
 * 输出html
 * 文件操作类
 * Excel文件 基本读写操作的封装
 * XSSF 2007以后
 */
public class FileMethod {
//    public static String PATH = "./datas/workbook.xlsx";
//    public static String PATHOUT = "./datas/workbook.html";
    public FileMethod(){
    }

    /**
     * XSSFMethod 默认都是这个读取方式
     * 文件输入，一般是excel文件
     * @return
     */
    public  synchronized Workbook readExcel() {
        Workbook wb = null;
        try {
            InputStream input = new FileInputStream(Constant.INPATH);
            wb = WorkbookFactory.create(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     * 文件输出包装，任何路径
     */
    public synchronized void writeSteam(String outPath,String str){
        OutputStreamWriter outputStreamWriter =null;
        FileOutputStream fileWriter = null;
        try {
            fileWriter = new FileOutputStream(outPath);
            outputStreamWriter = new OutputStreamWriter(fileWriter);
            outputStreamWriter.write(str);
            outputStreamWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStreamWriter!=null){
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
