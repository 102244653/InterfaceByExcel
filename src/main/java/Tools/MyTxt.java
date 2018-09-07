package Tools;

import java.io.*;

/**
 * Created by Administrator on 2017/11/8.
 */
public class MyTxt {
    final static LoggerControler log = LoggerControler.getLogger(MyTxt.class);
    //读取
    public static String readtxt(String path){
        String text="拣货单号";
        if(new MyFile().fileExists(path)==false){
            log.error("文件"+path+"不存在");
            return text;
        }
        try {
            File filename = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            if (line != null) {
                text = line;
                log.info("读取成功："+text);
            }
            br.close();
        }catch (Exception e){
            log.error("读取失败："+text);
            e.printStackTrace();
        }
        return text;
    }

    //写入
    public static void writetxt(String path,String text){
        new MyFile().creatFile(path);
        try {
            File filename = new File(path);
            //覆盖
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            out.write(text);
            log.info("写入成功："+text);
            out.flush();
            out.close();
        }catch (Exception e){
            log.error("写入失败："+text);
            e.printStackTrace();
        }
    }

    public static void addtxt(String path,String text){
        new MyFile().creatFile(path);
        try {
            File filename = new File(path);
            //追加
            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
            out.write(text);
            log.info("写入成功：" + text);
            out.flush();
            out.close();
        }catch (Exception e){
            log.error("写入失败："+text);
            e.printStackTrace();
        }
    }


}
