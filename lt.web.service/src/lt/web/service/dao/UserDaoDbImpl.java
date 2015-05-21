package lt.web.service.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lt.web.service.model.User;

public class UserDaoDbImpl implements UserDao{
	
	private static final String USER_COUNT_SQL = "select count(*) from Users";
	private static final String USER_GET_ALL_SQL = "select username, password from Users";
	private static final String USER_GET_SQL = "SELECT username, password FROM Users WHERE username = ?";
	private static final String USER_CREATE_SQL = "insert into Users (username, password) values (?, ?)";
	private static final String USER_UPDATE_SQL = "UPDATE Users SET password = ? WHERE username = ?";

	@Override
	public User createUser(User user) throws DaoException {
		return SqlExecutor.executePreparedStatement(this::createUserFunction, USER_CREATE_SQL, user);
	}
	
	@Override
	public User updateUser(User user) throws DaoException{
		return SqlExecutor.executePreparedStatement(this::updateUserFunction, USER_UPDATE_SQL, user);
	}

	@Override
	public User getUser(String username) throws DaoException {
		return SqlExecutor.executePreparedStatement(this::getUserFunction, USER_GET_SQL, username);
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		return SqlExecutor.executeStatement(this::getUserListFunction);
	}

	@Override
	public Long countUsers() {
		return SqlExecutor.executeStatement(this::countUsersFunction);
	}
	
	private List<User> getUserListFunction(Statement statement) throws DaoException {
		try {
			if (statement.execute(USER_GET_ALL_SQL)) {
				ResultSet rs = statement.getResultSet();
				List<User> result = new ArrayList<>();
				while (rs.next()) {
					result.add(fillUserData(rs));
				}
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "No users");
	}

	private User getUserFunction(PreparedStatement statement, String username)
			throws DaoException {
		try {
			statement.setString(1, username);
			if (statement.execute()) {
				ResultSet rs = statement.getResultSet();
				if (rs.next()) {
					return fillUserData(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "No users by username=" + username);
	}

	private User createUserFunction(PreparedStatement statement,
			User user) throws DaoException {
		try {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPswhash());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}

		return user;
	}
	
	private User updateUserFunction(PreparedStatement statement, User user) throws DaoException{
		try{
			statement.setString(1, user.getPswhash());
			statement.setString(2, user.getUsername());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		return user;
	}
	private Long countUsersFunction(Statement statement) throws DaoException {
		try {
			if (statement.execute(USER_COUNT_SQL)) {
				ResultSet rs = statement.getResultSet();
				if (rs.next()) {
					return rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		return 0L;
	}

	private User fillUserData(ResultSet rs) throws SQLException {
		User user = new User(rs.getString(1), rs.getString(2));
		return user;
	}

}
