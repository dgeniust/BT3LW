package dgeniust.dao.impl;

import java.util.List;

import dgeniust.models.UserModel;



public interface IUserDAO {
	List<UserModel> findAll();

	UserModel findById(int id);

	void insert(UserModel user);
	
	UserModel update(UserModel user);
	
	UserModel findByUserName(String username);
	
	public UserModel findByFullname(String fullname);
	
	UserModel findByEmail(String email);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	String findRoleByID(int id);
	
	void insert(UserModel user, String role);
	
	boolean changePasswordByMail(String mail, String password);

}
