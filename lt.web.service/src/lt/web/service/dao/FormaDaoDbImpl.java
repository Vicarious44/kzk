package lt.web.service.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lt.web.service.model.FormaDuomenys;
import lt.web.service.model.FormaSablonas;

public class FormaDaoDbImpl implements FormaDao{
	
	private static final String TEMPL_CREATE_SQL = "insert into Templates (t_owner,f_data,f_title)"
			+ " values ((SELECT id FROM Users WHERE username = ?), ?, ?)";
	private static final String TEMPL_GET_SQL = "SELECT t_id, f_data, f_title FROM Templates WHERE t_id = ? AND t_owner=(SELECT id FROM Users WHERE username = ?)";
	private static final String TEMPL_GET_ALL_SQL = "SELECT t_id, f_data, f_title FROM Templates"
			+ " WHERE t_owner = (SELECT id FROM Users WHERE username = ?)";
	private static final String TEMPL_DELETE_SQL = "DELETE FROM Templates WHERE t_id=? AND t_owner=(SELECT id FROM Users WHERE username = ?)";
	private static final String DATA_DELETE_SQL = "DELETE FROM Data WHERE d_template_id = (SELECT t_id FROM Templates WHERE t_id = ? AND t_owner = (SELECT id FROM Users WHERE username = ?))";
	private static final String TEMPL_UPDATE_SQL = "UPDATE Templates SET f_title=?, f_data=? WHERE t_owner=(SELECT id FROM Users WHERE username = ?) AND t_id=?";
	private static final String DATA_CREATE_SQL = "insert into Data (d_template_id, d_owner, d_data) values (?,(SELECT id FROM Users WHERE username = ?), ?)";
	//private static final String DATA_GET_SQL = "SELECT d_id, d_template_id, username, d_data FROM Data INNER JOIN Users ON d_owner = id WHERE d_id = ? AND d_template_id = ?";
	//private static final String DATA_GET_ALL_SQL = "SELECT d_id, d_template_id, username, d_data FROM Data INNER JOIN Users ON d_owner = id WHERE d_template_id=?";
	private static final String DATA_GET_SQL = "SELECT d_id, d_template_id, username, d_data FROM Data, Templates"
			+ " INNER JOIN Users ON d_owner = id"
			+ " WHERE d_id = ? AND d_template_id = ? AND t_owner = (SELECT id FROM Users WHERE username = ?)";
	private static final String DATA_GET_ALL_SQL = "SELECT d_id, d_template_id, username, d_data "
			+ "FROM Data, Templates INNER JOIN Users ON d_owner = id"
			+ " WHERE d_template_id=? AND t_owner = (SELECT id FROM Users WHERE username = ?)";
	
	@Override
	public List<FormaDuomenys> getDataList(int sablonasID, String owner)
			throws DaoException {
		// TODO Auto-generated method stub
		return SqlExecutor.executePreparedStatement2(this::getDataListFunction, DATA_GET_ALL_SQL, sablonasID, owner);
	}

	@Override
	public FormaDuomenys createData(String ownerID, int sablonasID,
			FormaDuomenys duom) throws DaoException {
		return SqlExecutor.executePreparedStatement3(this::createDataFunction, DATA_CREATE_SQL, duom, ownerID, sablonasID);
	}

	@Override
	public FormaDuomenys updateData(String id, int tempid, int duomid,
			FormaDuomenys duom) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys getData(int duomid, int tempid, String owner)
			throws DaoException {
		return SqlExecutor.executePreparedStatement3(this::getDataFunction, DATA_GET_SQL, duomid, tempid, owner);
	}
	
	private List<FormaDuomenys> getDataListFunction(PreparedStatement statement, int templId, String owner){
		try {
			statement.setInt(1, templId);
			statement.setString(2, owner);
			if(statement.execute()){
				ResultSet rs = statement.getResultSet();
				List<FormaDuomenys> result = new ArrayList<FormaDuomenys>();
				while(rs.next()){
					result.add(fillDataData(rs));
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "No data for template:"+templId);
	}
	
	private FormaDuomenys getDataFunction(PreparedStatement statement, int id, int tid, String owner){
		try {
			statement.setInt(1, id);
			statement.setInt(2, tid);
			statement.setString(3, owner);
			if(statement.execute()){
				ResultSet rs = statement.getResultSet();
				if(rs.next()){
					return fillDataData(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "No such data goes by id=" + id);
	}
	
	private FormaDuomenys createDataFunction(PreparedStatement statement, FormaDuomenys duom, String owner, int templ) throws DaoException{
		try {
			statement.setInt(1, templ);
			statement.setString(2, owner);
			if(duom.getData().length() < 100001){
				statement.setString(3, duom.getData());
			}
			else
				throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, "string size larger than 10k");
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		return duom;
	}

	@Override
	public FormaDuomenys deleteData(String id, int tempid, int duomid)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormaSablonas> getTemplateList(String ownerID)
			throws DaoException {
		return SqlExecutor.executePreparedStatement(this::getTemplateListFunction, TEMPL_GET_ALL_SQL, ownerID);
	}

	@Override
	public FormaSablonas createTemplate(String ownerID, FormaSablonas sab)
			throws DaoException {
		return SqlExecutor.executePreparedStatement2(this::createTemplateFunction, TEMPL_CREATE_SQL, sab, ownerID);
	}
	
	@Override
	public FormaSablonas updateTemplate(String ownerID, int tempId,
			FormaSablonas temp) throws DaoException {
		return SqlExecutor.executePreparedStatement3(this::updateTemplateFunction, TEMPL_UPDATE_SQL, temp, tempId, ownerID);
	}

	@Override
	public FormaSablonas getTemplate(int sablonasID, String owner)
			throws DaoException {
		return SqlExecutor.executePreparedStatement2(this::getTemplateFunction, TEMPL_GET_SQL, sablonasID, owner);
	}

	@Override
	public FormaSablonas deleteTemplate(String ownerID, int sablonasID)
			throws DaoException {
		SqlExecutor.executePreparedStatement2(this::deleteTempladeDataFunction, DATA_DELETE_SQL, sablonasID, ownerID);
		return SqlExecutor.executePreparedStatement2(this::deleteTemplateFunction, TEMPL_DELETE_SQL, ownerID, sablonasID);
	}
	
	private FormaSablonas updateTemplateFunction(PreparedStatement statement, FormaSablonas sab, int templ, String owner) throws DaoException{
		try {
			statement.setString(1, sab.getPavadinimas());
			statement.setString(2, sab.getDuom());
			statement.setString(3, owner);
			statement.setInt(4, templ);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		return sab;
	}	
	
	private FormaSablonas deleteTempladeDataFunction(PreparedStatement statement, int templ, String owner) throws DaoException{
		try {
			statement.setInt(1, templ);
			statement.setString(2, owner);
			statement.executeUpdate();
			return new FormaSablonas();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());	
		}
	}
	
	private FormaSablonas deleteTemplateFunction(PreparedStatement statement,
			String owner, int templ) throws DaoException {
		try {
			statement.setInt(1, templ);
			statement.setString(2, owner);
			statement.executeUpdate();
			return new FormaSablonas();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());	
		}
	}
	
	private FormaSablonas createTemplateFunction(PreparedStatement statement,
			FormaSablonas templ, String owner) throws DaoException {
		try {
			statement.setString(1, owner);
			if(templ.getDuom().length() < 100001){
				statement.setString(2, templ.getDuom());
			}
			else
				throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, "string size larger than 10k");
			statement.setString(3, templ.getPavadinimas());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		return templ;
	}
	
	private FormaSablonas getTemplateFunction(PreparedStatement statement, int id, String owner) throws DaoException{
		try {
			statement.setInt(1, id);
			statement.setString(2, owner);
			if(statement.execute()){
				ResultSet rs = statement.getResultSet();
				if(rs.next()){
					return fillTemplData(rs);
				}
			}
		} catch (Exception e) {
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "No template by id=" + id);
	}
	
	private List<FormaSablonas> getTemplateListFunction(PreparedStatement statement, String user) throws DaoException{
		try {
			statement.setString(1, user);
			if(statement.execute()){
				ResultSet rs = statement.getResultSet();
				List<FormaSablonas> result = new ArrayList<FormaSablonas>();
				while(rs.next()){
					result.add(fillTemplData(rs));
				}
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(lt.web.service.dao.DaoException.Type.ERROR, e.getMessage());
		}
		throw new DaoException(lt.web.service.dao.DaoException.Type.NO_DATA, "no templates");
	}
	
	private FormaSablonas fillTemplData(ResultSet rs) throws SQLException {
		FormaSablonas templ = new FormaSablonas();
		templ.setId(rs.getInt(1));
		templ.setDuom(rs.getString(2));
		templ.setPavadinimas(rs.getString(3));
		return templ;
	}
	private FormaDuomenys fillDataData(ResultSet rs) throws SQLException {
		FormaDuomenys duom = new FormaDuomenys();
		duom.setId(rs.getInt(1));
		duom.setT_id(rs.getInt(2));
		duom.setOwner(rs.getString(3));
		duom.setData(rs.getString(4));
		return duom;
	}

}
