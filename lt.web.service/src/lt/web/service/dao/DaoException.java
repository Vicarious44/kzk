package lt.web.service.dao;

public class DaoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2545452232963083750L;
	
	public enum Type {
		NO_DATA, ERROR
	}
	
	private final Type type;
	
	public DaoException(Type type, String message) {
		super(message);
		this.type = type;
	}
	
	public Type getType(){
		return type;
	}
}
