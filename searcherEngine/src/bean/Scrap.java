package bean;

import java.io.Serializable;

public class Scrap implements Serializable{
	private static final long serialVersionUID = 1L;
	String divId;
	String divHtml;
	public String getDivId() {
		return divId;
	}
	public void setDivId(String divId) {
		this.divId = divId;
	}
	public String getDivHtml() {
		return divHtml;
	}
	public void setDivHtml(String divHtml) {
		this.divHtml = divHtml;
	}
	
}
