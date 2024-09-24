package dgeniust;

import dgeniust.dao.impl.IUserDAO;
import dgeniust.dao.impl.UserDAOImpl;
import dgeniust.models.UserModel;

public class test {
	public static void main(String[] args) {
		IUserDAO userDao = new UserDAOImpl();
		UserModel user = userDao.findByUserName("dgeniust");
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getFullname());
		System.out.println(user.getRole());
	}
}
