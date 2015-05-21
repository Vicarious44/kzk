package lt.web.service.dao;
import java.util.List;

import lt.web.service.model.FormaSablonas;
import lt.web.service.model.FormaDuomenys;

public interface FormaDao {
	List<FormaSablonas> getSablonasList(int ownerID);
	List<FormaDuomenys> getDuomenysList(int ownerID, int sablonasID);
	FormaSablonas getSablonas(int ownerID,int sablonasID);
	FormaDuomenys getDuomenys(int owderID,int sablonasID, int duomenysID);
	FormaSablonas createSablonas(int ownerID);
	FormaDuomenys createDuomenys(int ownerID, int sablonasID);
}
