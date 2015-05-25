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
	
	public static <R, P, U> R executePreparedStatement2(
			PreparedStatementFunction2<R, P, U> function, String sql, P param, U param2) {
		try (Connection connection = SqliteConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			return function.apply(statement, param, param2);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
	}
	
	public static <R, P, U, Z> R executePreparedStatement3(
			PreparedStatementFunction3<R, P, U, Z> function, String sql, P param, U param2, Z param3) {
		try (Connection connection = SqliteConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			return function.apply(statement, param, param2, param3);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
	}

	@FunctionalInterface
	public static interface PreparedStatementFunction<R, P> {
		R apply(PreparedStatement statement, P param);
	}
	@FunctionalInterface
	public static interface PreparedStatementFunction2<R, P, U> {
		R apply(PreparedStatement statement, P param, U pram2);
	}
	@FunctionalInterface
	public static interface PreparedStatementFunction3<R, P, U, Z> {
		R apply(PreparedStatement statement, P param, U pram2, Z param3);
	}
}
