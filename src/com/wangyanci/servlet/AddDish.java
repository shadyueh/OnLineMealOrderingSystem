package com.wangyanci.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.wangyanci.pojo.Dish;
import com.wangyanci.service.DishService;
import com.wangyanci.serviceimp.DishServiceImp;
import com.wangyanci.utils.PicUtils;
import com.wangyanci.utils.UploadUtils;

public class AddDish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddDish() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String[]> map = new HashMap<String, String[]>();// 用于封装所有请求参数
		// 1.设置临时文件存储位置以及缓存大小
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 100);
		factory.setRepository(new File(this.getServletContext().getRealPath("/temp")));

		// 2.得到ServletFileUpload
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8"); // 解决上传文件中文乱码.
		boolean flag = upload.isMultipartContent(request);

		if (flag) {

			// upload.setSizeMax(1024*1024*10);//设置上传文件总大小
			// 解析rquest,得到所有上传项
			try {
				List<FileItem> items = upload.parseRequest(request);

				// 3.遍历items,得到每一个上传项
				for (FileItem item : items) {

					if (item.isFormField()) {
						// 不是上传组件
						map.put(item.getFieldName(), new String[] { item.getString("utf-8") }); // 封装其它数据
					} else {
						// 是上传组件.
						String filename = item.getName(); // 获取上传文件名称

						// 得到真实名称
						filename = UploadUtils.subFileName(filename);

						// 得到随机名称
						String uuidname = UploadUtils.generateRandonFileName(filename);

						// 得到随机目录
						String uuidDir = UploadUtils.generateRandomDir(uuidname);

						// 判断随机目录是否存在，不存在，创建.
						File randomDir = new File(this.getServletContext().getRealPath("/upload" + uuidDir));

						if (!randomDir.exists()) {
							randomDir.mkdirs();
						}
						// 完成文件上传
						File dest = new File(randomDir, uuidname);
						IOUtils.copy(item.getInputStream(), new FileOutputStream(dest));

						item.delete();// 删除临时文件.

						// 生成缩略图
						PicUtils putils = new PicUtils(dest.getCanonicalPath());// 获取上传文件的绝对磁盘路径。

						putils.resize(200, 200);// 就会产生一个200*200的缩略图.
						// 封装imgurl
						map.put("imgurl", new String[] { "/upload" + uuidDir + "/" + uuidname });

					}
				}

				map.put("id", new String[] { UUID.randomUUID().toString() });// 封装id

				// 使用BeanUtils将所有数据封装到Product
				Dish p = new Dish();

				BeanUtils.populate(p, map);

				// 调用service完成添加操作
				DishService service = new DishServiceImp();

				service.addProduct(p);

				// 得定向到首页
				response.sendRedirect("http://localhost:8080/OnLineOrderingSystem/");
				return;

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
