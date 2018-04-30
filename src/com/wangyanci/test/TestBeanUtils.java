package com.wangyanci.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.wangyanci.pojo.User;
import com.wangyanci.utils.MailUtils;

public class TestBeanUtils {

	@Test
	public void testBeanUtils() {
		User user = new User();
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "xiaoming");
		map.put("password", "123456");
		map.put("email", "805631580@qq.com");
		try {
			BeanUtils.populate(user, map);
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testMail() {
		try {
			MailUtils.sendMail("805631580@qq.com", "你哈袄");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
