package com.example.henryzheng.qiushibaike.M.utils;

import android.util.Log;

import com.example.henryzheng.qiushibaike.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by henryzheng on 2017/1/24.
 */
public class FileUtils {
    public static void saveObject(String fileName, Object o) {
        ObjectInputStream ois = null;
        try {
            ObjectOutputStream fos = null;
            try {

                //如果文件不存在就创建文件
                File file = new File(Config.esternCacheDir + "/" +
                        fileName);
                file.deleteOnExit();
                //file.createNewFile();
                //获取输出流
                //这里如果文件不存在会创建文件，这是写文件和读文件不同的地方
                fos = new ObjectOutputStream(new FileOutputStream(file));
                //获取输入框内的文件进行写入

                //这里不能再用普通的write的方法了
                //要使用writeObject
                fos.writeObject(o);
                ;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //读取数据
    public static Object read(String fileName) {
        ObjectInputStream ois = null;
        Object o = null;
        try {
            Log.e("TAG", new File(Config.esternCacheDir+ "/" + fileName)
                    .getAbsolutePath() + "<---");
            //获取输入流
            ois = new ObjectInputStream(new FileInputStream(new File(MyApplication.getContext()
                    .getCacheDir() + "/" + fileName)));
            //获取文件中的数据
             o = ois.readObject();

            //把数据显示在TextView中
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                    return o;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return o;
    }

}
