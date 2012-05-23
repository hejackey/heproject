package com.moobao.index.console;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.moobao.entertainment.indexTask.EntertainmentUpdateIndexTask;
import com.moobao.indexlogs.IndexLogs;
import com.moobao.netComputer.indexTask.NetComputerUpdateIndexTask;
import com.moobao.peijian.indexTask.PeiJianUpdateIndexTask;
import com.moobao.peijianBase.indexTask.PeiJianBaseUpdateIndexTask;
import com.moobao.phone.indexTask.PhoneUpdateIndexTask;
import com.moobao.resource.indexTask.ResourceUpdateIndexTask;
import com.moobao.restartIndex.HttpClientRestart;

public class UpdateOneDocument extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateOneDocument() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		RequestDispatcher rd = null;
		try {
			String type = request.getParameter("type");
			String ID =  request.getParameter("id");
			StringBuffer message = new StringBuffer();
			if( StringUtils.isNotEmpty(type)  && ID != null && StringUtils.isNumeric(ID)){
				int idV = Integer.parseInt(ID);
				
				boolean flag = updateOneDocument(type,idV);
				if (flag) {
					message.append( type + "---" + ID ).append(" SUCCEED:");
				} else {
					message.append( type + "---" + ID ).append(" FAILED:");
				}
			}else{
				message.append( type + "---" + ID ).append(" FAILED:");
			}
			
			rd = this.getServletContext().getRequestDispatcher(
					"/console/messages.jsp?MESSAGE=" + message.toString());

			rd.forward(request, response);
		} catch (Exception e) {
			StringBuffer message = new StringBuffer();
			message.append("更新单个Document操作失败").append(" FAILED");
			rd = this.getServletContext().getRequestDispatcher(
					"/console/messages.jsp?MESSAGE=" + message.toString());
			rd.forward(request, response);
		}
	}
	
	private boolean updateOneDocument(String type, int ID) {
		boolean flag = false;
		//更新手机的一个ID的索引信息.
		if( type.equals("phone") ) {
			PhoneUpdateIndexTask task = new PhoneUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
			}
			catch( Exception e ) {
				flag = false;
			}
			
			
			if( flag == true ) {
				//重新加载手机索引.
				try {
					HttpClientRestart.getPostMethod( "RESTART_PHONE_INDEX" );
					IndexLogs.setLogs("phone index is restarted!", this.getClass().getName());
				}
				catch( Exception e ) {
					IndexLogs.setLogs( "更新单个Document中重启手机索引异常!", this.getClass().getName() );
				}
				
				//清空所有的Cache
				try {
					HttpClientRestart.removeCache( "REMOVE_PHONE_CACHE" );
					IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
				}
				catch( Exception e ) {
					IndexLogs.setLogs( "更新单个Document中清空Cache异常!", this.getClass().getName() );
				}
			}
			
			return flag;
		}
		
		if( type.equals("peiJian") ) {
			PeiJianUpdateIndexTask task = new PeiJianUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
				
				if( flag == true ) {
					//重新加载配件索引.
					try {
						HttpClientRestart.getPostMethod( "RESTART_PEIJIAN_INDEX" );
						IndexLogs.setLogs("peiJian index is restarted!", this.getClass().getName());
					}
					catch( Exception e ) {
						System.out.println( "更新单个Document中重启配件索引异常!" );
					}
					
					//清空所有的Cache
					try {
						HttpClientRestart.removeCache( "REMOVE_PEIJIAN_CACHE" );
						IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
					}
					catch( Exception e ) {
						IndexLogs.setLogs( "更新单个Document中清空Cache异常!", this.getClass().getName() );
					}
				}
			}
			catch( Exception e ) {
				flag = false;
			}
			return flag;
		}
		
		if( type.equals("peiJianBase") ) {
			PeiJianBaseUpdateIndexTask task = new PeiJianBaseUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
				
				if( flag == true ) {
					//重新加载配件索引.
					try {
						HttpClientRestart.getPostMethod( "RESTART_PEIJIANBASE_INDEX" );
						IndexLogs.setLogs("peiJian index is restarted!", this.getClass().getName());
					}
					catch( Exception e ) {
						System.out.println( "更新单个Document中重启配件索引异常!" );
					}
					
					//清空所有的Cache
					try {
						HttpClientRestart.removeCache( "REMOVE_PEIJIAN_CACHE" );
						IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
					}
					catch( Exception e ) {
						IndexLogs.setLogs( "更新单个Document中清空Cache异常!", this.getClass().getName() );
					}
				}
			}
			catch( Exception e ) {
				flag = false;
			}
			return flag;
		}
		
		
		//更新上网本的一个ID的索引信息.
		if( type.equals("netBook") ) {
			NetComputerUpdateIndexTask task = new NetComputerUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
			}
			catch( Exception e ) {
				flag = false;
			}
			
			
			if( flag == true ) {
				//重新加载手机索引.
				try {
					HttpClientRestart.getPostMethod( "RESTART_NETCOMPUTER_INDEX" );
					IndexLogs.setLogs("netComputer index is restarted!", this.getClass().getName());
				}
				catch( Exception e ) {
					IndexLogs.setLogs( "更新单个Document中重启上网本或上网卡索引异常!", this.getClass().getName() );
				}
				
				//清空所有的Cache
				try {
					HttpClientRestart.removeCache( "REMOVE_NETCOMPUTER_CACHE" );
					IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
				}
				catch( Exception e ) {
					IndexLogs.setLogs( "更新单个上网本Document中清空Cache异常!", this.getClass().getName() );
				}
			}
			
			return flag;
		}
		
		if( type.equals("resource") ) {
			ResourceUpdateIndexTask task = new ResourceUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
				
				if( flag == true ) {
					//重新加载资讯索引.
					try {
						HttpClientRestart.getPostMethod( "RESTART_RESOURCE_INDEX" );
						IndexLogs.setLogs("Resource index is restarted!", this.getClass().getName());
					}
					catch( Exception e ) {
						System.out.println( "更新单个Document中重启资讯索引异常!" );
					}
					
					//清空所有的Cache
					try {
						HttpClientRestart.removeCache( "REMOVE_RESOURCE_CACHE" );
						IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
					}
					catch( Exception e ) {
						IndexLogs.setLogs( "更新单个Document中清空Cache异常!", this.getClass().getName() );
					}
				}
			}
			catch( Exception e ) {
				flag = false;
			}
			return flag;
		}
		
		if( type.equals("entertainment") ) {
			EntertainmentUpdateIndexTask task = new EntertainmentUpdateIndexTask();
			try {
				boolean f = task.indexUpdate(ID);
				if( f == true )
					flag = true;
				else
					flag = false;
				
				if( flag == true ) {
					//重新加载娱乐中心索引.
					try {
						HttpClientRestart.getPostMethod( "RESTART_ENTERTAINMENT_INDEX" );
						IndexLogs.setLogs("entertainment index is restarted!", this.getClass().getName());
					}
					catch( Exception e ) {
						System.out.println( "更新单个Document中重启娱乐中心索引异常!" );
					}
					
					//清空所有的Cache
					try {
						HttpClientRestart.removeCache( "REMOVE_ENTER_CACHE" );
						IndexLogs.setLogs("Cache is removed!", this.getClass().getName());
					}
					catch( Exception e ) {
						IndexLogs.setLogs( "更新单个Document中清空Cache异常!", this.getClass().getName() );
					}
				}
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
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet( request, response );
	}

}
