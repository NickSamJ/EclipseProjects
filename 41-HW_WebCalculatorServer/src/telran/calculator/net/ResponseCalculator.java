package telran.calculator.net;

import java.io.Serializable;

public class ResponseCalculator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String code; // success error
	public String  res; // if success -number, if error - exception message
	public ResponseCalculator(String code, String res) {
		super();
		this.code = code;
		this.res = res;
	}

}
