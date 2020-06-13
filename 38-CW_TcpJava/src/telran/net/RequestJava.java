package telran.net;

import java.io.Serializable;

public class RequestJava implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RequestJava(String requesrType, Serializable requestData) {
		super();
		this.requestType = requesrType;
		this.requestData = requestData;
	}
	public String requestType;
	public Serializable requestData;

}
