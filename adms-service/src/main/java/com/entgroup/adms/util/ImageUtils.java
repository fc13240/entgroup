/**
 * @Project: adms-service  
 * @Title: ImageUtils.java
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: mqc
 * @Date: 2016-8-10 下午5:30:05   
 * @Version: V1.0
 */
package com.entgroup.adms.util;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import com.sun.image.codec.jpeg.*;

import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ImageUtils
 * @Description: 图片工具类
 * @Author: mqc
 * @Date: 2016-8-10 下午5:30:05
 */
public class ImageUtils {

    /**
     * 根据文件获取bufferedImage
     * <p>兼容ImageIO读取存在变色问题</p>
     *
     * @param file
     * @return
     */
    public static BufferedImage getBufferedImage(java.io.File file) {
        return getBufferedImage(file.getAbsolutePath());
    }

    /**
     * 根据文件完整地址获取bufferedImage
     * <p>兼容ImageIO读取存在变色问题</p>
     *
     * @param fileAbPath
     * @return
     */
    public static BufferedImage getBufferedImage(String fileAbPath) {
        ImageIcon src = new ImageIcon(fileAbPath);
        BufferedImage bufferedImage = new BufferedImage(src.getIconWidth(),
                src.getIconHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, src.getIconWidth(), src.getIconHeight());
        g.drawImage(src.getImage(), 0, 0, null);
        g.dispose();
        return bufferedImage;
    }

    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * @throws
     * @Title: ImageUtils
     * @param: @param inputImagePath         源路径
     * @param: @param outputImagePath        目标路径
     * @param: @param markContent            水印文字
     * @param: @param markContentColor       水印文字颜色
     * @param: @param qualNum                水印质量
     * @param: @param markFontType           水印文字字体
     * @param: @param markFontSize           水印文字大小
     * @param: @param width                  水印宽度
     * @param: @param height                 水印高度
     * @param: @return
     * @return: boolean                      是否成功
     * @Creator: mqc
     * @Description: 为图片添加文字水印
     * @Date: 2016-8-10 下午5:53:49
     * @Modifier:
     * @Date:
     * @Remark:
     * @Version: V1.0
     */
    @SuppressWarnings("restriction")
    public static boolean addMark4Pic(String inputImagePath, String outputImagePath,
                                      String markContent, Color markContentColor, float qualNum,
                                      String markFontType, int markFontSize, int width, int height) {

        boolean isSuccess = false;

		/* 构建要处理的源图片 */
        ImageIcon imageIcon = new ImageIcon(inputImagePath);

		/* 获取要处理的图片 */
        Image image = imageIcon.getImage();

        // Image可以获得图片的属性信息
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);

        // 为画出与源图片的相同大小的图片（可以自己定义）
        BufferedImage bImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // 构建2D画笔
        Graphics2D g = bImage.createGraphics();

		/* 设置2D画笔的画出的文字颜色 */
        g.setColor(markContentColor);

		/* 设置2D画笔的画出的文字背景色 */
//		g.setBackground(Color.white);

		/* 画出图片 */
        g.drawImage(image, 0, 0, null);

		/* --------对要显示的文字进行处理-------------- */
        AttributedString ats = new AttributedString(markContent);
        Font font = new Font(markFontType, Font.BOLD, markFontSize);
        g.setFont(font);

		/* 消除java.awt.Font字体的锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		/* 消除java.awt.Font字体的锯齿 */
        // font = g.getFont().deriveFont(30.0f);
        ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
        AttributedCharacterIterator iter = ats.getIterator();
		
		/* 添加水印的文字和设置水印文字出现的内容 ----位置 */
        g.drawString(iter, imageWidth - width, imageHeight - height);
		/* --------对要显示的文字进行处理-------------- www.it165.net */
        g.dispose();// 画笔结束

        try {
            // 输出 文件 到指定的路径
            FileOutputStream out = new FileOutputStream(outputImagePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
            param.setQuality(qualNum, true);
            encoder.encode(bImage, param);
            out.close();

            isSuccess = true;

        } catch (Exception e) {
            log.error("为图片设置文字水印失败......{}", e.getMessage());
        }

        return isSuccess;
    }

}
