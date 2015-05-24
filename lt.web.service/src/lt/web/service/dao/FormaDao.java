package lt.web.service.dao;
import java.util.List;

import lt.web.service.model.FormaSablonas;
import lt.web.service.model.FormaDuomenys;

public interface FormaDao {

	List<FormaDuomenys> getDataList(String ownerID, int sablonasID) throws DaoException;
	FormaDuomenys createData(String ownerID, int sablonasID, FormaDuomenys duom) throws DaoException;
	FormaDuomenys updateData(String id, int tempid, int duomid, FormaDuomenys duom) throws DaoException;
	FormaDuomenys getData(String id, int tempid, int duomid) throws DaoException;
	FormaDuomenys deleteData(String id, int tempid, int duomid) throws DaoException;

	List<FormaSablonas> getTemplateList(String ownerID) throws DaoException;
	FormaSablonas createTemplate(String ownerID, FormaSablonas sab) throws DaoException;
	FormaSablonas updateTemplate(String ownerID, int tempId, FormaSablonas temp) throws DaoException;
	FormaSablonas getTemplate(String ownerID,int sablonasID) throws DaoException;
	FormaSablonas deleteTemplate(String ownerID, int sablonasID) throws DaoException;
}
