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
	
	static {
		userDaoMap = new HashMap<DaoFactory.PersistanceType, UserDao>();
		//userDaoMap.put(PersistanceType.FILE, new UserDaoFileImpl());
		userDaoMap.put(PersistanceType.DB, new UserDaoDbImpl());
	}
	
	public static final UserDao getUserDao(PersistanceType persistanceType){
		return userDaoMap.get(persistanceType);
	}
}
