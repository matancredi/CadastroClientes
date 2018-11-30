package br.com.fatec.eng3.negocio;

public abstract class AbstractValidadores implements IStrategyValidadores {
	
	private StringBuilder msg;

	public String getMsg() {
		
		if(this.msg.length() > 0)
				return msg.toString();
		
		return null;
	}

	public void setMsg(StringBuilder msg) {
		this.msg = msg;
	}
	
	

}
