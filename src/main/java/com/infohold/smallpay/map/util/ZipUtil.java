package com.infohold.smallpay.map.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.util.ArrayList;


public class ZipUtil {

    /**
     * 生成zip文件
     * @param filepath  文件名
     * @param basePath  文件路径
     * @param filename  压缩成zip的名字
     * @throws Exception
     */
    public static void generateZipFile(String[] filepath,String basePath,String filename) throws Exception{
        ZipFile zipFile = new ZipFile(filename);
        ArrayList<File> filesToAdd = new ArrayList<File>();
        if(filepath.length >0){
            for(String str : filepath){
                filesToAdd.add(new File(basePath+str));
            }
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            /*parameters.setRootFolderInZip("test2/");*/
            zipFile.addFiles(filesToAdd, parameters);
        }
    }


    /**
     * 生成压缩文件
     * @param basePath   压缩文件夹的路径名
     * @param filename   压缩后的名字
     * @throws Exception
     */
    public static void generateZipFile(String basePath,String filename) throws Exception{
        File file=new File(basePath);
        if(!file.exists()){
            return;
        }
        ZipFile zipFile = new ZipFile(filename);
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        zipFile.addFolder(basePath,parameters);
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        generateZipFile(new String[]{"/20170614/1.png","/20170614/2.png"},"D:\\webFile\\mingpai\\","测试.zip");
        //file.deleteOnExit();
    }


}
