package telran.net;

import java.io.Serializable;

public class RequestJava implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RequestJava(String requesrType, Serializable requestData) {
		super();
		this.requesrType = requesrType;
		this.requestData = requestData;
	}
	public String requesrType;
	public Serializable requestData;

}
