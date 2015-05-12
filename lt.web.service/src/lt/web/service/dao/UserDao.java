package lt.web.service.dao;

import java.util.List;

public interface UserDao {
	User createUser(User user) throws DaoException;
	User updateUser(User user) throws DaoException;
	User getUser(String username) throws DaoException;
	List<User> getAllUsers() throws DaoException;
	Long countUsers() throws DaoException;
}
