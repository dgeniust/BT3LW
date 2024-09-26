package dgeniust.controller;

import java.io.IOException;
import java.util.List;

import dgeniust.dao.CategoryServiceImpl;
import dgeniust.models.CategoryModel;
import dgeniust.service.ICategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet (urlPatterns = {"/admin/categories", "/admin/category/edit", "/admin/category/update","/admin/category/insert", "/admin/category/add", "/admin/category/delete"})
public class CategoryController extends HttpServlet{
	public ICategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("categories")) {
			List<CategoryModel> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/edit")){
			int id = Integer.parseInt(req.getParameter("id"));
			CategoryModel category = cateService.fingById(id);
			
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/add")){
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		}
		else if(url.contains("/admin/category/delete")){
			int id = Integer.parseInt(req.getParameter("id"));
			System.out.println("test id: ");
			System.out.println(id);
			CategoryModel category = cateService.fingById(id);
			cateService.delete(category.getCategoryid());
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();
		if(url.contains("/admin/category/update")) {
			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = "https://stcv4.hnammobile.com/downloads/2/goi-y-top-30-bo-phim-tinh-cam-han-quoc-khong-the-bo-qua-41675351263.jpg";
			
			CategoryModel category = new CategoryModel();
			category.setCategoryid(categoryid);
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);
			
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
		}
		else if(url.contains("/admin/category/insert")) {
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = "https://stcv4.hnammobile.com/downloads/2/goi-y-top-30-bo-phim-tinh-cam-han-quoc-khong-the-bo-qua-41675351263.jpg";
			
			CategoryModel category = new CategoryModel();
			category.setCategoryname(categoryname);
			category.setImages(images);
			category.setStatus(status);
			System.out.println("test 1");
			System.out.println(categoryname);
			System.out.println(images);
			System.out.println(status);
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
