package dgeniust.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dgeniust.config.DBConnectSQL;
import dgeniust.models.UserModel;


public class UserDAOImpl extends DBConnectSQL implements IUserDAO{
	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
	
	@Override
	public List<UserModel> findAll() {
		String sql = "select * FROM users";
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("pword"), rs.getString("fullname"), rs.getString("image"),
						findRoleByID(rs.getInt("id"))));
			}
			if (list.size()!=0)
				return list;
		} catch (Exception e) {
			
		}
		return null;
	}
	@Override
	public UserModel findByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
		
			while (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("pword"));
				oneUser.setRole(findRoleByID(rs.getInt("id")));
				return oneUser;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public UserModel findById(int Id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		UserModel oneUser = new UserModel();

		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			rs = ps.executeQuery();
			while (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username"));
				oneUser.setFullname(rs.getString("fullname"));
				oneUser.setEmail(rs.getString("email"));
				oneUser.setPassword(rs.getString("pword"));
				oneUser.setRole(findRoleByID(rs.getInt("id")));
				return oneUser;
			}
			
		} catch (Exception e) {
			
		} finally {

		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users (username, email, pword, fullname) VALUES (?, ?, ?, ?)";
		try {
			
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getFullname());
			System.out.println(ps.toString());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserModel um = findByUserName(user.getUsername());
		insert(um, user.getRole());
	}

	@Override
	public void insert(UserModel user, String role) {
		String sql = "INSERT INTO tableRole (id, role) VALUES (?, ?)";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, role);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		UserModel user = null;
		user = this.findByEmail(email);
		if (user == null)
			return false;
		return true;
	}

	@Override
	public boolean checkExistUsername(String username) {
		UserModel user = null;
		user = this.findByUserName(username);
		if (user == null)
			return false;
		return true;
	}

	public static void main(String[] arg) {
		IUserDAO userDAO = new UserDAOImpl();
		UserModel user = userDAO.findByEmail("dathiichan141@gmail.com");
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getFullname());
		System.out.println(user.getRole());

	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ?";
		UserModel oneUser = new UserModel();

		try {
			System.out.println(1);
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			System.out.println(2);
			if (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username").strip());
				oneUser.setFullname(rs.getString("fullname").strip());
				oneUser.setEmail(rs.getString("email").strip());
				oneUser.setPassword(rs.getString("pword").strip());
				oneUser.setRole(findRoleByID(oneUser.getId()));
				System.out.println(4);
				return oneUser;
			}
			System.out.println(3);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}
	
	@Override
	public UserModel findByFullname(String fullname) {
		String sql = "SELECT * FROM users WHERE fullname = ?";
		UserModel oneUser = new UserModel();

		try {
			System.out.println(1);
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, fullname);
			rs = ps.executeQuery();
			System.out.println(2);
			if (rs.next()) {
				oneUser.setId(rs.getInt("id"));
				oneUser.setUsername(rs.getString("username").strip());
				oneUser.setFullname(rs.getString("fullname").strip());
				oneUser.setEmail(rs.getString("email").strip());
				oneUser.setPassword(rs.getString("pword").strip());
				oneUser.setRole(findRoleByID(oneUser.getId()));
				System.out.println(4);
				return oneUser;
			}
			System.out.println(3);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	@Override
	public String findRoleByID(int id) {
		String sql = "SELECT * FROM tableRole WHERE id = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("role");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}

	@Override
	public boolean changePasswordByMail(String mail, String password) {
		String sql = "UPDATE users SET pword=? WHERE email = ?";
		try {
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, mail);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return false;
	}
	@Override
	public UserModel update(UserModel user) {
		String sql = "UPDATE users SET fullname = ?, email = ? WHERE username = ?";
		try {
			
			conn = super.getDatabaseConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(3, user.getUsername());
			ps.setString(2, user.getEmail());
			ps.setString(1, user.getFullname());
			int y = ps.executeUpdate();
			if(y>0) {
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
