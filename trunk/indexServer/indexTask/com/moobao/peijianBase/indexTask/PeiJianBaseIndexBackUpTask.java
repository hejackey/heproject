package com.moobao.peijianBase.indexTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.moobao.config.PropertyConfiguration;
import com.moobao.phone.indexTask.PhoneIndexBackUpTask;

/**
 * 配件索引文件的备份.
 * @author liuxueyong
 */
public class PeiJianBaseIndexBackUpTask {
	//获取配件索引存放的位置.
	private static final String indexPath = PropertyConfiguration.getPeiJianBaseIndexPath();
	private static final String indexCopyPath = PropertyConfiguration.getPeiJianBaseBackUpIndexPath();
	
	//递归删除目录下的文件.
	static public void deleteDirectory(File dir) throws IOException {
	    if( (dir == null) || !dir.isDirectory() ) {
	        throw new IllegalArgumentException(
	                  "Argument "+dir+" is not a directory. "
	              );
	    }

	    File[ ] entries = dir.listFiles( );
	    int sz = entries.length;

	    for(int i=0; i<sz; i++) {
	        if(entries[i].isDirectory( )) {
	            deleteDirectory(entries[i]);
	        } else {
	            entries[i].delete();
	        }
	    }
	   //dir.delete();
	}		
	
	/**
     * 索引文件夹备份.
     * 原索引文件路径即为app.properties中peiJianIndex路径
     * @param indexPath String 原索引文件路径
     * @param newPath String 目标文件路径
     * @return boolean
     */
    public void copyIndex( String indexPath, String newPath ) {
    	
    	SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		String date = d.format(new Date()) ;
    	
    	try {
    		File dir = new File( newPath + date );
    		if( dir.exists() ) {
    			PhoneIndexBackUpTask.deleteDirectory( dir );
    		}
		} catch (IOException e) {
			System.out.println( "删除目录文件出错!" );
			e.printStackTrace();
		}
    	
    	try {
    		( new File( newPath + date ) ).mkdirs(); //如果文件夹不存在 则建立新文件夹
    		File a = new File( indexPath );
            String[] file = a.list();
            File temp = null;
            for ( int i = 0; i < file.length; i++ ) {
            	if( indexPath.endsWith( File.separator ) ) {
            		temp = new File( indexPath + file[i] );
            	}
            	else {
            		temp = new File( indexPath + File.separator + file[i] );
            	}
            	
            	if( temp.isFile() ) {
            		try {
            			FileInputStream input = new FileInputStream( temp );
                		FileOutputStream output = new FileOutputStream( newPath + date + "/" +
                				( temp.getName() ).toString() );
                		byte[] b = new byte[1024 * 5];
                		int len;
                		while ( ( len = input.read( b ) ) != -1) {
                			output.write( b, 0, len );
                		}  
                		output.flush();       
                		output.close();
                		input.close();
            		}
            		catch( FileNotFoundException ex ) {
            			ex.printStackTrace();
            		}
            	}
            	if( temp.isDirectory() ) {//如果是子文件夹 
            		copyIndex( indexPath + "/" + file[i], newPath + "/" + file[i] ); 
            	} 
            }
    	}
    	catch ( Exception e ) {  
    		System.out.println( "复制整个文件夹内容操作出错!" );  
    		e.printStackTrace(); 
    	} 
    }
}
