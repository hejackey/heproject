package com.bfb.commons.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.asprise.util.ocr.OCR;

/**
 * 图片验证码识别程序，
 * AspriseOCR.dll、DevIL.dll、ILU.dll拷贝到windows/system32目录下
 * aspriseOCR.jar拷到classpath下，需要付费
 * @author Administrator
 * @version 1.0
 * @date 2012-1-11
 */
public class ParserImgWithOcr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "";

        try {
               BufferedImage image = ImageIO.read(new File("d:\\8f8login.jpg"));
               int width = image.getTileWidth();
               int height = image.getTileHeight();
               image = image.getSubimage(0, 0, width, height);
               
               s = new OCR().recognizeEverything(image);
               System.out.println(s);
        } catch (IOException e) {
               e.printStackTrace();
               System.out.println(" 图片识别失败！ ");
        }

	}

}
