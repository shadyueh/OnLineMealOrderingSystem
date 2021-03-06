package com.wangyanci.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.wangyanci.pojo.User;
import com.wangyanci.service.UserService;
import com.wangyanci.service.UserServiceImp;
import com.wangyanci.utils.ActiveCodeUtils;

/**
 * Servlet implementation class RegistUserServlet
 */
public class RegistUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(RegistUserServlet.class);

	public RegistUserServlet() {
		super();

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("----------------");

		// 进行验证码判断
		String checkCode = new String(request.getParameter("checkcode").getBytes("iso-8859-1"), "utf-8");

		String _checkCode = (String) request.getSession().getAttribute("checkcode_session");
		logger.info("用户输入的验证码————————" + checkCode);
		logger.info("系统生成的验证码————————" + _checkCode);

		request.getSession().removeAttribute("checkcode_session");// 从session中删除。

		if (!checkCode.equals(_checkCode)) {
			request.getSession().setAttribute("regist.message", "验证码不正确");
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}

		// 1.得到所有请求参数，封装到User对象中.
		User user = new User();
		Map<String, String> paramap = new HashMap<String, String>();
		for (String str : request.getParameterMap().keySet()) {
			if (!str.equals("checkcode"))
				System.out.println(str + "-------"
						+ new String(request.getParameterMap().get(str)[0].getBytes("iso-8859-1"), "utf-8"));
			paramap.put(str, new String(request.getParameterMap().get(str)[0].getBytes("iso-8859-1"), "utf-8"));
		}

		try {
			BeanUtils.populate(user, paramap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 调用校验方法
		Map<String, String> map = user.validation();

		if (map.size() != 0) {
			request.setAttribute("map", map);
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}

		// 手动封装激活码
		user.setActivecode(ActiveCodeUtils.getActiveCode());

		// 2.调用service完成注册操作.
		UserService service = new UserServiceImp();

		try {
			service.regist(user);

			// 3.注册成功
			// response.setHeader("refresh", "3;url=http://www.estore.com");
			response.sendRedirect(request.getContextPath() + "/regist_success.jsp");
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("regist.message", e.getMessage());
			request.getRequestDispatcher("/regist.jsp").forward(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
