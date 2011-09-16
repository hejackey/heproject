package com.bfb.commonutil.barcode4j;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.tools.MimeTypes;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
/**
 * 不支持中文的二维码生成
 * @author Administrator
 *
 */
public class BarCodeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4370497541334321517L;

	private static DefaultConfiguration cfg;  
	
	/** 
     * 二维码参数 
     */  
    private static final String FORMAT = MimeTypes.MIME_JPEG;  
    private static final int ORIENTATION = 0;  
    private static final int RESOLUTION = 300;  
    private static final String BARCODE_TYPE = "datamatrix";  
	 
	public void doGet(HttpServletRequest req,HttpServletResponse res){
		/*cfg = new DefaultConfiguration("barcode");  
        DefaultConfiguration child = new DefaultConfiguration(BARCODE_TYPE );  
  
        cfg.addChild(child);  
  
        DefaultConfiguration attr;  
  
        attr = new DefaultConfiguration("height");  
        attr.setValue(50);  
        child.addChild(attr);  
  
        attr = new DefaultConfiguration("module-width");  
        attr.setValue("0.6");  
        child.addChild(attr);  
        
        ByteArrayOutputStream baos = null;  
        BitmapCanvasProvider bitmap = null;  
        try {  
            BarcodeUtil util = BarcodeUtil.getInstance();  
            BarcodeGenerator gen = util.createBarcodeGenerator(cfg);  
      
            baos = new ByteArrayOutputStream(4096);  
      
            bitmap = new BitmapCanvasProvider(baos, FORMAT, RESOLUTION, BufferedImage.TYPE_BYTE_GRAY, true, ORIENTATION);  
            
            String key = req.getParameter("barCodeKey");
            //String code = req.getParameter("code");
            String code = "1234sdf3424_sdf234234_1233";
            req.getSession().setAttribute("barcode_"+key, code);
            gen.generateBarcode(bitmap, code);  
            bitmap.finish();  
            
            res.getOutputStream().write(baos.toByteArray());
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (baos != null) {  
                    baos.close();  
                }  
                bitmap = null;  
            } catch (Exception e) {  
            }  
        }  */
		
		try {
			generate(res.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		doGet(req,res);
	} 
	
	private static final int width = 200;
	private static final int height = 200;  
    public void generate(OutputStream outputStream) {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode
            ("123123asdfds",BarcodeFormat.QR_CODE, width, height);
            

            MatrixToImageWriter.writeToStream(matrix, "png", outputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
