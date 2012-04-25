package com.zhuozhuo.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.springframework.util.Assert;

/**
 * 自行封装的一个Bean处理工具, 提供Spring, Apache的BeanUtils 之外的一些功能.
 *
 * 访问在当前类声明的private/protected成员变量及private/protected函数的BeanUtils.<p/>
 * 注意,因为必须为当前类声明的变量,通过继承获得的protected变量将不能访问, 需要转型到声明该变量的类才能访问.<p/>
 * 反射的其他功能请使用Apache Jarkarta Commons BeanUtils
 */
public final class BeanUtils {

	private BeanUtils() {}

	/**
	 * 获取当前类声明的private/protected字段内容，而不必要求该字段上定义了getter方法
	 *
	 * @param object
	 *            要访问的对象
	 * @param propertyName
	 *            要访问的属性名
	 * @return 该属性的值
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 *
	 * <pre>
	 * //有一个对象book，我们不必知道它的具体类型，就可以用这个方法取出它私有的name字段内容
	 * BeanUtils.getPrivateProperty(book, &quot;name&quot;);
	 * </pre>
	 */
	public static Object getPrivateProperty(Object object, String propertyName) throws IllegalAccessException,
			NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		return field.get(object);
	}

	/**
	 * 设置当前类声明的private/protected变量
	 *
	 * @param object
	 *            要访问的对象
	 * @param propertyName
	 *            要设置的属性名
	 * @param newValue
	 *            要设置的属性值
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 *
	 * <pre>
	 *    //有一个对象object，我们不必知道它的具体类型，就可以用这个方法设置它私有的name字段的值
	 *    BeanUtils.getPrivateProperty(object,&quot;name“,&quot;newvalue&quot;);
	 * </pre>
	 */
	public static void setPrivateProperty(Object object, String propertyName, Object newValue)
			throws IllegalAccessException, NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		Field field = object.getClass().getDeclaredField(propertyName);
		field.setAccessible(true);
		field.set(object, newValue);
	}

	/**
	 * 调用当前类声明的private/protected函数
	 *
	 * @param object
	 *            要访问的对象
	 * @param methodName
	 *            要调用的方法名
	 * @param params
	 *            调用时传递的参数
	 * @return 调用后的返回值
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *
	 * <pre>
	 * //有一个对象object，我们不必知道它的具体类型，就可以用这个方法调用它私有的disaccount方法，
	 * BeanUtils.getPrivateProperty(object, &quot;disaccount&quot;, 0.05);
	 * </pre>
	 */
	public static Object invokePrivateMethod(Object object, String methodName, Object... params)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}
		Method method = object.getClass().getDeclaredMethod(methodName, types);
		method.setAccessible(true);
		return method.invoke(object, params);
	}
}
