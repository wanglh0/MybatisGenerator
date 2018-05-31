package com.infohold.smallpay.map.util;

import java.io.*;

public class FileUtils {
    /**
     * 获得指定文件的byte数组
     * @param filePath 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(String filePath){
        ByteArrayOutputStream bos=null;
        BufferedInputStream in=null;
        try{
            File file=new File(filePath);
            if(!file.exists()){
                throw new FileNotFoundException("file not exists");
            }
            bos=new ByteArrayOutputStream((int)file.length());
            in=new BufferedInputStream(new FileInputStream(file));
            int buf_size=1024;
            byte[] buffer=new byte[buf_size];
            int len=0;
            while(-1 != (len=in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                if(in!=null){
                    in.close();
                }
                if(bos!=null){
                    bos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据byte数组，生成文件
     * @param bfile 文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static void byte2File(byte[] bfile,String filePath,String fileName){
        BufferedOutputStream bos=null;
        FileOutputStream fos=null;
        File file=null;
        try{
            File dir=new File(filePath);
            if(!dir.exists()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file=new File(filePath+fileName);
            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            bos.write(bfile);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtils.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtils.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
}
