package dgeniust.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dgeniust.config.DBConnectSQL;
import dgeniust.dao.impl.ICategoryDao;
import dgeniust.models.CategoryModel;

public class CategoryDaoImpl implements ICategoryDao {
	
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<CategoryModel> findAll() {
		String sql = " Select * from categories ";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			if (list.size()!=0)
				return list;
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel fingById(int id) {
		String sql = " Select * from categories where categoryid = ? ";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(CategoryModel category) {
		String sql = " Insert into categories (categoryname, images, status) VALUES (?, ?, ?)";
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(CategoryModel category) {
		String sql = " Update categories set categoryname = ? , images = ? , status = ? where categoryid =?";
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = " Delete from categories where categoryid = ?";
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CategoryModel> findByName(String keyword) {
		String sql = " Select * from categories where categoryname like ?";
		List<CategoryModel> list = new ArrayList<>();
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%k" + keyword +"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCategoryid(rs.getInt("categoryid"));
				category.setCategoryname(rs.getString("categoryname"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			if (list.size()!=0)
				return list;
			
			conn.close();
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void softDelete(CategoryModel category) {
		String sql = " Update categories set  status = ? where categoryid =?";
		try {
			conn = DBConnectSQL.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, category.getStatus());
			ps.setInt(2, category.getCategoryid());
			ps.executeUpdate();
			
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
