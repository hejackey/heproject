通信组件使用说明
1.作为服务器端需要写自己的serverHandler类继承MessageServerHandler类。
  例：
  public class TestHandler extends MessageServerHandler {

	@Override
	public void messageReceived(String message) {
		System.out.println("---------------->server receive message: " + message);
	}

	@Override
	public String messageReturn(String message) {
		return "receive success: " + message;
	}

}

2. 作为 客户端需要写自己的Client类继承MessageClient
例： 
public class PayWayMsgClient extends MessageClient {

	public PayWayMsgClient(String remoteIp, int remotePort, int timeout) {
		super(remoteIp, remotePort, timeout);
	}
	
	public String getReceiveMessage() {
		return super.receiveMessage;
	}
	
	public void close() {
		super.close();
	}	
}

3.spring文件配置
	<!-- Server start -->
	<bean id="testHandler" class="com.bang.communication.server.TestHandler" />

	<bean id="messageServer" class="com.bang.communication.server.MessageServer" init-method="run">
		<property name="messageServerHandler" ref="testHandler" />
		<property name="port" value="${server.port}"></property>
	</bean>
	<!-- Server end -->
	
	<!-- Client start -->
	<bean id="payWayMsgClient" class="com.bang.communication.client.PayWayMsgClient" destroy-method="close">
		<constructor-arg index="0" value="${payway.ip}"></constructor-arg>
		<constructor-arg index="1" value="${payway.port}"></constructor-arg>
		<constructor-arg index="2" value="${payway.timeout}"></constructor-arg>
		
	</bean>
	<!-- Client end -->

4.需要在springContext.xml中加入communication.properties
	<list>
		<value>classpath*:/application.properties</value>
		<value>classpath*:/communication.properties</value>
	</list>

客户端测试例子： 
	PayWayMsgClient payWayMsgClient = (PayWayMsgClient) context.getBean("payWayMsgClient");
	
	payWayMsgClient.sentMesasge("Hello");
	payWayMsgClient.sentMesasge("支付网关给我交易流水，快！！！");
	
	