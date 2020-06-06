package telran.net;

import java.io.Serializable;

public class ResponseJava implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TcpResponseCode code;
	public Serializable responseDta;
	public ResponseJava(TcpResponseCode code, Serializable responseDta) {
		super();
		this.code = code;
		this.responseDta = responseDta;
	}

}
