package yqius.dataDeal.excelData;

import org.apache.poi.ss.usermodel.*;
import java.io.*;

/**
 * HSSF biff8
 * XSSF 2007以后
 */
public class FileMethod {
    public String PATH = "./datas/workbook.xlsx";
    public FileMethod(String path){
        if(path!=""&&!path.equals("")){
            this.PATH=path;
        }
    }

    /**
     * XSSFMethod 默认都是这个读取方式
     * 文件输入，一般是excel文件
     * @return
     */
    public  synchronized Workbook readExcel() {
        Workbook wb = null;
        try {
            InputStream input = new FileInputStream(this.PATH);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(outputStreamWriter==null) {
                        outputStreamWriter.close();
                    }
                    if(fileWriter==null){
                        fileWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
