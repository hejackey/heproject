package com.apply.b2b.cms.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 
 * @author luoweifeng
 * 
 */
public class FileManager {
	
	/**
	 * @param fileName
	 * @param fileContent
	 * @throws IOException
	 */
	public boolean writeUTF8File(String fileName, String fileContent)
			throws IOException {
		if (fileName != null && !fileName.trim().equals("")) {
			if (fileContent != null) {
				if (fileName.lastIndexOf(File.separator) > 0) {
					File dfile = new File(fileName.substring(0, fileName
							.lastIndexOf(File.separator)));
					if (!dfile.exists())
						dfile.mkdirs();
					
					File file = new File(fileName);
					
					Writer writer = new OutputStreamWriter(new FileOutputStream(
							file), "UTF-8");
					
					writer.write(fileContent);
					writer.flush();
					writer.close();
					return true;
				}else{
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public  long getFileLastModified(String dir_path)
			throws FileNotFoundException {
		
		File file = new File(dir_path);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}
		if (file.isDirectory()) {
			throw new FileNotFoundException();
		}
		return file.lastModified();
	}

	public  long getSubDirNum(String dir_path, FileFilter fileFilter)
			throws FileNotFoundException {

		long fileNme = 0;
		File dir = new File(dir_path);

		if (!dir.exists()) {
			throw new FileNotFoundException();
		}

		if (!dir.isDirectory()) {
			return 0;
		} else {
			File[] fe = dir.listFiles(fileFilter);

			for (int i = 0; i < fe.length; i++) {
				if (fe[i].isDirectory()) {
					fileNme = fileNme + 1
							+ getSubDirNum(fe[i].toString(), fileFilter);
				}
			}
		}
		return fileNme;
	}

	public  long getFileNum(String dir_path, FileFilter fileFilter)
			throws FileNotFoundException {

		long fileNme = 0;
		File dir = new File(dir_path);

		if (!dir.exists()) {
			throw new FileNotFoundException();
		}

		if (!dir.isDirectory()) {
			return 1;
		} else {
			File[] fe = dir.listFiles(fileFilter);

			for (int i = 0; i < fe.length; i++) {
				if (fe[i].isDirectory()) {
					fileNme += getFileNum(fe[i].toString(), fileFilter);
				} else {
					fileNme += 1;
				}
			}
		}
		return fileNme;
	}

	public boolean fileExists(String dir_path){
		if(dir_path != null && dir_path.trim().length() > 0){
			File dir = new File(dir_path);
			if (dir.exists()){
				return dir.isFile();
			} else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public synchronized long getSize(String dir_path, FileFilter fileFilter)
			throws FileNotFoundException {

		long dir_size = 0;
		File dir = new File(dir_path);

		if (!dir.exists()) {
			throw new FileNotFoundException();
		}

		if (!dir.isDirectory()) {
			return dir.length();
		} else {
			File[] fe = dir.listFiles(fileFilter);

			for (int i = 0; i < fe.length; i++) {
				if (fe[i].isDirectory()) {
					dir_size += getSize(fe[i].toString(), fileFilter);
				} else {
					dir_size += fe[i].length();
				}
			}
		}
		return dir_size;
	}
    
	public synchronized void deleteFiles(String dir_path)
			throws FileNotFoundException {

		File file = new File(dir_path);
		if (!file.exists()) {
			throw new FileNotFoundException();
		}

		if (file.isDirectory()) {
			File[] fe = file.listFiles();
			for (int i = 0; i < fe.length; i++) {
				deleteFiles(fe[i].toString());
				fe[i].delete();
			}
		}
		file.delete();
	}

	public static void main(String[] args) {
		FileManager fileManager = new FileManager();
		try {
			System.out.println(fileManager.getSubDirNum("F:/server/cms_b2b",
					null));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Process all files and directories under dir
	public void visitAllDirsAndFiles(File dir, FileManagerProcess processor) {
		processor.process(dir);

		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirsAndFiles(new File(dir, children[i]), processor);
			}
		}
	}

	// Process only directories under dir
	public void visitAllDirs(File dir, FileManagerProcess processor) {
		if (dir.isDirectory()) {
			processor.process(dir);

			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllDirs(new File(dir, children[i]), processor);
			}
		}
	}
	
	// Process only files under dir
	public void visitAllFiles(File dir, FileManagerProcess processor) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				visitAllFiles(new File(dir, children[i]), processor);
			}
		} else {
			processor.process(dir);
		}
	}
}
