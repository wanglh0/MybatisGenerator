package com.infohold.smallpay.map.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class PictureUtil {
    private static Font font = new Font("微软雅黑", Font.PLAIN, 15);                     //水印字体
    /**
     * 将两张图片拼接一张
     * @param url3   拼接后图地址
     * @throws Exception
     */
    public static Boolean JoinTwo(byte[] b1, byte[] b2, String url3,String waterMarkContent) throws Exception {
        FileOutputStream out=null;
        try {
            //创建两个文件对象（注：这里两张图片的宽度都是相同的，因此下文定义BufferedImage宽度就取第一只的宽度就行了）
           /* File _file1 = new File(url1);
            File _file2 = new File(url2);*/
            InputStream in1=new ByteArrayInputStream(b1);
            InputStream in2=new ByteArrayInputStream(b2);

            Image src1 = ImageIO.read(in1);
            Image src2 = ImageIO.read(in2);



            //获取图片的宽度
            int width = src1.getWidth(null);
            int width1 = src2.getWidth(null);
            if(width != width1){
                System.out.println("两张图片宽度不一致");
                return false;
            }
            //获取各个图像的高度
//            int height1 = src1.getHeight(null);
//            int height2 = src2.getHeight(null);
            //固定的宽高
            int w=704;
            int h=576;


            //构造一个类型为预定义图像类型之一的 BufferedImage。 宽度为第一只的宽度，高度为各个图片高度之和
//            BufferedImage tag = new BufferedImage(width, height1 + height2, BufferedImage.TYPE_INT_RGB);
            BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            //创建输出流
            File f=new File(url3);
            if(!f.getParentFile().exists()){
                    f.getParentFile().mkdirs();
            }
             out = new FileOutputStream(url3);
            //绘制合成图像
            Graphics2D graphics = tag.createGraphics();
//            graphics.drawImage(src1, 0, 0, width, height1, null);
//            graphics.drawImage(src2, 0, height1, width, height2, null);
            graphics.drawImage(src1, 0, 0, w, h/2, null);
            graphics.drawImage(src2, 0, h/2, w, h/2, null);

            graphics.setColor(Color.blue);
            graphics.setFont(font);
            String[] split = waterMarkContent.split(";");
            for (int i = 0; i < split.length; i++) {
//                graphics.drawString(split[i],100,height1+height2-100*(i+1));
               graphics.drawString(split[i],30,h-25*(i+1));
            }


            // 释放此图形的上下文以及它使用的所有系统资源。
            graphics.dispose();
            //将绘制的图像生成至输出流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
           return true;
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            //关闭输出流
            out.close();
        }
        return false;
    }


    /**
     * 添加水印
     * @param srcImgPath
     * @param tarImgPath
     * @param waterMarkContent
     * @param markContentColor
     * @param font
     */
    public static void addWaterMark(String srcImgPath, String tarImgPath, String waterMarkContent,Color markContentColor,Font font) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //根据图片的背景设置水印颜色
            g.setFont(font);              //设置字体

            //设置水印的坐标
            int x = srcImgWidth - 2*getWatermarkLength(waterMarkContent, g);
            int y = srcImgHeight - 2*getWatermarkLength(waterMarkContent, g);
            g.drawString(waterMarkContent, x, y);  //画出水印
            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }


    /**
     * 测试添加水印
     * @param args
     */
    public static void main(String[] args) {
        Font font = new Font("微软雅黑", Font.PLAIN, 35);                     //水印字体
        String srcImgPath="H:/安静时写写/写写博客/Java实现给图片添加水印/s.jpg"; //源图片地址
        String tarImgPath="H:/安静时写写/写写博客/Java实现给图片添加水印/t.jpg"; //待存储的地址
        String waterMarkContent="图片来源：http://blog.csdn.net/zjq_1314520";  //水印内容
        Color color=new Color(255,255,255,128);                               //水印图片色彩以及透明度
        new PictureUtil().addWaterMark(srcImgPath, tarImgPath, waterMarkContent,color ,font);

    }
}
