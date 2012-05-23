package com.liu.index;

import org.apache.lucene.index.IndexWriter;

import com.moobao.analisis.AnalysisFactory;

public class ProductIndexer {

	private String indexPath = "";
	
	private IndexWriter writer = null;
	


	public ProductIndexer(String indexPath) throws Exception {
		this.indexPath = indexPath;
		initialize();
	}

	/**
	 * @throws Exception
	 */
	private void initialize() throws Exception {

		writer = new IndexWriter(indexPath, AnalysisFactory.getIndexingAnalisis(), true);

	}
	
	public void close() {
		try{
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
			writer = null;
		}
	}
	
	public void addProduct() throws Exception {
		writer.setUseCompoundFile(true);// 采用复合索引格式
		writer.addDocument(ProductDocument.buildProductDocument());
	}
	
	public void optimizeIndex() throws Exception {
		writer.optimize();
	}
	
	public static void main( String[] args ) {
		try {
			ProductIndexer pi = new ProductIndexer("E:\\index1");
			pi.addProduct();
		    pi.optimizeIndex();
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
