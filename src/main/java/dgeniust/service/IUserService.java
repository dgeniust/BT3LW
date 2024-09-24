package dgeniust.service;

import dgeniust.models.UserModel;

public interface IUserService {
	public UserModel login(String username, String password);

	public UserModel get(String username);
	
	public UserModel update(String username, String fullname, String email);

	boolean register(String username, String email, String password, String fullname);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);
	
	boolean updatePassword(String email, String password);
}
