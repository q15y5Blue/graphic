package yqius.dataDeal.excelData;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.*;

/**
 * HSSF biff8
 * XSSF 2007以后
 */
public class SXSSFMethod {
    public static void main(String[] args) {
        try{
            InputStream input = new FileInputStream("workbook.xlsx");
            Workbook wb =WorkbookFactory.create(input);
            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(5);
            Cell cell =row.getCell(10);
            if(cell==null){
                cell = row.createCell(3);
            }
            System.out.println(cell);
            cell.setCellType(CellType.STRING);
            cell.setCellValue("test");
            OutputStream outputStream = new FileOutputStream("workbook.xlsx");
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
