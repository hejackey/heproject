package com.moobao.index.console;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.moobao.config.PropertyConfiguration;
import com.moobao.peijian.indexTask.PeiJianDeleteIndexTask;
import com.moobao.peijian.indexTask.PeiJianIndexBackUpTask;
import com.moobao.peijian.indexTask.PeiJianOptimizeIndexTask;
import com.moobao.peijian.indexTask.PeiJianUpdateIndexTask;
import com.moobao.peijian.indexTask.PeijianCreateIndexTask;

/**
 * 配件索引控制.
 * @author liuxueyong
 *
 */
public class PeiJianIndexServlet extends HttpServlet {

	static Logger log4j = Logger.getLogger(PeiJianIndexServlet.class.getName());

	/**
	 * Constructor of the object.
	 */
	public PeiJianIndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RequestDispatcher rd = null;
		try {
			String infoType = request.getParameter("infotype");
			StringBuffer message = new StringBuffer();
			boolean flag = doIndex(infoType);
			if (flag) {
				message.append(infoType).append(" SUCCEED:");
			} else {
				message.append(infoType).append(" FAILED:");
			}
			rd = this.getServletContext().getRequestDispatcher(
					"/console/messages.jsp?MESSAGE=" + message.toString());

			rd.forward(request, response);
		} catch (Exception e) {
			StringBuffer message = new StringBuffer();
			message.append("索引操作失败").append(" FAILED");
			rd = this.getServletContext().getRequestDispatcher(
					"/console/messages.jsp?MESSAGE=" + message.toString());
			rd.forward(request, response);
		}
	}

	private boolean doIndex(String infoType) {
		boolean flag = false;
		//创建
		if (infoType.equals("CREATE_INDEX_PAGE")) {

			PeijianCreateIndexTask task = new PeijianCreateIndexTask();
			try {
				task.createIndex();
				flag = true;
			} catch (Exception e) {
				flag = false;
			}
			return flag;
		}
		
		//更新
		if (infoType.equals("UPDATE_INDEX_PAGE")) {
			PeiJianUpdateIndexTask update = new PeiJianUpdateIndexTask();
			PeiJianDeleteIndexTask del = new PeiJianDeleteIndexTask();
			try {
				update.indexUpdate();
				del.deleteIndex();
				flag = true;
			}catch( Exception e ) {
				e.printStackTrace();
				flag = false;
			}
			return flag;
		}
		// 优化
		if (infoType.equals("OPTIMIZE_INDEX_PAGE")) {
			try {
				PeiJianOptimizeIndexTask.optimizeIndex();
				flag = true;
			}
			catch( Exception e ) {
				flag = false;
			}
			return flag;
		}
		//备份
		if (infoType.equals("COPY_INDEX_PAGE")) {
			PeiJianIndexBackUpTask backUp = new PeiJianIndexBackUpTask();
			try {
				backUp.copyIndex( PropertyConfiguration.getPeiJianIndexPath(), PropertyConfiguration.getPeiJianBackUpIndexPath() );
				flag = true;
			}
			catch( Exception e ) {
				flag = false;
			}
			return flag;
		}
		
		return flag;
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
