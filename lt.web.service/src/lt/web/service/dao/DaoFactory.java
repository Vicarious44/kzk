package lt.web.service.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
	public enum PersistanceType {
		FILE("file"), DB("DB");
		
		private String code;
		
		private PersistanceType(String code) {
			this.code = code;
		}
		
		public static PersistanceType getByCode(String code){
			for(PersistanceType type : values()) {
				if(type.code.equals(code)){
					return type;
				}
			}
			return null;
		}
	}
	
	private static final Map<PersistanceType, UserDao> userDaoMap;
	private static final Map<PersistanceType, FormaDao> formaDaoMap;
	
	static {
		userDaoMap = new HashMap<DaoFactory.PersistanceType, UserDao>();
		userDaoMap.put(PersistanceType.DB, new UserDaoDbImpl());
		
		formaDaoMap = new HashMap<DaoFactory.PersistanceType, FormaDao>();
		formaDaoMap.put(PersistanceType.DB, new FormaDaoDbImpl());
	}
	
	public static final UserDao getUserDao(PersistanceType persistanceType){
		return userDaoMap.get(persistanceType);
	}
	public static final FormaDao getFormaDao(PersistanceType persitanceType){
		return formaDaoMap.get(persitanceType);
	}
}
