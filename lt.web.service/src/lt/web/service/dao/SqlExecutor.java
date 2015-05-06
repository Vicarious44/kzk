package lt.web.service.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;
import java.sql.PreparedStatement;

public class SqlExecutor {
	public static <R> R executeStatement(Function<Statement, R> function) {
		try (Connection connection = SqliteConnector.getConnection();
				Statement statement = connection.createStatement()) {
			return function.apply(statement);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
	}

	public static <R, P> R executePreparedStatement(
			PreparedStatementFunction<R, P> function, String sql, P param) {
		try (Connection connection = SqliteConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			return function.apply(statement, param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
	}

	@FunctionalInterface
	public static interface PreparedStatementFunction<R, P> {
		R apply(PreparedStatement statement, P param);
	}
}
