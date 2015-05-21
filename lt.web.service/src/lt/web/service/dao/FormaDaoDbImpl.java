package lt.web.service.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lt.web.service.model.FormaDuomenys;
import lt.web.service.model.FormaSablonas;
import lt.web.service.model.User;

public class FormaDaoDbImpl implements FormaDao{
	//tbd
	/*private static final String FORMAS_GET_ALL_SQL = "select fs_id, fs_question, pavadinimas from Fsablonai"
			+ "where fs_owner = (select id from Users where username = ?)";
	private static final String FORMAS_GET_SQL = "select fs_id, fs_question, pavadinimas from Fsablonai"
			+ "where fs_owner = (select id from Users where username = ?) and fs_id = ?";
	private static final String FORMAS_CREATE_SQL = "insert into Fsablonai(fs_owner, fs_question, pavadinimas)"
			+ "values(?,?,?)";*/
	
	
	@Override
	public List<FormaSablonas> getSablonasList(int ownerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FormaDuomenys> getDuomenysList(int ownerID, int sablonasID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas getSablonas(int ownerID, int sablonasID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys getDuomenys(int owderID, int sablonasID, int duomenysID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas createSablonas(int ownerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys createDuomenys(int ownerID, int sablonasID) {
		// TODO Auto-generated method stub
		return null;
	}

}
