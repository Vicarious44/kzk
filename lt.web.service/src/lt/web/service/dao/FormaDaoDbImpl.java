package lt.web.service.dao;

import java.util.List;

import lt.web.service.model.FormaDuomenys;
import lt.web.service.model.FormaSablonas;

public class FormaDaoDbImpl implements FormaDao{

	@Override
	public List<FormaDuomenys> getDataList(String ownerID, int sablonasID)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys createData(String ownerID, int sablonasID,
			FormaDuomenys duom) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys updateData(String id, int tempid, int duomid,
			FormaDuomenys duom) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaDuomenys getData(String id, int tempid, int duomid)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas createTemplate(String ownerID, FormaSablonas sab)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas updateTemplate(String ownerID, int tempId,
			FormaSablonas temp) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas getTemplate(String ownerID, int sablonasID)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormaSablonas deleteTemplate(String ownerID, int sablonasID)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
