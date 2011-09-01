package com.bfb.commons.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ApacheFtpUtil {
	private static FTPClient ftpClient;
	private static ApacheFtpUtil ftpUtil = null;
	private final static Logger log = Logger.getLogger(ApacheFtpUtil.class);

	/**
	 * 获取ApacheFtpUtil的实例
	 * 
	 * @param ip
	 *            ftpIp
	 * @param port
	 *            ftp端口
	 * @param userName
	 *            ftp用户名
	 * @param userPwd
	 *            ftp密码
	 * @param path
	 * @return
	 */
	public static synchronized ApacheFtpUtil getInstance(String ip, int port,
			String userName, String userPwd, String path) {
		connectServer(ip, port, userName, userPwd, path);
		if (ftpUtil == null)
			ftpUtil = new ApacheFtpUtil();
		return ftpUtil;
	}

	/**
	 * @param ip
	 *            ftp地址
	 * @param port
	 *            ftp端口
	 * @param userName
	 *            登录名
	 * @param userPwd
	 *            密码
	 * @param path
	 *            操作目录
	 * @throws SocketException
	 * @throws IOException
	 *             function:连接到服务器
	 */
	public static void connectServer(String ip, int port, String userName,
			String userPwd, String path) {
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(ip, port);// 连接
			ftpClient.login(userName, userPwd);// 登录
			if (!StringUtils.isEmpty(path) && path.length() > 0)
				ftpClient.changeWorkingDirectory(path);// 跳转到指定目录
		} catch (SocketException ex) {
			log.info(ex.getMessage());
		} catch (IOException ex) {
			log.info(ex.getMessage());
		}
	}

	/**
	 * 关闭ftpClient
	 */
	public void closeServer() {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException ex) {
				log.info(ex.getMessage());
			}
		}
	}

	/**
	 * ftp下载文件
	 * 
	 * @param fileName
	 *            ftp上文件名
	 * @param newfilename
	 *            本地重命名文件名
	 */
	public String downFile(String fileName, String newfilename) {
		log.info("download file start....");
		InputStream ins = null;
		BufferedWriter bw = null;
		BufferedReader reader = null;
		String inLine = null;
		try {
			ins = ftpClient.retrieveFileStream(fileName);
			if (ins == null)
				return null;
			reader = new BufferedReader(new InputStreamReader(ins, "gbk"));
			File outfile = new File(newfilename);
			bw = new BufferedWriter(new FileWriter(outfile));
			while ((inLine = reader.readLine()) != null) {
				if (!StringUtils.isEmpty(inLine)) {
					bw.flush();
					bw.write(inLine);
					bw.newLine();
				}
			}
			log.info("download file end....");
		} catch (IOException ex) {
			log.info(ex.getMessage());
		} finally {
			closeIo(ins, bw, reader);
			closeServer();
		}
		return newfilename;
	}

	/**
	 * 关闭io
	 * 
	 * @param ins
	 *            InputStream
	 * @param bw
	 *            BufferedWriter
	 * @param reader
	 *            BufferedReader
	 */
	public void closeIo(InputStream ins, BufferedWriter bw,
			BufferedReader reader) {
		try {
			if (reader != null)
				reader.close();
			if (ins != null)
				ins.close();
			if (bw != null)
				bw.close();
		} catch (IOException ex) {
			log.info(ex.getMessage());
		}

	}

	/**
	 * 获得指定目录下所有文件名
	 * 
	 * @param path
	 *            指定目录路径
	 * @return 目录下所有文件名
	 */
	public List<String> getFileList(String path) {
		List<String> fileLists = new ArrayList<String>();
		FTPFile[] ftpFiles = null;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName:
	 *            文件路径
	 */
	public void deleteFile(String fileName) {
		try {
			ftpClient.deleteFile(fileName);
		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}

	public static void main(String[] args) throws ParseException {
		ApacheFtpUtil ftp = ApacheFtpUtil.getInstance("test.com",
				21, "test", "test", "");

		DateTime dt = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMdd");
		String pre = dt.plusDays(-1).toString(fmt);

		System.out.println("开始下载支付成功对账文件：" + pre);
		ftp.downFile("20110608.csv", "d:/20110608.csv");
	}
}
