package yqius.util.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileMethod {
    /**
     * readFileBy FileReader
     * @param filePath
     * @return
     */
    public StringBuffer readFile(String filePath){
        StringBuffer stringBuffer=new StringBuffer();
        FileReader reader= null;
        BufferedReader bufferedReader =null;
        try {
            reader= new FileReader(filePath);
            bufferedReader = new BufferedReader(reader);
            String str=null;
            while((str = bufferedReader.readLine()) != null){
                stringBuffer.append(str+"\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuffer;
    }

//    public static void main(String[] args) {
//
//        System.out.println( new FileMethod().readFile("./datas/xxxx.txt"));
//    }
}
