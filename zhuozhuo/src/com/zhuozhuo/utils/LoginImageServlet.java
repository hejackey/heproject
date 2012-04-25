package com.zhuozhuo.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class LoginImageServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1498298068325751545L;

	/**
	 * Log4J Logger for this class
	 */
	private static final Logger logger =
		Logger.getLogger(LoginImageServlet.class);

	private static final String CONTENT_TYPE = "image/jpeg;charset=UTF-8";

	public LoginImageServlet(){
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (logger.isDebugEnabled()) {
			logger.debug(
				"doGet(HttpServletRequest request = "
					+ request
					+ ", HttpServletResponse response = "
					+ response
					+ ") - start");
		}

		doPost(request, response);

		if (logger.isDebugEnabled()) {
			logger.debug(
				"doGet(HttpServletRequest request = "
					+ request
					+ ", HttpServletResponse response = "
					+ response
					+ ") - end");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if (logger.isDebugEnabled()) {
			logger.debug(
				"doPost(HttpServletRequest request = "
					+ request
					+ ", HttpServletResponse response = "
					+ response
					+ ") - start");
		}

		try
		{
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0L);
			int width = 80;
			int height = 30;
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics g = image.getGraphics();
			Random random = new Random();
			g.setColor(getRandColor(200, 250));
			g.fillRect(0, 0, width, height);
			g.setFont(new Font("Times New Roman", 1, 20));
			g.setColor(new Color(255, 255, 255));
			g.drawRect(0, 0, width - 1, height - 1);
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 300; i++)
			{
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(15);
				int yl = random.nextInt(15);
				g.drawLine(x, y, x + xl, y + yl);
			}

			String sRand = "";
			Random newRandom = new Random();
			for (int i = 0; i < 4; i++){
				int k = newRandom.nextInt();
				int j = Math.abs(k % 10);
				// rand = RandomValue.getRandomValue();
				sRand = sRand + String.valueOf(j);
				g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
				g.drawString(String.valueOf(j), 16 * i + 12, 20);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("loginCheckImg", sRand);
			
			g.dispose();
			javax.servlet.ServletOutputStream outStream = response.getOutputStream();
			ImageIO.write(image, "JPEG", outStream);
			outStream.close();
		}catch (IOException e){
			logger.error(
				"doPost(HttpServletRequest request = "
					+ request
					+ ", HttpServletResponse response = "
					+ response
					+ ")",
				e);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug(
				"doPost(HttpServletRequest request = "
					+ request
					+ ", HttpServletResponse response = "
					+ response
					+ ") - end");
		}
	}

	public Color getRandColor(int fc, int bc){
		if (logger.isDebugEnabled()) {
			logger.debug(
				"getRandColor(int fc = "
					+ fc
					+ ", int bc = "
					+ bc
					+ ") - start");
		}

		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		Color returnColor = new Color(r, g, b);
		if (logger.isDebugEnabled()) {
			logger.debug(
				"getRandColor(int fc = " + fc + ", int bc = " + bc + ") - end");
		}
		return returnColor;
	}
	
	public void destroy(){
		if (logger.isDebugEnabled()) {
			logger.debug("destroy() - start");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("destroy() - end");
		}
	}
}
